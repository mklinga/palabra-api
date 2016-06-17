package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.Words
import dao.Conjugations
import models._

@Singleton
class WordsController @Inject() (wordsDao: Words, conjugationsDao: Conjugations)(implicit exec: ExecutionContext) extends Controller {

  private def appendConjugationsToWord (word: Word, conjugations: Seq[Conjugation]): JsObject = {
    JsObject(Seq("word" -> Json.toJson(word), "conjugations" -> Json.toJson(conjugations)))
  }

  private def appendConjugationsToWords (words: Seq[Word], conjugations: Seq[Seq[Conjugation]]): JsArray = {
    val combined: Seq[Tuple2[Word, Seq[Conjugation]]] = for {
      w <- words
      wordConjugations: Seq[Conjugation] = conjugations
        .fold(Seq[Conjugation]())((old: Seq[Conjugation], value: Seq[Conjugation]) => {
          if (value(0).word_id == w.id.get) value
          else old
        })
    } yield (w, wordConjugations)

    Json.arr(combined.map(cs => appendConjugationsToWord(cs._1, cs._2)))
  }

  def index = Action.async {
    wordsDao.all().map(words => Ok(Json.toJson(words)))
  }

  def getRandom (languageId: Int, amount: Int) = Action.async {
    val words: Future[Seq[Word]] = wordsDao.getRandomByLanguage(languageId, amount)
    words
      .flatMap((ws: Seq[Word]) =>
        Future.sequence(ws.map(
          (word: Word) => conjugationsDao.findByWord(word.id.get))))
      .flatMap((conjugations: Seq[Seq[Conjugation]]) =>
          words.map((ws: Seq[Word]) => Ok(appendConjugationsToWords(ws, conjugations))))
  }

  def getOne (id: Int) = Action.async {
    wordsDao
      .findOneWithConjugations(id)
      .map(w => Ok(appendConjugationsToWord(w._1, w._2)))
  }
}
