package lupos.simulator_iot.sensor

internal interface ISensor {
    fun setDataSink(sinkAddress: Int)
    fun startSampling()
    fun stopSampling()
}
