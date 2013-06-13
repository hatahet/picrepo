package models

import java.sql.Timestamp

import scala.slick.driver.H2Driver.simple._

case class Tag(id: Option[Long], imageId: Long, value: String, addedTime: Timestamp)

object Tags extends Table[Tag]("TAG") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def imageId = column[Long]("image_id")
  def value = column[String]("value")
  def addedTime = column[Timestamp]("added_time")
  def * = id.? ~ imageId ~ value ~ addedTime <> (Tag, Tag.unapply _)
  def image = foreignKey("image_fk", imageId, Images)(_.id)
}
