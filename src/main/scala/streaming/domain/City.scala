package streaming.domain

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

case class City(
  id: Int,
  name: String,
  countryCode: String,
  district: String,
  population: Int
)

object City {
  implicit val cityEncoder: Encoder[City] = deriveEncoder[City]
}