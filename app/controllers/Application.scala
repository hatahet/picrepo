package controllers

import play.api._
import play.api.mvc._
import models._
import models.Tags

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

object Application extends Controller {

  def index = Action {
    (Images.ddl ++ Tags.ddl).createStatements.foreach(println)
    (Images.ddl ++ Tags.ddl).dropStatements foreach println

    
Database.forURL("jdbc:h2:mem:play", driver = "org.h2.Driver") withSession {
    Images.insertAll(
          Image(None, "hello world", "/pics/abc", "/AUESSSD.jpg", new java.sql.Timestamp(System.currentTimeMillis), 0, "someone"),
          Image(None, "hello world", "/pics/abc", "/AUESSSD.jpg", new java.sql.Timestamp(System.currentTimeMillis), 0, "someone")
      )

      val q = for (i <- Images)
        yield i.id.?.asColumnOf[String] ++ "\t" ++ i.fsPath
      q foreach println
    }
    
    Ok(views.html.index("Your new application is ready."))

  }

}