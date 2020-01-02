
object Main extends App {

  println("Hello world!")
  var q: Queue[Int] = new QueueImpl[Int](Queue.empty)
  val q1 = q.enQueue(1)
  val q2 = q1.enQueue(2)
  val q3 = q2.enQueue(3)
  q3.printQ
}
