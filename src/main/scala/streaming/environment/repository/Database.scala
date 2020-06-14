package streaming.environment.repository

import streaming.domain.City
import doobie.implicits._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import zio.Task
import zio.interop.catz._

private[repository] final case class Database(xa: Transactor[Task]) extends CitiesRepository.Service {
  val ctx = new DoobieContext.H2(Literal)
  import ctx._

  def all: fs2.Stream[Task, City] =
    ctx.stream(Queries.all).transact(xa)

  def byId(id: Int): Task[Option[City]] =
    ctx.run(Queries.byId(id)).transact(xa).map(_.headOption)

  def byCountry(country: String): fs2.Stream[Task, City] =
    ctx.stream(Queries.byCountry(country)).transact(xa)

  private object Queries {
    val all = quote(query[City])

    def byId(id: Int) = quote {
      all.filter(_.id == lift(id))
    }

    def byCountry(country: String) = quote {
      all.filter(_.countryCode == lift(country))
    }
  }
}