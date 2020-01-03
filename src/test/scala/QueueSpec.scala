import org.scalatest._

//Test cases for the immutable queue
class QueueSpec extends FlatSpec {

  "A queue" should "have first in first out order" in {
    val initQueue = new QueueImpl[Int]()
    val q2 = initQueue.enQueue(1)
    val q3 = q2.enQueue(2)
    assert(q2.head == Some(1))
    val q4 = q3.deQueue()
    assert(q4.head == Some(2))
  }

  it should "make sure all the queues are immutable " in {
    val initQueue = new QueueImpl[Int]()
    val q2 = initQueue.enQueue(2).enQueue(3)
    assert(initQueue.isEmpty == true)
    assert(q2.head == Some(2))
    val q3 = q2.deQueue()
    assert(q2.head == Some(2))
    assert(q3.head == Some(3))
  }

  it should "get the head of a queue without dequeue operation" in{
    val initQueue = new QueueImpl[Int]()
    val q1 = initQueue.enQueue(1).enQueue(2).enQueue(3)
    assert(q1.head == Some(1))
  }

  it should "empty a queue and then populate it again" in {
    val initQueue = new QueueImpl[Int]()
    val q1 = initQueue.enQueue(-1).enQueue(-2).enQueue(-3)
    val q2 = q1.deQueue().deQueue().deQueue()
    assert(q2.isEmpty)
    val q3 = q2.enQueue(100).enQueue(200).enQueue(300)
    assert(q3.head == Some(100))
  }

  it should "return None for the head on empty queue" in {
    val emptyQueue = new QueueImpl[Int]()
    assert(emptyQueue.head == None)
  }

  it should "throw Runtime Exception if an empty queue is dequeue-d" in{
    val emptyQueue = new QueueImpl[Int]()
    assertThrows[RuntimeException] {
      emptyQueue.deQueue()
    }
  }

  it should "verify queue is empty with its method" in {
    val emptyQueue = new QueueImpl[Int]()
    assert(emptyQueue.isEmpty == true)
  }
}
