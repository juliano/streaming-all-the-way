import sbt._

object Dependencies {

  object Versions {
    val zio            = "1.0.0-RC20"
    val zioInteropCats = "2.1.3.0-RC15"
    val zioLogging     = "0.3.1"
    val http4s         = "0.21.4"
    val circe          = "0.13.0"
    val quill          = "3.5.1"
    val doobie         = "0.9.0"
    val pureConfig     = "0.12.3"

    val h2             = "1.4.200"
    val logback        = "1.2.3"
  }

  object Libraries {
    def circe(artifact: String): ModuleID = "io.circe" %% artifact % Versions.circe
    def http4s(artifact: String): ModuleID = "org.http4s" %% artifact % Versions.http4s
    def zioM(artifact: String): ModuleID = "dev.zio" %% artifact % Versions.zio
    def doobie(artifact: String): ModuleID = "org.tpolecat" %% artifact % Versions.doobie

    val zio = zioM("zio")
    val zioStreams = zioM("zio-streams")
    val zioMacros = zioM("zio-macros")
    val zioInteropCats = "dev.zio" %% "zio-interop-cats" % Versions.zioInteropCats
    val zioLogging = "dev.zio" %% "zio-logging" % Versions.zioLogging

    val http4sServer = http4s("http4s-blaze-server")
    val http4sDsl = http4s("http4s-dsl")
    val http4sClient = http4s("http4s-blaze-client")
    val http4sCirce = http4s("http4s-circe")

    val circeCore = circe("circe-core")
    val circeGeneric = circe("circe-generic")
    val circeParser = circe("circe-parser")

    val quillJdbc = "io.getquill" %% "quill-jdbc" % Versions.quill
    val doobieCore = doobie("doobie-core")
    val doobieQuill = doobie("doobie-quill")
    val doobieH2 = doobie("doobie-h2")

    val pureConfig = "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig

    val h2 = "com.h2database" % "h2" % Versions.h2
    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback

    val zioTest = zioM("zio-test") % "test"
    val zioTestSbt = zioM("zio-test-sbt") % "test"
  }
}
