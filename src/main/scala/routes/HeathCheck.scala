package routes

import cats.Monad
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

final class HeathCheck[F[_]: Monad] extends Http4sDsl[F] {

  val healthCheckService: HttpService[F] = HttpService[F] {
    case GET -> Root =>
      Ok("Up and Running...")
  }

}

object HealthCheck {
  def apply[F[_]: Monad]() = new HeathCheck[F]().healthCheckService
}
