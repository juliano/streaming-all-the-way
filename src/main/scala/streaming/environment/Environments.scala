package streaming.environment

import streaming.environment.config.Configuration
import streaming.environment.repository.{ DbTransactor, CitiesRepository }
import zio.ULayer
import zio.clock.Clock

object Environments {
  type HttpServerEnvironment = Configuration with Clock
  type AppEnvironment = HttpServerEnvironment with CitiesRepository

  val httpServerEnvironment: ULayer[HttpServerEnvironment] = Configuration.live ++ Clock.live
  val dbTransactor: ULayer[DbTransactor] = Configuration.live >>> DbTransactor.h2
  val citiesRepository: ULayer[CitiesRepository] = dbTransactor >>> CitiesRepository.live
  val appEnvironment: ULayer[AppEnvironment] = httpServerEnvironment ++ citiesRepository
}
