import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock: Long = 0
        val endClock = Simulation.start(emptyList(),CallbackStub())
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val startClock = Simulation.clock
        val endClock = Simulation.start(arrayListOf(EntityStub(), EntityStub()), CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(receivingEntity!!, delay, data)
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                actualData = event.data as Int
                actualDestEntity = event.destination
                actualSrcEntity = event.source
                actualEventTime = event.occurrenceTime
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(receivingEntity, sendingEntity),CallbackStub())
        Assertions.assertEquals(delay, endClock)
        Assertions.assertEquals(0, Simulation.clock)
        Assertions.assertEquals(data, actualData)
        Assertions.assertEquals(receivingEntity, actualDestEntity)
        Assertions.assertEquals(sendingEntity, actualSrcEntity)
        Assertions.assertEquals(delay, actualEventTime)
    }

    @Test
    fun `clock equals to the time of occurrence of the last event`() {
        val firstDelay: Long = 4
        val secondDelay: Long = 5
        val thirdDelay: Long = 499
        var receivingEntity: Entity? = null
        var actualFirstClock: Long? = null
        var actualSecondClock: Long? = null
        var actualThirdClock: Long? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.sendEvent(receivingEntity!!, firstDelay, 1)
                this.sendEvent(receivingEntity!!, secondDelay, 2)
                this.sendEvent(receivingEntity!!, thirdDelay, 3)
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }

        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
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
            override fun onShutDown() {}
        }
        Simulation.start(arrayListOf(receivingEntity, sendingEntity),CallbackStub())
        Assertions.assertEquals(firstDelay, actualFirstClock)
        Assertions.assertEquals(secondDelay, actualSecondClock)
        Assertions.assertEquals(thirdDelay, actualThirdClock)
    }

    @Test
    fun `all entities are called at the beginning`() {

        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity() {
            override fun onStartUp() {
                entityAIsCalled = true
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {
                entityBIsCalled = true
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {
                entityCIsCalled = true
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        Simulation.start(arrayListOf(entityA, entityB, entityC),CallbackStub())
        Assertions.assertEquals(true, entityAIsCalled)
        Assertions.assertEquals(true, entityBIsCalled)
        Assertions.assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `all entities are called at the end`() {

        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {
                entityAIsCalled = true
            }
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {
                entityBIsCalled = true
            }
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {
                entityCIsCalled = true
            }
        }
        Simulation.start(arrayListOf(entityA, entityB, entityC),CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(respondingEntity!!, firstDelay,"dummy data")
            }
            override fun onEvent(event: Event) {
               isResponseReceived = event.data == 2
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, responseDelay,2)
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
                this.terminate()
                processCounter++
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),CallbackStub())
        Assertions.assertEquals(delay * 3, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `beBusy() do lead to a call of processEvent()`() {
        val delay: Long = 4
        var processCounter = 0
        val expectedProcessCounter = 1

        val busyEntity = object : Entity() {
            override fun onStartUp() {
                this.beBusy(delay)
            }
            override fun onEvent(event: Event) {
                processCounter++
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(busyEntity),CallbackStub())
        Assertions.assertEquals(delay, endClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `state is busy until busyDuration ends`() {
        val busyDuration: Long = 4
        var endState: Entity.State? = null
        var startState: Entity.State? = null
        val busyEntity = object : Entity() {
            override fun onStartUp() {
                this.beBusy(busyDuration)
                startState = this.currentState
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {
                endState = this.currentState
            }
        }
        val endClock = Simulation.start(arrayListOf(busyEntity),CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(this, delay, eventType)
                this.beBusy(busyDuration)
            }
            override fun onEvent(event: Event) {
                processCounter++
                if(event.data == eventType) {
                    eventProcessedAt = Simulation.clock
                }
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(busyEntity),CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(this, delay, eventType)
                this.sendEvent(this, delay - 1, 0)
                this.sendEvent(this, delay + 1, 0)
            }
            override fun onEvent(event: Event) {
                if(event.data == eventType) {
                    eventProcessedAt = Simulation.clock
                }
            }
            override fun onShutDown() {}
        }
        Simulation.start(arrayListOf(busyEntity),CallbackStub())
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
            override fun onStartUp() {
                this.sendEvent(receivingEntity!!, 4,1)
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        val actualClock = Simulation.start(arrayListOf(receivingEntity, sendingEntity),CallbackStub(), maxClock)
        Assertions.assertEquals(maxClock, actualClock)
    }

    @Test
    fun `recursive sending events until maxClock is reached`() {
        val delay: Long = 1
        val maxClock: Long = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.sendEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),CallbackStub(), maxClock)
        Assertions.assertEquals(maxClock, endClock)
    }

    @Test
    fun `recursive sending events until stop is called`() {
        val delay: Long = 1
        val maxEventNumber = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.sendEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.sendEvent(event.source, delay,"dummy data")
                if(Simulation.eventCounter >= maxEventNumber)
                    Simulation.stop()
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),CallbackStub())
        Assertions.assertEquals(maxEventNumber.toLong()-1, endClock)
    }

}