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
      .flatMap(ws => conjugationsDao.all())
      .map(result => Ok(Json.toJson(result)))
  }

}
