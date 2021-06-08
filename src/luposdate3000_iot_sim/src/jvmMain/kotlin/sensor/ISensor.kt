package lupos.iot_sim.sensor

public interface ISensor {
    public fun setDataSink(sinkAddress: Int)
    public fun startSampling()
    public fun stopSampling()
}
