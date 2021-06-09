package lupos.des_core

public interface ISimulationLifeCycle {
    public fun onStartUp()
    public fun onSteadyState()
    public fun onShutDown()
}
