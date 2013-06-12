package models

import java.sql.Timestamp

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

case class Image(id: Long, name: String, fsPath: String, uri: String,
  lastAccessTime: Timestamp, accessCount: Long, creator: String)

object Images extends Table[Image]("IMAGE") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // TODO: Test without AutoInc
  def name = column[String]("NAME")
  def fsPath = column[String]("FS_PATH")
  def uri = column[String]("URI")
  def lastAccessTime = column[Timestamp]("LAST_ACCESS_TIME")
  def accessCount = column[Long]("ACCESS_COUNT")
  def creator = column[String]("CREATOR")
  // def tags = column[String]("TAGS") // FIXME
  def * = id ~ name ~ fsPath ~ uri ~ lastAccessTime ~ accessCount ~ creator <> (Image, Image.unapply _)
 
}

case class Tag(id: Long, imageId: Long, value: String, addedTime: Timestamp)

object Tags extends Table[Tag]("TAG") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def imageId = column[Long]("image_id")
  def value = column[String]("value")
  def addedTime = column[Timestamp]("added_time")
  def * = id ~ imageId ~ value ~ addedTime <> (Tag, Tag.unapply _)
  def image = foreignKey("image_fk", imageId, Images)(_.id)
}

object Test {
  println("Creating DDL")
  (Images.ddl ++ Tags.ddl).create

}

trait CRUD[T, ID] {
  def insert(t: T)
  def findById(id: ID): T
  def count(): ID
  def update(t: T)
  def delete(t: T)
}