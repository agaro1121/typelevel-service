import config.DBConfig
import doobie.util.transactor.Transactor
import monix.eval.Task


object Main extends App {

  val dbConfig = DBConfig.fromConfig

  val transactor = Transactor.fromDriverManager[Task](
    dbConfig.driver,
    dbConfig.jdbcBaseUrl,
    dbConfig.user,
    dbConfig.password)

  println("Saluton Mondo!")

}
