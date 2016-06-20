package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class PersonConjugation (
  id: Option[Int],
  person: String
)

object PersonConjugation {
  implicit val conjugationReads: Reads[PersonConjugation] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "person").read[String]
  )(PersonConjugation.apply _)

  implicit val conjugationWrites: Writes[PersonConjugation] = (
    (JsPath \ "id").writeNullable[Int] and
    (JsPath \ "person").write[String]
  )(unlift(PersonConjugation.unapply _))
}

