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

class Questions @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit exec: ExecutionContext) extends Controller {  

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val Words = TableQuery[WordsTable]
  private val WordRelations = TableQuery[WordRelationsTable]
  private val Conjugations = TableQuery[ConjugationsTable]

  private def _findWordById (id: Int) = for (w <- Words if (w.id === id)) yield w

  def all(): Future[Seq[Word]] = dbConfig.db.run(Words.result)

  private def _wrapWordAndConjugations (conjugations: Future[Seq[(Word, Conjugation)]]) = {
    conjugations.map(r =>
      r.foldLeft[(Word, Seq[Conjugation])]((null, null)) ((value: Tuple2[Word, Seq[Conjugation]], next: Tuple2[Word, Conjugation]) => {
        if (value._1 == null) (next._1, Seq(next._2))
        else (value._1, value._2 ++ Seq(next._2))
    }))
  }

  def getQuestionWord (id: Int): Future[(((Word, Seq[Conjugation]), (Word, Seq[Conjugation])))] = {
    val word = for {
      w <- Words if (w.id === id)
      c <- Conjugations if (c.word_id === id)
    } yield (w, c)

    val word2 = for {
      wr <- WordRelations if (wr.word_from_id === id)
      w <- Words if (w.id === wr.word_to_id)
      c <- Conjugations if (c.word_id === wr.word_to_id)
    } yield (w, c)

    for {
      question <- _wrapWordAndConjugations(dbConfig.db.run(word.result))
      answer <- _wrapWordAndConjugations(dbConfig.db.run(word2.result))
    } yield (question, answer)
  }

  def getRandomQuestionnaire (amount: Int): Future[Seq[(((Word, Conjugation), (Word, Conjugation)))]] = {
    val rand = SimpleFunction.nullary[Double]("random")
    val questionQuery = for {
      w <- Words
      c <- Conjugations if (w.id === c.word_id)
    } yield (w, c)

    val questions: Future[Seq[( Word, Conjugation )]] = dbConfig.db.run(questionQuery.sortBy(x => rand).take(amount).result)

    val answers: Future[Seq[( Word, Conjugation )]] = questions.flatMap(qs => {
      Future.sequence(qs.map(q => {
        val answerQuery = for {
          wr <- WordRelations if (wr.word_from_id === q._1.id.get)
          w2 <- Words if (wr.word_to_id === w2.id)
          c2 <- Conjugations if (
            (w2.id === c2.word_id) &&
            (c2.person_conjugation_id === q._2.person_conjugation_id) &&
            (c2.time_conjugation_id === q._2.time_conjugation_id)
          )
        } yield (w2, c2)

        dbConfig.db.run(answerQuery.result.head)
      }))
    })

    val sequences = for {
      qs <- questions
      as <- answers
    } yield (qs, as)

    // Map Seq(Seq[(Word, Conjugation)], Seq[(Word, Conjugation)]) into a Seq((Word, Conjugation), (Word, Conjugation))
    sequences.map(s => for (i <- 0 until (s._1.size)) yield (s._1(i), s._2(i)))
  }

}
