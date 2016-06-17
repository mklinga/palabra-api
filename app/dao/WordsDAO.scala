package dao

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

import slick.driver.JdbcProfile  
import slick.driver.PostgresDriver.api._  
import play.api.db.slick.DatabaseConfigProvider  
import play.db.NamedDatabase
import play.api._
import play.api.mvc._

import models._

class Words @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit exec: ExecutionContext) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val Words = TableQuery[WordsTable]
  private val WordRelations = TableQuery[WordRelationsTable]
  private val Conjugations = TableQuery[ConjugationsTable]

  def all(): Future[Seq[Word]] = dbConfig.db.run(Words.result)

  def find (id: Int): Future[Word] = {
    val word = for (w <- Words if (w.id === id)) yield w
    dbConfig.db.run(word.result.head)
  }

  def findOneWithConjugations (id: Int): Future[(Word, Seq[Conjugation])] = {
    val word = for {
      w <- Words if (w.id === id)
      c <- Conjugations if (c.word_id === id)
    } yield (w, c)

    val result = dbConfig.db.run(word.result)

    result.map(r =>
      r.foldLeft((r(0)._1, Seq(r(0)._2))) ((value: Tuple2[Word, Seq[Conjugation]], next: Tuple2[Word, Conjugation]) => {
      (value._1, value._2 ++ Seq(next._2))
    }))
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
