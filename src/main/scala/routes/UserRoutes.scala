package routes

import cats.Applicative
import controllers.UserController
import org.http4s._
import org.http4s.dsl.io._

object UserRoutes {

  def apply[F[_]: Applicative](userController: UserController[F]) = HttpService[F] {

      case GET -> Root =>
        userController.get

      case GET -> Root / id =>
        userController.get(id)

      case req@POST -> Root =>
        userController.create(req)

    }
}
