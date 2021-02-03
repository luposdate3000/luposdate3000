package net

class WorldTest {

//    @ParameterizedTest
//    @CsvSource(
//        "0, 0, 0, 0",
//        "0, 7, 0, 18",
//        "40, 100, 42, 100",
//        "10, 10, 10, 10"
//    )
//    fun `Random point should be within borders`(xLeft: Int, xRight: Int, yBottom: Int, yTop: Int) {
//        val point = World.createRandomPoint(xLeft, xRight, yBottom, yTop)
//        val xIsValid = point.x in xLeft..xRight
//        val yIsValid = point.y in yBottom..yTop
//        assertTrue(xIsValid)
//        assertTrue(yIsValid)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        "0, 0, 0, 0",
//        "0, 500, 8, 500",
//    )
//    fun `Random point should be in the area`(xLeft: Int, xRight: Int, yBottom: Int, yTop: Int) {
//        val area = Area(xLeft, xRight, yBottom, yTop)
//        val point = World.createRandomPoint(area)
//        val xIsValid = point.x in xLeft..xRight
//        val yIsValid = point.y in yBottom..yTop
//        assertTrue(xIsValid)
//        assertTrue(yIsValid)
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = [1, 2, 44])
//    fun `Number of created devices should match x`(x: Int) {
//        var category = DeviceCategory()
//        category.numberOfDevices = x
//        category = World.createDevicesInCategory(category)
//        val listSizeDoesMatch = category.devices.size == x
//        assertTrue(listSizeDoesMatch)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        "0, 0, 0, 0, 0.0",
//        "0, 1, 0, 1, 0.0",
//        "5, 1, 1, 700, 699.01",
//        "8, 20, 70, 700, 682.82",
//    )
//    fun `check calculation of distance`(x1: Int, y1: Int, x2: Int, y2: Int, expected: Double) {
//        var point1 = Point(x1, y1)
//        var point2 = Point(x2, y2)
//        var actual = World.getDistance(point1, point2)
//        assertEquals(expected, actual)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        "0.0, 4, 0.0",
//        "13.8, 2, 13.8",
//        "1.999, 2, 2.0",
//        "1.91, 2, 1.91",
//    )
//    fun `check correct rounding`(number: Double, places: Int, expected: Double) {
//        val actual = World.roundToNDecimalPlaces(number, places)
//        assertEquals(expected, actual)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        "0, 0, 0, 0, -1.0",
//        "0, 1, 0, 1, -1.0",
//        "5, 1, 1, 700, 699.00",
//        "8, 20, 70, 700, 682.81",
//    )
//    fun `Point should not be reachable`(x1: Int, y1: Int, x2: Int, y2: Int, signalRange: Double) {
//        val point1 = Point(x1, y1)
//        val point2 = Point(x2, y2)
//        val one = Device()
//        one.point = point1
//        one.signalRange = signalRange
//        val two = Device()
//        two.point = point2
//        val isReachable = World.deviceOneCanReachDeviceTwo(one, two)
//        assertFalse(isReachable)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        "0, 0, 0, 0, 1.0",
//        "0, 1, 0, 1, 0.0",
//        "5, 1, 1, 700, 699.01",
//        "8, 20, 70, 700, 682.82",
//    )
//    fun `Point should be reachable`(x1: Int, y1: Int, x2: Int, y2: Int, signalRange: Double) {
//        val point1 = Point(x1, y1)
//        val point2 = Point(x2, y2)
//        val one = Device()
//        one.point = point1
//        one.signalRange = signalRange
//        val two = Device()
//        two.point = point2
//        val isReachable = World.deviceOneCanReachDeviceTwo(one, two)
//        assertTrue(isReachable)
//    }

}