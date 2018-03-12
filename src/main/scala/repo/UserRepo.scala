package repo

import models.User

trait UserRepo {

  def add(user: User): Boolean
  def get(id: String): Either[Throwable, Option[User]]
  def getAll: Vector[User]

}
