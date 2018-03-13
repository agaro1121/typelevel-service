package controllers

import cats.Monad
import cats.effect.Sync
import service.UserService
import cats.syntax.flatMap._
import cats.syntax.functor._
import io.circe.syntax._
import marshalling.UserMarshallar
import models.User
import org.http4s.{Request, Response}
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._

class UserController[F[_]: Monad : Sync](userService: UserService[F])
  extends Http4sDsl[F] with UserMarshallar {

  private implicit val decoder = jsonOf[F, User]

  def get: F[Response[F]] = Ok(userService.get.map(_.asJson))

  def get(id: String): F[Response[F]] = Ok(userService.get(id).map(_.asJson))

  def create(req: Request[F]): F[Response[F]] =
    req.as[User].flatMap{ user =>
      userService.add(user).flatMap{
        case true => Created()
        case false => InternalServerError()
      }
    }

}
