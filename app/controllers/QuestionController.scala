package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.Questions
import models._
import services.WordServices

@Singleton
class QuestionController @Inject() (questionDao: Questions)(implicit exec: ExecutionContext) extends Controller {

  def getQuestionWord (id: Int) = Action.async {
    questionDao
      .getQuestionWord(id)
      .map(w => Ok(JsObject(Seq(
        "word" -> WordServices.appendConjugationsToWord(w._1._1, w._1._2),
        "translation" -> WordServices.appendConjugationsToWord(w._2._1, w._2._2)
      ))))
  }
}
