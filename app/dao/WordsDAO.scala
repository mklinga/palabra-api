package dao

import javax.inject.Inject
import scala.concurrent.Future

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models.Word

class Words @Inject()(@NamedDatabase("default") dbConfigProvider: DatabaseConfigProvider) (tag: Tag) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val Words = TableQuery[WordsTable]

  def all(): Future[Seq[Word]] = dbConfig.db.run(Words.result)

  private class WordsTable (tag: Tag) extends Table[Word](tag, "Words") {  

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def infinitive = column[String]("infinitive")
    def irregular = column[Boolean]("irregular")
    def language_id = column[Int]("language_id")
    def type_id = column[Int]("type_id")

    def * = (id.?, irregular, infinitive, language_id, type_id) <> ((Word.apply _).tupled, Word.unapply _)

  }

}
