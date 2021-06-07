package sensor

interface ISensor {
    fun setDataSink(sinkAddress: Int)
    fun startSampling()
    fun stopSampling()
}