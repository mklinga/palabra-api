package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import play.api.libs.json._
import dao.Conjugations
import models.Conjugation

class Application extends Controller {

  def headers = List(
    "Access-Control-Allow-Origin" -> "*",
    "Access-Control-Allow-Methods" -> "GET, POST, OPTIONS, DELETE, PUT",
    "Access-Control-Max-Age" -> "3600",
    "Access-Control-Allow-Headers" -> "Origin, Content-Type, Accept, Authorization",
    "Access-Control-Allow-Credentials" -> "true"
  )

  def options (url: String) = Action { request =>
    NoContent.withHeaders(headers : _*)
  }
}
