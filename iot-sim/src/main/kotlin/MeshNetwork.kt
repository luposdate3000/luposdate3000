class MeshNetwork {
    var networkPrefix = ""
    var mesh: MutableList<MutableList<Device>> = arrayListOf(arrayListOf())

    fun numOfDevices(): Int {
        var counter = 0
        for (list in mesh) {
            counter += list.size
        }
        return counter
    }
}