package models

import java.sql.Timestamp

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

case class Image(id: Option[Long], name: String, fsPath: String, uri: String,
  lastAccessTime: Timestamp, accessCount: Long, creator: String)

object Images extends Table[Image]("IMAGE") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def fsPath = column[String]("FS_PATH")
  def uri = column[String]("URI")
  def lastAccessTime = column[Timestamp]("LAST_ACCESS_TIME")
  def accessCount = column[Long]("ACCESS_COUNT")
  def creator = column[String]("CREATOR")
  // def tags = column[String]("TAGS") // FIXME
  def * = id.? ~ name ~ fsPath ~ uri ~ lastAccessTime ~ accessCount ~ creator <> (Image, Image.unapply _)
 
}
