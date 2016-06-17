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

class Words @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val Words = TableQuery[WordsTable]

  def all(): Future[Seq[Word]] = dbConfig.db.run(Words.result)

  def find (id: Int): Future[Word] = {
    val word = for (w <- Words if (w.id === id)) yield w
    dbConfig.db.run(word.result.head)
  }

  def findByLanguage (languageId: Int): Future[Seq[Word]] = {
    val word = for (w <- Words if w.language_id === languageId) yield w
    dbConfig.db.run(word.result)
  }

  def getRandomByLanguage (languageId: Int, amount: Int): Future[Seq[Word]] = {
    val rand = SimpleFunction.nullary[Double]("random")
    val word = for (w <- Words if w.language_id === languageId) yield w
    dbConfig.db.run(word.sortBy(x => rand).take(amount).result)
  }

  private class WordsTable (tag: Tag) extends Table[Word](tag, "words") {  

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def infinitive = column[String]("infinitive")
    def irregular = column[Boolean]("irregular")
    def language_id = column[Int]("language_id")
    def type_id = column[Int]("type_id")

    def * = (id.?, irregular, infinitive, language_id, type_id) <> ((Word.apply _).tupled, Word.unapply _)

  }

}
