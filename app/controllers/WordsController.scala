package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.Words
import dao.Conjugations

@Singleton
class WordsController @Inject() (wordsDao: Words, conjugationsDao: Conjugations)(implicit exec: ExecutionContext) extends Controller {

  def index = Action.async {
    val words = wordsDao.all()
    words
      .flatMap(ws => Future.sequence(ws.map(word => conjugationsDao.findByWord(word.id.get))))
      .map(result => Ok(Json.toJson(result)))
  }

  def getOne (id: Int) = Action.async {
    val word = wordsDao.find(id)
    
    word.flatMap(w => {
      conjugationsDao
        .findByWord(w.id.get)
        .map(cs => Ok(
          JsObject(
            Seq(
              "word" -> Json.toJson(w),
              "conjugations" -> Json.toJson(cs)
            )
          )
        )
      )
    })
  }
}
