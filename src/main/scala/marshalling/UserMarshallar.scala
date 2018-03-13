package marshalling


import io.circe.{Decoder, Encoder}
import models.User
import io.circe.generic.semiauto._
import cats.syntax.either._
import org.joda.time.LocalDate

trait UserMarshallar {

  implicit val LocalDateEncoder: Encoder[LocalDate] =
    Encoder.encodeString.contramap[LocalDate](_.toString)

  implicit val LocalDateDecoder: Decoder[LocalDate] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(LocalDate.parse(str)).leftMap(_.getMessage)
  }

  implicit val UserEncoder: Encoder[User] = deriveEncoder[User]
  implicit val UserDecoder: Decoder[User] = deriveDecoder[User]

}
