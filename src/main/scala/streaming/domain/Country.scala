package streaming.domain

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

case class Country(
  code: String,
  name: String,
  continent: String,
  region: String,
  surfaceArea: Double,
  indepYear: Option[Int],
  population: Int,
  lifeExpectancy: Option[Double],
  gnp: Option[scala.math.BigDecimal],
  gnpold: Option[scala.math.BigDecimal],
  localName: String,
  governmentForm: String,
  headOfState: Option[String],
  capital: Option[Int],
  code2: String
)

object Country {
  implicit val countryEncoder: Encoder[Country] = deriveEncoder[Country]
}