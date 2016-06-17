package dao

import javax.inject.Inject
import scala.concurrent.Future

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models.WordRelation

class WordRelations @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val WordRelations = TableQuery[WordRelationsTable]

  def all(): Future[Seq[WordRelation]] = dbConfig.db.run(WordRelations.result)

  def find (id: Int): Future[WordRelation] = {
    val wordRelation = for (w <- WordRelations if (w.id === id)) yield w
    dbConfig.db.run(wordRelation.result.head)
  }
}

class WordRelationsTable (tag: Tag) extends Table[WordRelation](tag, "wordrelations") {  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def word_from_id = column[Int]("word_from_id")
  def word_to_id = column[Int]("word_to_id")
  def relation_type_id = column[Int]("relation_type_id")

  def * = (id.?, word_from_id, word_to_id, relation_type_id) <> ((WordRelation.apply _).tupled, WordRelation.unapply _)
}
