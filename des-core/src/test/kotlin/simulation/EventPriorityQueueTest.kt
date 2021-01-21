package simulation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EventPriorityQueueTest {



    @Test
    fun `queue should peak() lowest time`() {
        val queue = EventPriorityQueue()
        val e1 = Event(1.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e2 = Event(2.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e4 = Event(4.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e5 = Event(5.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        queue.enqueue(e5)
        var head: Event = queue.peek()
        Assertions.assertEquals(e5, head)
        queue.enqueue(e4)
        head = queue.peek()
        Assertions.assertEquals(e4, head)
        queue.enqueue(e1)
        head = queue.peek()
        Assertions.assertEquals(e1, head)
        queue.enqueue(e2)
        head = queue.peek()
        Assertions.assertEquals(e1, head)
    }

    @Test
    fun `hasNext() is false`() {
        val queue = EventPriorityQueue()
        val isEmpty = ! queue.hasNext()
        Assertions.assertTrue(isEmpty)
    }

    @Test
    fun `hasNext() is true`() {
        val queue = EventPriorityQueue()
        val e1 = Event(1.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        queue.enqueue(e1)
        val isNotEmpty = queue.hasNext()
        Assertions.assertTrue(isNotEmpty)
    }

    @Test
    fun `dequeue() return and remove lowest time`() {
        val queue = EventPriorityQueue()
        val e1 = Event(1.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e2 = Event(2.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e3 = Event(2.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e4 = Event(4.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        val e5 = Event( 5.0, EmptyEntityStub(), EmptyEntityStub(), 0, null)
        queue.enqueue(e5)
        queue.enqueue(e3)
        queue.enqueue(e4)
        queue.enqueue(e1)
        queue.enqueue(e2)
        var head: Event = queue.dequeue()
        Assertions.assertEquals(e1, head)
        head = queue.dequeue()
        Assertions.assertEquals(e2, head)
        head = queue.dequeue()
        Assertions.assertEquals(e3, head)
        head = queue.dequeue()
        Assertions.assertEquals(e4, head)
        head = queue.dequeue()
        Assertions.assertEquals(e5, head)
        Assertions.assertFalse(queue.hasNext())
    }

}