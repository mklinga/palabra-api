package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Word (
  id: Option[Int],
  irregular: Boolean,
  infinitive: String,
  language_id: Int,
  type_id: Int
)

object Word {
  implicit val wordReads: Reads[Word] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "irregular").read[Boolean] and
    (JsPath \ "infinitive").read[String] and
    (JsPath \ "language_id").read[Int] and
    (JsPath \ "type_id").read[Int]
  )(Word.apply _)

  implicit val wordWrites: Writes[Word] = (
    (JsPath \ "id").writeNullable[Int] and
    (JsPath \ "irregular").write[Boolean] and
    (JsPath \ "infinitive").write[String] and
    (JsPath \ "language_id").write[Int] and
    (JsPath \ "type_id").write[Int]
  )(unlift(Word.unapply _))
}

