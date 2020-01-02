import scala.collection.immutable
import scala.collection.immutable._

sealed trait Queue[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue[T]
   // # Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): Queue[T]
  def head: Option[T]
  def printQ: Unit
}

class QueueImpl[T](private val lst: List[T]) extends Queue[T] {
  //val lst: List[T] = scala.collection.immutable.List[T]()
  //var lst: List[T] = scala.collection.immutable.List[T]()

  override def isEmpty: Boolean = lst.isEmpty

  override def enQueue(t: T): Queue[T] = {
    new QueueImpl[T](t :: lst)
  }

  override def deQueue(): Queue[T] = {
    new QueueImpl[T](lst.dropRight(1))
  }

  override def head: Option[T] = {
    lst.headOption
  }

  override def printQ(): Unit = {
    print("Queue is: ")
    lst.foreach(x => print(s"$x ") )
    println(lst.isEmpty)
  }
}

object Queue {
  type Queue[T] = scala.collection.immutable.List[T]
  //def Queue[T](xs: T*) = List(xs: _*)
  def empty[T]: Queue[T] = List.empty[T]
}
