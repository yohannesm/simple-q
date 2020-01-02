sealed trait Queue[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue[T]
   // # Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): Queue[T]
  def head: Option[T]
}

class QueueImpl[T] extends Queue[T] {
  override def isEmpty: Boolean = ???

  override def enQueue(t: T): Queue[T] = ???

  override def deQueue(): Queue[T] = ???

  override def head: Option[T] = ???
}

object Queue {
  def empty[T]: Queue[T] = ???
}
