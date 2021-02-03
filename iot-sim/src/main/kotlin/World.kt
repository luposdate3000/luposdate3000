object World {

/*    val graph: AdjacencyMatrix<Device, Connection> = AdjacencyMatrix()

    fun createRandomPoint(area: Area): Point = createRandomPoint(area.xLeft, area.xRight, area.yBottom, area.yTop)

    fun createRandomPoint(xLeft: Int, xRight: Int, yBottom: Int, yTop: Int): Point {
        val x = createRandomCoordinate(xLeft, xRight)
        val y = createRandomCoordinate(yBottom, yTop)
        return Point(x, y)
    }

    private fun createRandomCoordinate(first: Int, second: Int) = (first..second).random()

    fun createDevicesInCategory(category: DeviceCategory): DeviceCategory {
        for (i in 0 until category.numberOfDevices) {
            val newDevice = shallowCopyOfDeviceInCategory(category)
            category.devices.add(newDevice)
        }
        return category
    }

    private fun shallowCopyOfDeviceInCategory(category: DeviceCategory): Device {
        val newDevice = category.device.copy()
        newDevice.point = createRandomPoint(category.area)
        return newDevice
    }

    fun getDistance(point1: Point, point2: Point): Double {
        val x1 = point1.x.toDouble()
        val x2 = point2.x.toDouble()
        val y1 = point1.y.toDouble()
        val y2 = point2.y.toDouble()
        return getDistance(x1, y1, x2, y2)
    }

    private fun getDistance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        val notRounded = sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
        return roundToNDecimalPlaces(notRounded, 2)
    }

    fun roundToNDecimalPlaces(number: Double, places: Int): Double {
        val roundedStr = String.format(Locale.ENGLISH, "%.${places}f", number)
        return roundedStr.toDouble()
    }

    fun deviceOneCanReachDeviceTwo(one: Device, two: Device): Boolean {
        val distance = getDistance(one.point, two.point)
        return one.signalRange >= distance
    }*/
}