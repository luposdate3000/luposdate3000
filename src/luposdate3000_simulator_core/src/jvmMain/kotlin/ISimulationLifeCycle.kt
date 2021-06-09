package lupos.simulator_core

public interface ISimulationLifeCycle {
    public var simulation: ISimulation
    public fun onStartUp()
    public fun onSteadyState()
    public fun onShutDown()
}
