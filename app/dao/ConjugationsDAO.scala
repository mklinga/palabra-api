package dao

import javax.inject.Inject
import scala.concurrent.Future

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models.Conjugation

class Conjugations @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val Conjugations = TableQuery[ConjugationsTable]

  def all(): Future[Seq[Conjugation]] = dbConfig.db.run(Conjugations.result)

  private class ConjugationsTable (tag: Tag) extends Table[Conjugation](tag, "conjugations") {  

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def word_id = column[Int]("word_id")
    def person_conjugation_id = column[Int]("person_conjugation_id")
    def time_conjugation_id = column[Int]("time_conjugation_id")
    def value = column[String]("value")

    def * = (id.?, word_id, person_conjugation_id, time_conjugation_id, value) <> ((Conjugation.apply _).tupled, Conjugation.unapply _)

  }

}
