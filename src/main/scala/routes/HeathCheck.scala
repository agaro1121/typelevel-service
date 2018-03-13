package routes

import cats.Monad
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

object HealthCheck {
  def apply[F[_]: Monad]() = new Http4sDsl[F]{
    val healthCheckService: HttpService[F] = HttpService[F] {
      case GET -> Root =>
        Ok("Up and Running...")
    }
  }.healthCheckService
}
