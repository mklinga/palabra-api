package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class WordRelation (
  id: Option[Int],
  word_from_id: Int,
  word_to_id: Int,
  relation_type_id: Int
)

object WordRelation {
  implicit val wordReads: Reads[WordRelation] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "word_from_id").read[Int] and
    (JsPath \ "word_to_id").read[Int] and
    (JsPath \ "relation_type_id").read[Int]
  )(WordRelation.apply _)

  implicit val wordWrites: Writes[WordRelation] = (
    (JsPath \ "id").writeNullable[Int] and
    (JsPath \ "word_from_id").write[Int] and
    (JsPath \ "word_to_id").write[Int] and
    (JsPath \ "relation_type_id").write[Int]
  )(unlift(WordRelation.unapply _))
}

