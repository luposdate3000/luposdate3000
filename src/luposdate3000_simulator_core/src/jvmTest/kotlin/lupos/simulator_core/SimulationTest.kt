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
import kotlin.test.assertTrue

class SimulationTest {

    @Test
    fun `run without entities has no effect on clock`() {
        val startClock: Long = 0
        val sim = Simulation(emptyList())
        sim.startSimulation()
        assertEquals(startClock, sim.clock)
    }

    @Test
    fun `run without sending events has no effect on clock`() {
        val sim = Simulation(mutableListOf(EntityStub(), EntityStub()))
        val startClock = sim.clock
        sim.startSimulation()
        assertEquals(0, startClock)
        assertEquals(startClock, sim.clock)
    }

    @Test
    fun `sent data equals the received data`() {
        val data = 5
        val delay: Long = 18
        var receivingEntity: Entity? = null
        var actualData: Int? = null
        var actualSrcEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(receivingEntity!!, data, delay)
            }

            override fun onSteadyState() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                actualData = data as Int
                actualSrcEntity = source
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(receivingEntity, sendingEntity))
        sim.startSimulation()

        assertEquals(delay, sim.clock)
        assertEquals(data, actualData)
        assertEquals(sendingEntity, actualSrcEntity)
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
                this.scheduleEvent(receivingEntity!!, 1, firstDelay)
                this.scheduleEvent(receivingEntity!!, 2, secondDelay)
                this.scheduleEvent(receivingEntity!!, 3, thirdDelay)
            }

            override fun onSteadyState() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onShutDown() {}
        }

        receivingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                when (data) {
                    1 -> {
                        actualFirstClock = simulation.clock
                    }
                    2 -> {
                        actualSecondClock = simulation.clock
                    }
                    3 -> {
                        actualThirdClock = simulation.clock
                    }
                }
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(receivingEntity, sendingEntity))
        sim.startSimulation()

        assertEquals(firstDelay, actualFirstClock)
        assertEquals(secondDelay, actualSecondClock)
        assertEquals(thirdDelay, actualThirdClock)
    }

    @Test
    fun `all entities are called at the beginning`() {
        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity() {
            override fun onStartUp() {
                entityAIsCalled = true
            }

            override fun onSteadyState() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onShutDown() {}
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {
                entityBIsCalled = true
            }

            override fun onSteadyState() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onShutDown() {}
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {
                entityCIsCalled = true
            }

            override fun onSteadyState() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(entityA, entityB, entityC))
        sim.startSimulation()

        assertEquals(true, entityAIsCalled)
        assertEquals(true, entityBIsCalled)
        assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `onSteadyState is not called when it was not set`() {
        var entityAIsCalled = false
        val entityA = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {
                entityAIsCalled = true
            }

            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(entityA))
        sim.startSimulation()

        assertEquals(false, entityAIsCalled)
    }

    @Test
    fun `all entities are called at the end`() {
        var entityAIsCalled: Boolean? = null
        val entityA = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityAIsCalled = true
            }
        }
        var entityBIsCalled: Boolean? = null
        val entityB = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityBIsCalled = true
            }
        }
        var entityCIsCalled: Boolean? = null
        val entityC = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {
                entityCIsCalled = true
            }
        }
        val sim = Simulation(mutableListOf(entityA, entityB, entityC))
        sim.startSimulation()

        assertEquals(true, entityAIsCalled)
        assertEquals(true, entityBIsCalled)
        assertEquals(true, entityCIsCalled)
    }

    @Test
    fun `An entity responds to an event with an event`() {
        val firstDelay: Long = 4
        val responseDelay: Long = 5
        var isResponseReceived = false
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, "dummy data", firstDelay)
            }

            override fun onEvent(source: Entity, data: Any) {
                isResponseReceived = data == 2
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, 2, responseDelay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(mutableListOf(respondingEntity, sendingEntity))
        sim.startSimulation()

        val expected: Long = firstDelay + responseDelay
        assertEquals(expected, sim.clock)
        assertTrue(isResponseReceived)
    }

    @Test
    fun `Terminated entity does not receive a message`() {
        val delay: Long = 4
        var processCounter = 0
        val expectedProcessCounter = 1
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, "dummy data", delay)
            }

            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, "dummy data", delay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, "dummy data", delay)
                this.terminate()
                processCounter++
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(mutableListOf(respondingEntity, sendingEntity))
        sim.startSimulation()

        assertEquals((delay * 3), sim.clock)
        assertEquals(expectedProcessCounter, processCounter)
    }

    @Test
    fun `event is processed when delay equals clock`() {
        val delay: Long = 58
        val eventType = 3
        var eventProcessedAt: Long = 0

        val busyEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(this, eventType, delay)
                this.scheduleEvent(this, 0, delay - 1)
                this.scheduleEvent(this, 0, delay + 1)
            }

            override fun onEvent(source: Entity, data: Any) {
                if (data == eventType) {
                    eventProcessedAt = simulation.clock
                }
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(mutableListOf(busyEntity))
        sim.startSimulation()

        assertEquals(delay, eventProcessedAt)
    }

    @Test
    fun `check default end time`() {
        val sim = Simulation(mutableListOf())
        assertEquals(Long.MAX_VALUE, sim.maxClock)
    }

    @Test
    fun `simulation with maxClock equals clock`() {
        val maxClock: Long = 0
        var receivingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(receivingEntity!!, 1, 4)
            }

            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        receivingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(receivingEntity, sendingEntity))
        sim.maxClock = maxClock
        sim.startSimulation()

        assertEquals(maxClock, sim.clock)
    }

    @Test
    fun `recursive sending events until maxClock is reached`() {
        val delay: Long = 1
        val maxClock: Long = 100
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, "dummy data", delay)
            }

            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, "dummy data", delay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, "dummy data", delay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(respondingEntity, sendingEntity))
        sim.maxClock = maxClock
        sim.startSimulation()

        assertEquals(maxClock, sim.clock)
    }

    @Test
    fun `recursive sending events until stop is called`() {
        val delay: Long = 1
        val maxEventNumber = 100
        var currentProcessedEventCounter = 0
        var respondingEntity: Entity? = null

        val sendingEntity = object : Entity() {
            override fun onStartUp() {
                this.scheduleEvent(respondingEntity!!, "dummy data", delay)
            }

            override fun onEvent(source: Entity, data: Any) {
                this.scheduleEvent(source, "dummy data", delay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        respondingEntity = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {
                val sim = simulation
                if (sim.addedEventCounter >= maxEventNumber) {
                    currentProcessedEventCounter = sim.addedEventCounter
                    simulation.endSimulation()
                }
                this.scheduleEvent(source, "dummy data", delay)
            }

            override fun onSteadyState() {}
            override fun onShutDown() {}
        }

        val sim = Simulation(mutableListOf(respondingEntity, sendingEntity))
        sim.startSimulation()

        assertEquals(currentProcessedEventCounter, sim.addedEventCounter - 1)
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
            override fun onStartUp() {
                this.setTimer(
                    timerDelay1,
                    object : ITimer {
                        override fun onTimerExpired(clock: Long) {
                            timer1Result = simulation.clock
                        }
                    }
                )
                this.setTimer(
                    timerDelay2,
                    object : ITimer {
                        override fun onTimerExpired(clock: Long) {
                            timer2Result = simulation.clock
                        }
                    }
                )
                this.setTimer(
                    timerDelay3,
                    object : ITimer {
                        override fun onTimerExpired(clock: Long) {
                            timer3Result = simulation.clock
                        }
                    }
                )
            }

            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {}
            override fun onShutDown() {}
        }
        val sim = Simulation(mutableListOf(entity))
        sim.startSimulation()

        assertEquals(timerDelay1, timer1Result)
        assertEquals(timerDelay2, timer2Result)
        assertEquals(timerDelay3, timer3Result)
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
                this.scheduleEvent(entityB, "dummy data", delay)
            }

            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {
                entityAIsCalledAt = simulation.clock
            }

            override fun onShutDown() {}
        }

        entityB = object : Entity() {
            override fun onStartUp() {}
            override fun onEvent(source: Entity, data: Any) {}
            override fun onSteadyState() {
                entityBIsCalledAt = simulation.clock
            }

            override fun onShutDown() {}
        }

        val sim = Simulation(mutableListOf(entityA, entityB))
        sim.steadyClock = steadyStateAt
        sim.startSimulation()

        assertEquals(delay, sim.clock)
        assertEquals(steadyStateAt, entityAIsCalledAt)
        assertEquals(steadyStateAt, entityBIsCalledAt)
    }
}
