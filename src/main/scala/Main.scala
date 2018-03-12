import config.DBConfig
import doobie.util.transactor.Transactor
import models.User
import monix.eval.Task
import repo.DoobieUserRepo
import monix.execution.Scheduler.Implicits.global
import org.joda.time.LocalDate

object Main extends App {

  val dbConfig = DBConfig.fromConfig

  val transactor = Transactor.fromDriverManager[Task](
    dbConfig.driver,
    dbConfig.jdbcBaseUrl,
    dbConfig.user,
    dbConfig.password)

  val doobieUserRepo: DoobieUserRepo[Task] = new DoobieUserRepo(transactor)

  doobieUserRepo
    .add(User("Anthony", "Garo2", new LocalDate(1988, 11, 21)))
      .flatMap(_ =>
        doobieUserRepo.getAll
      ).runOnComplete(println)

//  doobieUserRepo.getAll.runOnComplete(println)

  println("Saluton Mondo!")

}
