/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PriorityQueueTest {

    @Test
    fun `queue should peak() lowest time`() {
        val queue = EventPriorityQueueStub().getEventPriorityQueue()
        val e1 = Event(1, 1, EntityStub(), EntityStub(), "")
        val e2 = Event(1, 2, EntityStub(), EntityStub(), "")
        val e4 = Event(1, 4, EntityStub(), EntityStub(), "")
        val e5 = Event(1, 5, EntityStub(), EntityStub(), "")
        queue.enqueue(e5)
        var head: Event = queue.peek()
        assertEquals(e5, head)
        queue.enqueue(e4)
        head = queue.peek()
        assertEquals(e4, head)
        queue.enqueue(e1)
        head = queue.peek()
        assertEquals(e1, head)
        queue.enqueue(e2)
        head = queue.peek()
        assertEquals(e1, head)
    }

    @Test
    fun `hasNext() is false`() {
        val queue = EventPriorityQueueStub().getEventPriorityQueue()
        val isEmpty = !queue.hasNext()
        assertTrue(isEmpty)
    }

    @Test
    fun `hasNext() is true`() {
        val queue = EventPriorityQueueStub().getEventPriorityQueue()
        val e1 = Event(1, 1, EntityStub(), EntityStub(), "")
        queue.enqueue(e1)
        val isNotEmpty = queue.hasNext()
        assertTrue(isNotEmpty)
    }

    @Test
    fun `dequeue() return and remove lowest time`() {
        val queue = EventPriorityQueueStub().getEventPriorityQueue()
        val e1 = Event(1, 1, EntityStub(), EntityStub(), "")
        val e2 = Event(1, 2, EntityStub(), EntityStub(), "")
        val e3 = Event(1, 2, EntityStub(), EntityStub(), "")
        val e4 = Event(1, 4, EntityStub(), EntityStub(), "")
        val e5 = Event(1, 5, EntityStub(), EntityStub(), "")
        queue.enqueue(e5)
        queue.enqueue(e3)
        queue.enqueue(e4)
        queue.enqueue(e1)
        queue.enqueue(e2)
        var head: Event = queue.dequeue()
        assertEquals(e1, head)
        head = queue.dequeue()
        assertEquals(e2, head)
        head = queue.dequeue()
        assertEquals(e3, head)
        head = queue.dequeue()
        assertEquals(e4, head)
        head = queue.dequeue()
        assertEquals(e5, head)
        assertFalse(queue.hasNext())
    }

    @Test
    fun `after comparing occurrenceTime, sequenceNumber is compared`() {
        val queue = EventPriorityQueueStub().getEventPriorityQueue()
        val e1 = Event(10, 1, EntityStub(), EntityStub(), "")
        val e2 = Event(1, 2, EntityStub(), EntityStub(), "")
        val e3 = Event(2, 2, EntityStub(), EntityStub(), "")
        val e4 = Event(2, 3, EntityStub(), EntityStub(), "")
        val e5 = Event(1, 3, EntityStub(), EntityStub(), "")
        queue.enqueue(e5)
        queue.enqueue(e3)
        queue.enqueue(e4)
        queue.enqueue(e1)
        queue.enqueue(e2)
        assertEquals(e1, queue.dequeue())
        assertEquals(e2, queue.dequeue())
        assertEquals(e3, queue.dequeue())
        assertEquals(e5, queue.dequeue())
        assertEquals(e4, queue.dequeue())
        assertFalse(queue.hasNext())
    }
}
