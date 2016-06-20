package dao

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models.PersonConjugation

class PersonConjugations @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val PersonConjugations = TableQuery[PersonConjugationsTable]

  def all(): Future[Seq[PersonConjugation]] = dbConfig.db.run(PersonConjugations.result)
}

class PersonConjugationsTable (tag: Tag) extends Table[PersonConjugation](tag, "personconjugations") {  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def person = column[String]("person")

  def * = (id.?, person) <> ((PersonConjugation.apply _).tupled, PersonConjugation.unapply _)
}
