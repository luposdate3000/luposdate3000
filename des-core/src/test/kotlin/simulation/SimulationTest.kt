package simulation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock: Long = 0
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val startClock = Simulation.clock
        Simulation.initialize(arrayListOf(EmptyEntityStub(), EmptyEntityStub()))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, startClock)
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `sent data equals the received data`() {
        val data = 5
        val delay: Long = 18
        var receivingEntity: Entity? = null
        var actualData: Int? = null
        var actualDestEntity: Entity? = null
        var actualSrcEntity: Entity? = null
        var actualEventTime: Long? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity!!, delay, data)
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }
        receivingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {
                actualData = event.data as Int
                actualDestEntity = event.destination
                actualSrcEntity = event.source
                actualEventTime = event.occurrenceTime
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(receivingEntity, sendingEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(delay, endClock)
        Assertions.assertEquals(0, Simulation.clock)
        Assertions.assertEquals(data, actualData)
        Assertions.assertEquals(receivingEntity, actualDestEntity)
        Assertions.assertEquals(sendingEntity, actualSrcEntity)
        Assertions.assertEquals(delay, actualEventTime)
    }

    @Test
    fun `clock equals to the time of Occurrence of the last event`() {
        val firstDelay: Long = 4
        val secondDelay: Long = 5
        val thirdDelay: Long = 499
        var receivingEntity: Entity? = null
        var actualFirstClock: Long? = null
        var actualSecondClock: Long? = null
        var actualThirdClock: Long? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity!!, firstDelay, 1)
                this.sendEvent(receivingEntity!!, secondDelay, 2)
                this.sendEvent(receivingEntity!!, thirdDelay, 3)
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }

        receivingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {
                when(event.data) {
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
        val entityA = object : Entity() {
            override fun startUpEntity() {
                entityAIsCalled = true
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun startUpEntity() {
                entityBIsCalled = true
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun startUpEntity() {
                entityCIsCalled = true
            }
            override fun processEvent(event: Event) {}
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
        val entityA = object : Entity() {
            override fun startUpEntity() {}
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {
                entityAIsCalled = true
            }
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun startUpEntity() {}
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {
                entityBIsCalled = true
            }
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun startUpEntity() {}
            override fun processEvent(event: Event) {}
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
        val firstDelay: Long = 4
        val responseDelay: Long = 5
        var isResponseReceived = false
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(respondingEntity!!, firstDelay,null)
            }
            override fun processEvent(event: Event) {
               isResponseReceived = event.data == 2
            }
            override fun shutDownEntity() {}
        }

        respondingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {
                this.sendEvent(event.source, responseDelay,2)
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
        val delay: Long = 4
        var processCounter = 0
        val expectedProcessCounter = 1
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(respondingEntity!!, delay,null)
            }
            override fun processEvent(event: Event) {
                this.sendEvent(event.source, delay,null)
            }
            override fun shutDownEntity() {}
        }

        respondingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {
                this.sendEvent(event.source, delay,null)
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
    fun `beBusy() do lead to a call of processEvent()`() {
        val delay: Long = 4
        var processCounter = 0
        val expectedProcessCounter = 1

        val busyEntity = object : Entity() {
            override fun startUpEntity() {
                this.beBusy(delay)
            }
            override fun processEvent(event: Event) {
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
    fun `state is busy until busyDuration ends`() {
        val busyDuration: Long = 4
        var endState: Entity.State? = null
        var startState: Entity.State? = null
        val busyEntity = object : Entity() {
            override fun startUpEntity() {
                this.beBusy(busyDuration)
                startState = this.currentState
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {
                endState = this.currentState
            }
        }
        Simulation.initialize(arrayListOf(busyEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(busyDuration, endClock)
        Assertions.assertEquals(Entity.State.BUSY, startState)
        Assertions.assertEquals(Entity.State.RUNNABLE, endState)
    }

    @Test
    fun `busy entity do not process while being busy`() {
        val delay: Long = 4
        val busyDuration: Long = 100
        var processCounter = 0
        val eventType = 3
        var eventProcessedAt: Long= 0
        val expectedProcessCounter = 2

        val busyEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(this, delay, eventType)
                this.beBusy(busyDuration)
            }
            override fun processEvent(event: Event) {
                processCounter++
                if(event.data == eventType) {
                    eventProcessedAt = Simulation.clock
                }
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(busyEntity))
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(busyDuration, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
        Assertions.assertEquals(busyDuration, eventProcessedAt)
    }

    @Test
    fun `event is processed when delay equals clock`() {
        val delay: Long = 58
        val eventType = 3
        var eventProcessedAt: Long = 0

        val busyEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(this, delay, eventType)
                this.sendEvent(this, delay - 1, 0)
                this.sendEvent(this, delay + 1, 0)
            }
            override fun processEvent(event: Event) {
                if(event.data == eventType) {
                    eventProcessedAt = Simulation.clock
                }
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(busyEntity))
        Simulation.runSimulation()
        Assertions.assertEquals(delay, eventProcessedAt)
    }

    @Test
    fun `check default end time`() {
        Assertions.assertEquals(Long.MAX_VALUE, Simulation.maxClock)
    }

    @Test
    fun `simulation with maxClock equals clock`() {
        val maxClock: Long = 0
        var receivingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(receivingEntity!!, 4,1)
            }
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }
        receivingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {}
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(receivingEntity, sendingEntity), maxClock)
        val actualClock = Simulation.runSimulation()
        Assertions.assertEquals(0, Simulation.maxClock)
        Assertions.assertEquals(0, actualClock)
    }

    @Test
    fun `recursive sending events until maxClock is reached`() {
        val delay: Long = 1
        val maxClock: Long = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun startUpEntity() {
                this.sendEvent(respondingEntity!!, delay,null)
            }
            override fun processEvent(event: Event) {
                this.sendEvent(event.source, delay,null)
            }
            override fun shutDownEntity() {}
        }

        respondingEntity = object : Entity() {
            override fun startUpEntity(){}
            override fun processEvent(event: Event) {
                this.sendEvent(event.source, delay,null)
            }
            override fun shutDownEntity() {}
        }
        Simulation.initialize(arrayListOf(respondingEntity, sendingEntity), maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

}