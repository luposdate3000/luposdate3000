
package lupos.simulator_iot
import lupos.shared.Luposdate3000Instance
import lupos.simulator_core.ISimulationLifeCycle

public class LifeCycleImpl(private val simRun: SimulationRun) : ISimulationLifeCycle {

    override fun onStartUp() {
        simRun.timeMeasurer.onStartUp()
        simRun.measureOnStartUp()
        simRun.logger?.logStartUp()
    }

    override fun onSteadyState() {
    }

    override fun onShutDown() {
        simRun.timeMeasurer.onShutDown()
        simRun.measureOnShutDown()
        simRun.logger?.logShutDown()
        if (Luposdate3000Instance.enableVisualisationInSimulator) {
//            println(simRun.visualisationNetwork.toString())
            simRun.visualisationNetwork.toImage()
        }
    }
}
