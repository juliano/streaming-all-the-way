package streaming.environment

import streaming.environment.config.Configuration._
import zio.Has

package object config {

  type Configuration = Has[DbConfig] with Has[HttpServerConfig]
}
