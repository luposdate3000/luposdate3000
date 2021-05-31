interface IDeviceSensor {
    var dataSinkAddress: Int
    fun startSampling()
    fun stopSampling()
}