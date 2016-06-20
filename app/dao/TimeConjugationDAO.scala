package dao

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models.TimeConjugation

class TimeConjugations @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val TimeConjugations = TableQuery[TimeConjugationsTable]

  def all(): Future[Seq[TimeConjugation]] = dbConfig.db.run(TimeConjugations.result)
}

class TimeConjugationsTable (tag: Tag) extends Table[TimeConjugation](tag, "timeconjugations") {  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def time = column[String]("time")

  def * = (id.?, time) <> ((TimeConjugation.apply _).tupled, TimeConjugation.unapply _)
}
