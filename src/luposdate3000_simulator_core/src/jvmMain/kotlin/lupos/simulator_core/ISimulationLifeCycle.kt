package lupos.simulator_core

public interface ISimulationLifeCycle {
    public var simulation: Simulation
    public fun onStartUp()
    public fun onSteadyState()
    public fun onShutDown()
}
