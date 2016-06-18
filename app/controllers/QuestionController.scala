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

  def getRandomQuestionnaire (amount: Int) = Action.async {
    questionDao
      .getRandomQuestionnaire(amount)
      .map(words => words.map(w => JsObject(Seq(
        "question" -> JsObject(Seq("word" -> Json.toJson(w._1._1), "conjugation" -> Json.toJson(w._1._2))),
        "answer" -> JsObject(Seq("word" -> Json.toJson(w._2._1), "conjugation" -> Json.toJson(w._2._2)))
      ))))
      .map(words => Ok(Json.toJson(words)))
  }
}
