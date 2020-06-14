package streaming.environment.repository

import streaming.domain.City
import zio._

object CitiesRepository {

  trait Service {
    def all: fs2.Stream[Task, City]
    def byCountry(country: String): fs2.Stream[Task, City]
    def byId(id: Int): Task[Option[City]]
  }

  val live: URLayer[DbTransactor, CitiesRepository] =
    ZLayer.fromService { resource =>
      Database(resource.xa)
    }
}
