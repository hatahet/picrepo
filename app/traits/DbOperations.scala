package traits

trait DbOperations[T, ID] {
  def insert(t: T)
  def findById(id: ID): T
  def count(): ID
  def update(t: T)
  def delete(t: T)
}
