import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
import play.api.Application
import play.api.Play.current
import models.Images
import models.Tags
import play.Logger
import models.Image

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    database.withSession {
      Logger.info("Creating DDL")

      (Images.ddl ++ Tags.ddl).create

      Images.insertAll(
          Image(None, "hello world", "/pics/abc", "/AUESSSD.jpg", new java.sql.Timestamp(System.currentTimeMillis), 0, "someone"),
          Image(None, "hello world", "/pics/abc", "/AUESSSD.jpg", new java.sql.Timestamp(System.currentTimeMillis), 0, "someone")
      )

      Logger.info("Images")
      val q = for (i <- Images)
        yield i.id.asColumnOf[String] ++ "\t" ++ i.fsPath
      q foreach println
    }
  }
}
