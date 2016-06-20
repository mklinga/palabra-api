package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class TimeConjugation (
  id: Option[Int],
  time: String
)

object TimeConjugation {
  implicit val conjugationReads: Reads[TimeConjugation] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "time").read[String]
  )(TimeConjugation.apply _)

  implicit val conjugationWrites: Writes[TimeConjugation] = (
    (JsPath \ "id").writeNullable[Int] and
    (JsPath \ "time").write[String]
  )(unlift(TimeConjugation.unapply _))
}

