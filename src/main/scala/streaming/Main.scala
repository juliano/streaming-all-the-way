package streaming

import streaming.environment.Environments.appEnvironment
import streaming.http.Server
import zio._

object Main extends App {
  def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    val program = for {
      _ <- Server.runServer
    } yield ()

    program.provideLayer(appEnvironment).exitCode
  }
}
