package lupos.simulator_iot

import lupos.simulator_iot.config.JsonObjects

internal interface IConfigManipulator {

    fun manipulateJsonObjects(jsonObjects: JsonObjects)

}
