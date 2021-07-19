package lupos.simulator_iot

import lupos.simulator_iot.config.JsonObjects

internal interface ISimRunPreparation {

    fun prepareJsonObjects(jsonObjects: JsonObjects)
}
