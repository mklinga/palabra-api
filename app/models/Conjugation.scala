package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Conjugation (
  id: Option[Int],
  word_id: Int,
  person_conjugation_id: Int,
  time_conjugation_id: Int,
  value: String
)

object Conjugation {
  implicit val conjugationReads: Reads[Conjugation] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "word_id").read[Int] and
    (JsPath \ "person_conjugation_id").read[Int] and
    (JsPath \ "time_conjugation_id").read[Int] and
    (JsPath \ "value").read[String]
  )(Conjugation.apply _)

  implicit val conjugationWrites: Writes[Conjugation] = (
    (JsPath \ "id").writeNullable[Int] and
    (JsPath \ "word_id").write[Int] and
    (JsPath \ "person_conjugation_id").write[Int] and
    (JsPath \ "time_conjugation_id").write[Int] and
    (JsPath \ "value").write[String]
  )(unlift(Conjugation.unapply _))
}

