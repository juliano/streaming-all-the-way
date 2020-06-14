package streaming.http

import cats.Applicative
import io.circe.Encoder
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

package object endpoints {

  implicit def jsonEncoder[F[_] : Applicative, A](implicit encoder: Encoder[A]): EntityEncoder[F, A] =
    jsonEncoderOf[F, A]
}