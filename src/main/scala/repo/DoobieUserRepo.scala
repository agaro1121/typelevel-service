package repo
import doobie.util.transactor.Transactor
import models.User

class DoobieUserRepo[F[_]](transactor: Transactor[F]) extends UserRepo {
  override def add(user: User): Boolean = ???

  override def get(id: String): Either[Throwable, Option[User]] = ???

  override def getAll: Vector[User] = ???
}
