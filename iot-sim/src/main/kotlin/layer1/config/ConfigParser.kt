package layer1.config

import kotlinx.serialization.*
import kotlinx.serialization.json.*

class ConfigParser {

    val configFile = "example1.json"

    fun parse(fileName: String) {
        val fileStr = readFileDirectlyAsText(fileName)
        val data = Json.decodeFromString<Config>(fileStr)
        println(data)
    }

    private fun readFileDirectlyAsText(fileName: String): String
            = javaClass.classLoader.getResource(fileName).readText()

}
