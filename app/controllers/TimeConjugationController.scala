package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.TimeConjugations
import models.TimeConjugation

@Singleton
class TimeConjugationController @Inject() (timeConjugationsDao: TimeConjugations)(implicit exec: ExecutionContext) extends Controller {
  def index = Action.async {
    timeConjugationsDao.all().map(timeConjugations => Ok(Json.toJson(timeConjugations)))
  }
}
