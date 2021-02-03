package config;

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConfigParserTest {

    @ParameterizedTest
    @ValueSource(strings = ["configTest1.json", "configTest2.json"])
    fun `CreateDevices`(fileName: String) {
        val parser = ConfigParser()
        val config = parser.parse(fileName)
        //val devices = parser.createDevices(config)


    }

}
