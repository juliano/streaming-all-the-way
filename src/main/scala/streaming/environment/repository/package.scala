package streaming.environment

import streaming.domain.City
import streaming.environment.config.Configuration.DbConfig
import doobie.util.transactor.Transactor
import zio._
import zio.interop.catz._

package object repository {

  type DbTransactor = Has[DbTransactor.Resource]
  type CitiesRepository = Has[CitiesRepository.Service]

  def allCities: RIO[CitiesRepository, fs2.Stream[Task, City]] =
    RIO.access(_.get.all)

  def cityById(id: Int): RIO[CitiesRepository, Task[Option[City]]] =
    RIO.access(_.get.byId(id))

  def citiesByCountry(country: String): RIO[CitiesRepository, fs2.Stream[Task, City]] =
    RIO.access(_.get.byCountry(country))

  object DbTransactor {
    trait Resource {
      val xa: Transactor[Task]
    }

    val h2: URLayer[Has[DbConfig], DbTransactor] = ZLayer.fromService { db =>
      new Resource {
        val xa: Transactor[Task] =
          Transactor.fromDriverManager(db.driver, db.url, db.user, db.password)
      }
    }
  }
}
