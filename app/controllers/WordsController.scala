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
import services.WordServices

@Singleton
class WordsController @Inject() (wordsDao: Words, conjugationsDao: Conjugations)(implicit exec: ExecutionContext) extends Controller {

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
          words.map((ws: Seq[Word]) => Ok(WordServices.appendConjugationsToWords(ws, conjugations))))
  }

  def getOne (id: Int) = Action.async {
    wordsDao
      .findOneWithConjugations(id)
      .map(w => Ok(WordServices.appendConjugationsToWord(w._1, w._2)))
  }

  def update (id: Int) = Action.async { request =>
      val wordJson: JsValue = request.body.asJson.get

      val newWord: Word = (wordJson \ "value").as[Word]
      val conjugations: Seq[Conjugation] = (wordJson \ "conjugations").as[Seq[Conjugation]]

      Future.sequence(Seq(
        wordsDao.update(newWord),
        conjugationsDao.update(conjugations)
      )).map(_ => Ok(wordJson))
  }
}
