package traits

trait DbOperations[T, ID] {
  def findById(id: ID): T
  def count(): ID
  def update(t: T)
}
