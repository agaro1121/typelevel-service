package repo

import cats.{Functor, Monad}
import cats.syntax.functor._
import models.User
import doobie._
import doobie.implicits._
import org.joda.time.LocalDate
import Meta._

class DoobieUserRepo[F[_]: Monad](xa: Transactor[F]) extends UserRepo[F] {

  implicit val LocalDateMeta: Meta[LocalDate] = DateMeta.xmap(
    date => new LocalDate(date.getTime),
    b => new java.sql.Date(b.getYear - 1900, b.getMonthOfYear, b.getDayOfMonth)
  )

  override def add(user: User): F[Int] = {
    import user._
      sql"insert into users (first_name, last_name, birthday) values ($firstName, $lastName, $birthday)".update.run.transact(xa)
  }

  override def get(id: String): F[Option[User]] =
    sql"select * from users where first_name = $id".query[User].option.transact(xa)

  override def getAll: F[Vector[User]] =
    sql"select * from users".query[User].to[Vector].transact(xa)
}
