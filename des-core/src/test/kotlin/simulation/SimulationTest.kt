package simulation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    class EntityStub(name: String) : Entity(name) {
        override fun startUpEntity() {
        }
        override fun processEvent(ev: Event) {
        }
        override fun shutDownEntity() {
        }
        override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
            return 0.0
        }
    }

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock = Simulation.clock
        Simulation.runSimulation()
        val endClock = Simulation.clock
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val startClock = Simulation.clock
        Simulation.addEntityToSimulation(EntityStub(""))
        Simulation.addEntityToSimulation(EntityStub(""))
        Simulation.addEntityToSimulation(EntityStub(""))
        Simulation.runSimulation()
        val endClock = Simulation.clock
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `sent data equals the received data`() {
        var receivedDestinationEntity: Entity = EntityStub("")
        var receivedEventType: EventType = EventTypeStub()
        var receivedData = 4
        var receivedInternalEventType = -1
        var receivedSourceEntity: Entity = EntityStub("")
        var receivedTime = 0.0
        val receivingEntity = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                receivedData = ev.data as Int
                receivedDestinationEntity = ev.destinationEntity
                receivedEventType = ev.eventType
                receivedInternalEventType = ev.internalEventType
                receivedTime = ev.time
                receivedSourceEntity = ev.sourceEntity
            }
            override fun shutDownEntity() {}
            override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
                return 0.0
            }
        }
        val delay = 18.33
        val sentEventType = object : EventType {
            override fun getEventType(): Int {
                return 77
            }
        }
        val sentData = 5

        val sendingEntity = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity, sentEventType, sentData)
            }
            override fun processEvent(ev: Event) {
            }
            override fun shutDownEntity() {}
            override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
                return delay
            }
        }
        Simulation.addEntityToSimulation(receivingEntity)
        Simulation.addEntityToSimulation(sendingEntity)
        Simulation.runSimulation()

        Assertions.assertEquals(delay, Simulation.clock)
        Assertions.assertEquals(sentData, receivedData)
        Assertions.assertEquals(sentEventType, receivedEventType)
        Assertions.assertEquals(sendingEntity, receivedSourceEntity)
        Assertions.assertEquals(delay, receivedTime)
        Assertions.assertEquals(Event.SEND_EVENT, receivedInternalEventType)
        Assertions.assertEquals(receivingEntity, receivedDestinationEntity)
    }

    @Test
    fun `two entities sending another and increasing the clock`() {

        var entityAReceivedEventsCount = 0
        var entityBReceivedEventsCount = 0
        val entityASentEventNumber = 200
        val entityASentDelay = 19.33
        val entityBSentDelay = 8.1
        val entityA = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                entityAReceivedEventsCount++
                for(x in 1..entityASentEventNumber) {
                    this.sendEvent(ev.sourceEntity, EventTypeStub(), null)
                }
            }
            override fun shutDownEntity() {}
            override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
                return entityASentDelay
            }
        }

        val entityB = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(entityA, EventTypeStub(), null)
            }
            override fun processEvent(ev: Event) {
                entityBReceivedEventsCount++
            }
            override fun shutDownEntity() {}
            override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
                return entityBSentDelay
            }
        }
        Simulation.addEntityToSimulation(entityA)
        Simulation.addEntityToSimulation(entityB)
        Simulation.runSimulation()
        val expectedClock = entityBSentDelay + entityASentDelay
        Assertions.assertEquals(expectedClock, Simulation.clock)
        Assertions.assertEquals(entityASentEventNumber, entityBReceivedEventsCount)
        Assertions.assertEquals(1, entityAReceivedEventsCount)

    }



//    @Test
//    fun `send event to not existing entity`() {
//        var x: Int
//        val sendingEntity = object : Entity("") {
//            override fun startUpEntity(){}
//            override fun processEvent(ev: Event) {
//                x = 5
//            }
//            override fun shutDownEntity() {}
//            override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
//                return 0.0
//            }
//        }
//        sendingEntity.se
//        val e1 = Event(Event.SEND_EVENT, 1.0, , EntityStub(""), EventTypeStub(), null)
//
//        val startClock = Simulation.clock
//        Simulation.runSimulation()
//        val endClock = Simulation.clock
//        Assertions.assertEquals(startClock, endClock)
//    }
}