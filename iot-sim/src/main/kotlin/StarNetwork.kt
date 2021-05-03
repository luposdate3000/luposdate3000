class StarNetwork(val parent: Device) {
    var networkPrefix = ""
    var children: MutableList<Device> = arrayListOf()
}