import scala.collection.immutable._

sealed trait Queue[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue[T]
   // # Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): Queue[T]
  def head: Option[T]
  // # for debugging
  def printQ: Unit
}

//Using 2 immutable list to implement this queue
//This is inspired from List class functioning as a stack
//Since scala List class is optimal for LIFO/stack like pattern , then we can implement queue with using 2 List/stacks
class QueueImpl[T](private val inputLst: List[T], private val outputLst: List[T]) extends Queue[T] {
  def this() = this(Queue.empty[T], Queue.empty[T])

  override def isEmpty: Boolean = inputLst.isEmpty && outputLst.isEmpty

  override def enQueue(t: T): Queue[T] = {
    val newLst = t :: inputLst
    new QueueImpl[T](newLst, outputLst)
  }

  override def deQueue(): Queue[T] = {
    if(this.isEmpty) throw new RuntimeException("The queue is empty")
    else if(outputLst.isEmpty){
      new QueueImpl(Queue.empty[T],  inputLst.reverse.tail)
    } else {
      new QueueImpl[T](inputLst, outputLst.tail)
    }
  }

  override def head: Option[T] = {
    if(this.isEmpty) None
    else if(outputLst.nonEmpty) outputLst.headOption
    else Option(inputLst.last)
  }

  override def printQ(): Unit = {
    print("Input List is: ")
    inputLst.foreach(x => print(s"$x ") )
    print("Output List is: ")
    outputLst.foreach(x => print(s"$x ") )
    println("")
  }
}

object Queue {
  type Queue[T] = scala.collection.immutable.List[T]
  def empty[T]: Queue[T] = List.empty[T]
}
