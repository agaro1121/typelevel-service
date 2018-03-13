import config.DBConfig
import controllers.UserController
import doobie.util.transactor.Transactor
import fs2.StreamApp
import monix.eval.Task
import repo.{DoobieUserRepo, UserRepo}
import monix.execution.Scheduler.Implicits.global
import org.http4s.server.blaze.BlazeBuilder
import routes.{HealthCheck, UserRoutes}
import service.UserService

object Main extends StreamApp[Task] {

  val dbConfig = DBConfig.fromConfig

  val transactor = Transactor.fromDriverManager[Task](
    dbConfig.driver,
    dbConfig.jdbcBaseUrl,
    dbConfig.user,
    dbConfig.password)


  val doobieUserRepo: UserRepo[Task] = new DoobieUserRepo(transactor)
  val newUserService = UserService[Task](doobieUserRepo)
  val userController: UserController[Task] = new UserController[Task](newUserService)

  val port = 9000
  val host = "localhost"

  override def stream(args: List[String], requestShutdown: Task[Unit]): fs2.Stream[Task, StreamApp.ExitCode] = {
    BlazeBuilder[Task]
      .bindHttp(port, host)
      .mountService(UserRoutes(userController), "/users")
      .mountService(HealthCheck(), "/")
      .serve
  }

  println(s"Running Server at $host:$port...")
  println("Saluton Mondo!")

}
