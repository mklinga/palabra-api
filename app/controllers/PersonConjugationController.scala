package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

import play.api.libs.json._
import dao.PersonConjugations
import models.PersonConjugation

@Singleton
class PersonConjugationController @Inject() (personConjugationsDao: PersonConjugations)(implicit exec: ExecutionContext) extends Controller {
  def index = Action.async {
    personConjugationsDao.all().map(personConjugations => Ok(Json.toJson(personConjugations)))
  }
}
