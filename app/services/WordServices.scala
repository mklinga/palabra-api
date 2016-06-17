package services

import play.api.libs.json._
import dao.Words
import dao.Conjugations
import models._

object WordServices {
  
  def appendConjugationsToWord (word: Word, conjugations: Seq[Conjugation]): JsObject = {
    JsObject(Seq("value" -> Json.toJson(word), "conjugations" -> Json.toJson(conjugations)))
  }

  def appendConjugationsToWords (words: Seq[Word], conjugations: Seq[Seq[Conjugation]]): JsArray = {
    val combined: Seq[Tuple2[Word, Seq[Conjugation]]] = for {
      w <- words
      wordConjugations: Seq[Conjugation] = conjugations
        .fold(Seq[Conjugation]())((old: Seq[Conjugation], value: Seq[Conjugation]) => {
          if (value(0).word_id == w.id.get) value
          else old
        })
    } yield (w, wordConjugations)

    Json.arr(combined.map(cs => appendConjugationsToWord(cs._1, cs._2)))
  }

}
