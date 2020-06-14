package streaming.http.endpoints

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class HealthEndpoint[R] {
  type HealthTask[A] = RIO[R, A]

  private val prefixPath = "/health"

  val dsl: Http4sDsl[HealthTask] = Http4sDsl[HealthTask]
  import dsl._

  private val httpRoutes = HttpRoutes.of[HealthTask] {
    case GET -> Root => Ok("OK")
  }

  val routes: HttpRoutes[HealthTask] = Router(
    prefixPath -> httpRoutes
  )
}
