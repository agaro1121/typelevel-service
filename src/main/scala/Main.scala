import config.DBConfig
import doobie.util.transactor.Transactor
import fs2.StreamApp
import models.User
import monix.eval.Task
import repo.DoobieUserRepo
import monix.execution.Scheduler.Implicits.global
import org.http4s.server.blaze.BlazeBuilder
import org.joda.time.LocalDate
import service.Http4sUserService

object Main extends StreamApp[Task] {

  val dbConfig = DBConfig.fromConfig

  val transactor = Transactor.fromDriverManager[Task](
    dbConfig.driver,
    dbConfig.jdbcBaseUrl,
    dbConfig.user,
    dbConfig.password)


  val doobieUserRepo: DoobieUserRepo[Task] = new DoobieUserRepo(transactor)
  val userService = new Http4sUserService[Task](doobieUserRepo)

  val port = 9000

  override def stream(args: List[String], requestShutdown: Task[Unit]): fs2.Stream[Task, StreamApp.ExitCode] = {
    BlazeBuilder[Task]
      .bindHttp(port, "localhost")
      .mountService(userService.userService, "/")
      .serve
  }

  println(s"Running Server on port $port...")
  println("Saluton Mondo!")

}
