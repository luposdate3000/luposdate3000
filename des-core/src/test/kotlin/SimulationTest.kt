import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock: Long = 0
        val endClock = Simulation.start(emptyList(),LoggerStub())
        Assertions.assertEquals(startClock, endClock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val startClock = Simulation.clock
        val endClock = Simulation.start(arrayListOf(EntityStub(), EntityStub()), LoggerStub())
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
                this.scheduleEvent(receivingEntity!!, delay, data)
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
        val endClock = Simulation.start(arrayListOf(receivingEntity, sendingEntity),LoggerStub())
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
                this.scheduleEvent(receivingEntity!!, firstDelay, 1)
                this.scheduleEvent(receivingEntity!!, secondDelay, 2)
                this.scheduleEvent(receivingEntity!!, thirdDelay, 3)
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
        Simulation.start(arrayListOf(receivingEntity, sendingEntity),LoggerStub())
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
        Simulation.start(arrayListOf(entityA, entityB, entityC),LoggerStub())
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
        Simulation.start(arrayListOf(entityA, entityB, entityC),LoggerStub())
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
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, responseDelay,2)
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),LoggerStub())
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
                this.scheduleEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
                this.terminate()
                processCounter++
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),LoggerStub())
        Assertions.assertEquals(delay * 3, endClock)
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
                    eventProcessedAt = Simulation.clock
                }
            }
            override fun onShutDown() {}
        }
        Simulation.start(arrayListOf(busyEntity),LoggerStub())
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
                this.scheduleEvent(receivingEntity!!, 4,1)
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }
        val actualClock = Simulation.start(arrayListOf(receivingEntity, sendingEntity),LoggerStub(), maxClock)
        Assertions.assertEquals(maxClock, actualClock)
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
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),LoggerStub(), maxClock)
        Assertions.assertEquals(maxClock, endClock)
    }

    @Test
    fun `recursive sending events until stop is called`() {
        val delay: Long = 1
        val maxEventNumber = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, delay,"dummy data")
            }
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
            }
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp(){}
            override fun onEvent(event: Event) {
                this.scheduleEvent(event.source, delay,"dummy data")
                if(Simulation.eventCounter >= maxEventNumber)
                    Simulation.stop()
            }
            override fun onShutDown() {}
        }
        val endClock = Simulation.start(arrayListOf(respondingEntity, sendingEntity),LoggerStub())
        Assertions.assertEquals(maxEventNumber.toLong()-1, endClock)
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
                    timer1Result = Simulation.clock
                }
            }
            val timer2 = object : ITimerExpired {
                override fun onExpire() {
                    timer2Result = Simulation.clock
                }
            }
            val timer3 = object : ITimerExpired {
                override fun onExpire() {
                    timer3Result = Simulation.clock
                }
            }
            override fun onStartUp() {
                this.setTimer(timerDelay1, timer1)
                this.setTimer(timerDelay2, timer2)
                this.setTimer(timerDelay3, timer3)
            }
            override fun onEvent(event: Event) {}
            override fun onShutDown() {}
        }

        Simulation.start(arrayListOf(entity), LoggerStub())
        Assertions.assertEquals(timerDelay1, timer1Result)
        Assertions.assertEquals(timerDelay2, timer2Result)
        Assertions.assertEquals(timerDelay3, timer3Result)
    }



}