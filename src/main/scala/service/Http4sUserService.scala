package service

import cats.Monad
import cats.effect.Sync
import repo.UserRepo
import cats.syntax.functor._
import cats.syntax.flatMap._
import marshalling.UserMarshallar
import org.http4s._
import org.http4s.circe._
import io.circe.syntax._
import models.User
import org.http4s.dsl.Http4sDsl
import circe._

@Deprecated
class Http4sUserService[F[_]: Monad: Sync](repo: UserRepo[F])
  extends Http4sDsl[F] with UserMarshallar {

  private implicit val decoder = jsonOf[F, User]

  val userService = HttpService[F] {

    case GET -> Root => Ok("Welcome to the Batcave!")

    case GET -> Root / "users" =>
      Ok(repo.getAll.map(_.asJson))

    case GET -> Root / "users" / id =>
      Ok(repo.get(id).map(_.asJson))

    case req @ POST -> Root / "users" => for {
        user <- req.as[User]
        result <- repo.add(user)
        resp <- Ok(result.asJson)
      } yield resp

  }


}
