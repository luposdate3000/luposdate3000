interface IDeviceSensor {
    fun setDataSink(sinkAddress: Int)
    fun startSampling()
    fun stopSampling()
}