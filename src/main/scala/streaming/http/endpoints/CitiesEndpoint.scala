package streaming.http.endpoints

import streaming.domain._
import streaming.environment.repository._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class CitiesEndpoint[R <: CitiesRepository] {
  type CitiesTask[A] = RIO[R, A]
  type CitiesStream = fs2.Stream[CitiesTask, City]

  private val prefixPath = "/cities"

  val dsl = Http4sDsl[CitiesTask]
  import dsl._

  object CountryParameter extends
    QueryParamDecoderMatcher[String]("country")

private val httpRoutes = HttpRoutes.of[CitiesTask] {
  case GET -> Root :? CountryParameter(country) =>
    val pipeline: CitiesTask[CitiesStream] = citiesByCountry(country)
    pipeline.flatMap(stream => Ok(stream.map(_.asJson)))

  case GET -> Root =>
    val pipeline: CitiesTask[CitiesStream] = allCities
    for {
      stream <- pipeline
      json <- Ok(stream.map(_.asJson))
    } yield json
}

  val routes: HttpRoutes[CitiesTask] = Router(
    prefixPath -> httpRoutes
  )
}
