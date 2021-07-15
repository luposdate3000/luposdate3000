
package lupos.simulator_iot

import lupos.shared.inline.File
import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.measure.Logger
import lupos.simulator_iot.measure.Measurement
import lupos.simulator_iot.measure.TimeMeasurer
import lupos.simulator_iot.utils.FilePaths
import lupos.visualize.distributed.database.VisualisationConnection
import lupos.visualize.distributed.database.VisualisationDevice


    public class LifeCycleImpl(private val simRun:SimulationRun) : ISimulationLifeCycle {

        override fun onStartUp() {
simRun.            timeMeasurer.onStartUp()
       simRun.     measureOnStartUp()
          simRun.  logger.logStartUp()
        }

        override fun onSteadyState() {
        }

        override fun onShutDown() {
simRun.            timeMeasurer.onShutDown()
       simRun.     measureOnShutDown()
simRun.            logger.logShutDown()
        }
    }

