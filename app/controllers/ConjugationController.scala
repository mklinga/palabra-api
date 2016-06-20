package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.Conjugations
import models.Conjugation

@Singleton
class ConjugationController @Inject() (conjugationsDao: Conjugations)(implicit exec: ExecutionContext) extends Controller {
  def index = Action.async {
    conjugationsDao.all().map(conjugations => Ok(Json.toJson(conjugations)))
  }
}
