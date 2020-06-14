package streaming.environment.config

import pureconfig.ConfigSource
import pureconfig.generic.auto._
import zio._

object Configuration {

  final case class DbConfig(driver: String, url: String, user: String, password: String)
  final case class HttpServerConfig(host: String, port: Int, path: String)
  final case class AppConfig(database: DbConfig, httpServer: HttpServerConfig)

  val live: ULayer[Configuration] = ZLayer.fromEffectMany(
    ZIO
      .effect(ConfigSource.default.loadOrThrow[AppConfig])
      .map(c => Has(c.database) ++ Has(c.httpServer))
      .orDie
  )
}
