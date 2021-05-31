interface IDeviceSensor {
    var dataSinkAddress: Int
    fun startTakingSample()
    fun onSampleTaken()
}