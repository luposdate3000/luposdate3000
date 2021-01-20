package simulation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock = 0.0
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val startClock = Simulation.clock
        Simulation.initialize(arrayListOf(EmptyEntityStub(""), EmptyEntityStub("")))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0.0, startClock)
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `sent data equals the received data`() {
        val eventType: EventType = EmptyEventTypeStub()
        val data = 5
        val delay = 18.33
        var receivingEntity: Entity? = null
        var actualData: Int? = null
        var actualDestEntity: Entity? = null
        var actualSrcEntity: Entity? = null
        var actualInternalEventType: Int? = null
        var actualEventTime: Double? = null
        var actualEventType: EventType? = null

        val sendingEntity = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity!!, delay, eventType, data)
            }
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {}
        }
        receivingEntity = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                actualData = ev.data as Int
                actualDestEntity = ev.destination
                actualSrcEntity = ev.source
                actualInternalEventType = ev.internalTag
                actualEventTime = ev.occurrenceTime
                actualEventType = ev.type!!
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(receivingEntity, sendingEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(delay, endClock)
        Assertions.assertEquals(0.0, Simulation.clock)
        Assertions.assertEquals(data, actualData)
        Assertions.assertEquals(receivingEntity, actualDestEntity)
        Assertions.assertEquals(sendingEntity, actualSrcEntity)
        Assertions.assertEquals(Event.SEND_EVENT, actualInternalEventType)
        Assertions.assertEquals(delay, actualEventTime)
        Assertions.assertEquals(eventType, actualEventType)
    }

    @Test
    fun `clock equals to the time of Occurrence of the last event`() {
        val firstDelay = 4.99
        val secondDelay = 5.0
        val thirdDelay = 499.1
        var receivingEntity: Entity? = null
        var actualFirstClock: Double? = null
        var actualSecondClock: Double? = null
        var actualThirdClock: Double? = null

        val sendingEntity = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity!!, firstDelay, EmptyEventTypeStub(), 1)
                this.sendEvent(receivingEntity!!, secondDelay, EmptyEventTypeStub(), 2)
                this.sendEvent(receivingEntity!!, thirdDelay, EmptyEventTypeStub(), 3)
            }
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {}
        }

        receivingEntity = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                when(ev.data) {
                    1 -> {
                        actualFirstClock = Simulation.clock
                    }
                    2 -> {
                        actualSecondClock = Simulation.clock
                    }
                    3 -> {
                        actualThirdClock = Simulation.clock
                    }
                }
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(receivingEntity, sendingEntity))
        Simulation.runSimulation()
        Assertions.assertEquals(firstDelay, actualFirstClock)
        Assertions.assertEquals(secondDelay, actualSecondClock)
        Assertions.assertEquals(thirdDelay, actualThirdClock)
    }

    @Test
    fun `all entities are called at the beginning`() {

        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity("A") {
            override fun startUpEntity() {
                entityAIsCalled = true
            }
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {}
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity("B") {
            override fun startUpEntity() {
                entityBIsCalled = true
            }
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {}
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity("C") {
            override fun startUpEntity() {
                entityCIsCalled = true
            }
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(entityA, entityB, entityC))
        Simulation.runSimulation()
        Assertions.assertEquals(true, entityAIsCalled)
        Assertions.assertEquals(true, entityBIsCalled)
        Assertions.assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `all entities are called at the end`() {

        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity("A") {
            override fun startUpEntity() {}
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {
                entityAIsCalled = true
            }
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity("B") {
            override fun startUpEntity() {}
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {
                entityBIsCalled = true
            }
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity("C") {
            override fun startUpEntity() {}
            override fun processEvent(ev: Event) {}
            override fun shutDownEntity() {
                entityCIsCalled = true
            }
        }
        Simulation.initialize(arrayListOf(entityA, entityB, entityC))
        Simulation.runSimulation()
        Assertions.assertEquals(true, entityAIsCalled)
        Assertions.assertEquals(true, entityBIsCalled)
        Assertions.assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `An entity responds to an event with an event`() {
        val firstDelay = 4.2
        val responseDelay = 5.999
        var isResponseReceived = false
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(respondingEntity!!, firstDelay, EmptyEventTypeStub(), null)
            }
            override fun processEvent(ev: Event) {
               isResponseReceived = ev.data == 2
            }
            override fun shutDownEntity() {}
        }

        respondingEntity = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                this.sendEvent(ev.source, responseDelay, EmptyEventTypeStub(), 2)
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(respondingEntity, sendingEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(firstDelay + responseDelay, endClock)
        Assertions.assertTrue(isResponseReceived)
    }

    @Test
    fun `Terminated entity does not receive a message`() {
        val delay = 4.2
        var processCounter = 0
        val expectedProcessCounter = 1
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity("") {
            override fun startUpEntity() {
                this.sendEvent(respondingEntity!!, delay, EmptyEventTypeStub(), null)
            }
            override fun processEvent(ev: Event) {
                this.sendEvent(ev.source, delay, EmptyEventTypeStub(), null)
            }
            override fun shutDownEntity() {}
        }

        respondingEntity = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                this.sendEvent(ev.source, delay, EmptyEventTypeStub(), null)
                this.terminate()
                processCounter++
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(respondingEntity, sendingEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(delay * 3, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `being busy is also part of the clock`() {
        val delay = 4.2
        var processCounter = 0
        val expectedProcessCounter = 0

        val busyEntity = object : Entity("") {
            override fun startUpEntity() {
                this.beBusy(delay)
            }
            override fun processEvent(ev: Event) {
                processCounter++
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(busyEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(delay, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `being busy delays further sending`() {
        val delay = 4.2
        var processCounter = 0
        val expectedProcessCounter = 1
        val expectedEndClock = delay + delay

        val busyEntity = object : Entity("") {
            override fun startUpEntity() {
                this.beBusy(delay)
                this.sendEvent(this, delay, EmptyEventTypeStub(), null)
            }
            override fun processEvent(ev: Event) {
                processCounter++
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(busyEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(expectedEndClock, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `Send and be busy several times in a row`() {
        val delay = 4.2
        var processCounterA = 0
        val expectedProcessCounterA = 0
        var processCounterB = 0
        val expectedProcessCounterB = 3
        var entityB: Entity? = null
        val count = 3
        val expectedClock = count * (delay + delay)

        val entityA = object : Entity("") {
            override fun startUpEntity() {
                for(i in 1..count) {
                    this.beBusy(delay)
                    this.sendEvent(entityB!!, delay, EmptyEventTypeStub(), null)
                }
            }
            override fun processEvent(ev: Event) {
                processCounterA++
            }
            override fun shutDownEntity() {}
        }

        entityB = object : Entity("") {
            override fun startUpEntity(){}
            override fun processEvent(ev: Event) {
                processCounterB++
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(entityB, entityA))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(expectedClock, endClock)
        Assertions.assertEquals(expectedProcessCounterA, processCounterA)
        Assertions.assertEquals(expectedProcessCounterB, processCounterB)
    }

//    @Test
//    fun `Send and be busy several times in a row`() {
//        val delay = 4.2
//        var processCounterA = 0
//        val expectedProcessCounterA = 1
//        var processCounterB = 0
//        val expectedProcessCounterB = 1
//        var entityB: Entity? = null
//        val count = 3
//        val expectedClock =
//
//        val entityA = object : Entity("") {
//            override fun startUpEntity() {
//                for(i in 1..count) {
//                    this.beBusy(delay)
//                    this.sendEvent(entityB!!, delay, EmptyEventTypeStub(), null)
//                }
//            }
//            override fun processEvent(ev: Event) {
//                processCounterA++
//            }
//            override fun shutDownEntity() {}
//        }
//
//        entityB = object : Entity("") {
//            override fun startUpEntity(){}
//            override fun processEvent(ev: Event) {
//                this.sendEvent(ev.sourceEntity, delay, EmptyEventTypeStub(), null)
//                this.beBusy(delay)
//                processCounterB++
//            }
//            override fun shutDownEntity() {}
//        }
//        Simulation.initialize(arrayListOf(entityB, sendingEntity))
//        val endClock = Simulation.runSimulation()
//        Assertions.assertEquals(delay * 3, endClock)
//        Assertions.assertEquals(expectedProcessCounterA, processCounterA)
//    }

}