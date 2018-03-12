package repo

import models.User

trait UserRepo[F[_]] {

  def add(user: User): F[Boolean]
  def get(id: String): F[Option[User]]
  def getAll: F[Vector[User]]

}
