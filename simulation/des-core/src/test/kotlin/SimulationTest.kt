import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock: Long = 0
        val sim = Simulation(emptyList())
        sim.start()
        Assertions.assertEquals(startClock, sim.currentClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val sim = Simulation(arrayListOf(EntityStub(), EntityStub()))
        val startClock = sim.currentClock
        sim.start()
        Assertions.assertEquals(0, startClock)
        Assertions.assertEquals(startClock, sim.currentClock)
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
                this.scheduleEvent(receivingEntity!!, delay, data)
            }
            override fun onSteadyState() {}
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
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(receivingEntity, sendingEntity))
        sim.start()

        Assertions.assertEquals(delay, sim.currentClock)
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
                this.scheduleEvent(receivingEntity!!, firstDelay, 1)
                this.scheduleEvent(receivingEntity!!, secondDelay, 2)
                this.scheduleEvent(receivingEntity!!, thirdDelay, 3)
            }
            override fun onSteadyState() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }

        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                when(event.data) {
                    1 -> {
                        actualFirstClock = simulation.currentClock
                    }
                    2 -> {
                        actualSecondClock = simulation.currentClock
                    }
                    3 -> {
                        actualThirdClock = simulation.currentClock
                    }
                }
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(receivingEntity, sendingEntity))
        sim.start()

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
            override fun onSteadyState() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {
                entityBIsCalled = true
            }
            override fun onSteadyState() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {
                entityCIsCalled = true
            }
            override fun onSteadyState() {}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(entityA, entityB, entityC))
        sim.start()

        Assertions.assertEquals(true, entityAIsCalled)
        Assertions.assertEquals(true, entityBIsCalled)
        Assertions.assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `onSteadyState is not called when it was not set`() {
        var entityAIsCalled = false
        val entityA = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() { entityAIsCalled = true }
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(entityA))
        sim.start()

        Assertions.assertEquals(false, entityAIsCalled)
    }

    @Test
    fun `all entities are called at the end`() {

        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityAIsCalled = true
            }
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityBIsCalled = true
            }
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityCIsCalled = true
            }
        }
        val sim = Simulation(arrayListOf(entityA, entityB, entityC))
        sim.start()

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
                this.scheduleEvent(respondingEntity!!, firstDelay,"dummy data")
            }
            override fun onEvent(event: Event) {
               isResponseReceived = event.data == 2
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, responseDelay,2)
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(arrayListOf(respondingEntity, sendingEntity))
        sim.start()

        Assertions.assertEquals(firstDelay + responseDelay, sim.currentClock)
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
                this.scheduleEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
                this.terminate()
                processCounter++
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(arrayListOf(respondingEntity, sendingEntity))
        sim.start()

        Assertions.assertEquals(delay * 3, sim.currentClock)
        Assertions.assertEquals(expectedProcessCounter, processCounter)
    }


    @Test
    fun `event is processed when delay equals clock`() {
        val delay: Long = 58
        val eventType = 3
        var eventProcessedAt: Long = 0

        val busyEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(this, delay, eventType)
                this.scheduleEvent(this, delay - 1, 0)
                this.scheduleEvent(this, delay + 1, 0)
            }
            override fun onEvent(event: Event) {
                if(event.data == eventType) {
                    eventProcessedAt = simulation.currentClock
                }
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(arrayListOf(busyEntity))
        sim.start()

        Assertions.assertEquals(delay, eventProcessedAt)
    }

    @Test
    fun `check default end time`() {
        val sim = Simulation(arrayListOf())
        Assertions.assertEquals(Long.MAX_VALUE, sim.maxClock)
    }

    @Test
    fun `simulation with maxClock equals clock`() {
        val maxClock: Long = 0
        var receivingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(receivingEntity!!, 4,1)
            }
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(receivingEntity, sendingEntity))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(maxClock, sim.currentClock)
    }

    @Test
    fun `recursive sending events until maxClock is reached`() {
        val delay: Long = 1
        val maxClock: Long = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(respondingEntity, sendingEntity))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(maxClock, sim.currentClock)
    }

    @Test
    fun `recursive sending events until stop is called`() {
        val delay: Long = 1
        val maxEventNumber = 100
        var currentProcessedEventCounter = 0
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                if(simulation.processedEventCounter >= maxEventNumber) {
                    currentProcessedEventCounter = simulation.processedEventCounter
                    simulation.stop()
                }
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(arrayListOf(respondingEntity, sendingEntity))
        sim.start()

        Assertions.assertEquals(currentProcessedEventCounter, sim.processedEventCounter)
    }

    @Test
    fun `set multiple timer`() {
        val timerDelay1: Long = 14
        val timerDelay2: Long = 22
        val timerDelay3: Long = 37

        var timer1Result: Long = 0
        var timer2Result: Long = 0
        var timer3Result: Long = 0

        val entity = object : Entity() {
            val timer1 = object : ITimerExpired {
                override fun onExpire() {
                    timer1Result = simulation.currentClock
                }
            }
            val timer2 = object : ITimerExpired {
                override fun onExpire() {
                    timer2Result = simulation.currentClock
                }
            }
            val timer3 = object : ITimerExpired {
                override fun onExpire() {
                    timer3Result = simulation.currentClock
                }
            }
            override fun onStartUp() {
                this.setTimer(timerDelay1, timer1)
                this.setTimer(timerDelay2, timer2)
                this.setTimer(timerDelay3, timer3)
            }
            override fun onEvent(event: Event) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(arrayListOf(entity))
        sim.start()

        Assertions.assertEquals(timerDelay1, timer1Result)
        Assertions.assertEquals(timerDelay2, timer2Result)
        Assertions.assertEquals(timerDelay3, timer3Result)
    }


    @Test
    fun `entities are called when the simulation reaches steady state`() {

        lateinit var entityA: Entity
        lateinit var entityB: Entity
        val steadyStateAt: Long = 5
        val delay: Long = 15
        var entityAIsCalledAt: Long = 0
        var entityBIsCalledAt: Long = 0

        entityA = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(entityB, delay,"dummy data")
            }
            override fun onEvent(event: Event) {}
            override fun onSteadyState() { entityAIsCalledAt = simulation.currentClock }
            override fun onShutDown() {}
        }

        entityB = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {}
            override fun onSteadyState() { entityBIsCalledAt = simulation.currentClock }
            override fun onShutDown() {}
        }

        val sim = Simulation(arrayListOf(entityA, entityB))
        sim.steadyStateReachedAt(steadyStateAt)
        sim.start()

        Assertions.assertEquals(delay, sim.currentClock)
        Assertions.assertEquals(steadyStateAt, entityAIsCalledAt)
        Assertions.assertEquals(steadyStateAt, entityBIsCalledAt)

    }


}