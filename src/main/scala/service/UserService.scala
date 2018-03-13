package service

import cats.Functor
import models.User
import repo.UserRepo
import cats.syntax.functor._

trait UserService[F[_]] {
  def add(user: User): F[Boolean]
  def get(id: String): F[Option[User]]
  def get: F[Vector[User]]
}

object UserService {
  //TODO: Move this out since it has logic in it?
  def apply[F[_]: Functor](userRepo: UserRepo[F]): UserService[F] = new UserService[F]() {
    override def add(user: User): F[Boolean] =
      userRepo.add(user).map(_ > 0)

    override def get(id: String): F[Option[User]] = userRepo.get(id)

    override def get: F[Vector[User]] = userRepo.getAll
  }
}
