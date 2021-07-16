package lupos.simulator_iot.models.sensor

public interface ISensor {
    public fun setDataSink(sinkAddress: Int)
    public fun startSampling()
    public fun stopSampling()
}
