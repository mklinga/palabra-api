package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

import play.api.libs.json._
import dao.Words

@Singleton
class WordsController @Inject() (wordsDao: Words)(implicit exec: ExecutionContext) extends Controller {

  def index = Action.async {
    wordsDao.all().map(result => Ok(Json.toJson(result)))
  }

}
