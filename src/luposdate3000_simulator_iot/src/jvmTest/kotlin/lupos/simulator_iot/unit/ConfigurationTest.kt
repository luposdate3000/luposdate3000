/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.unit
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.simulator_iot.SimulationRun
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConfigurationTest {

    companion object {
        private const val prefix = "src/jvmTest/resources/configurationTest"
    }

    @Test
    fun parseEmptyConfigFile() {
        val config = SimulationRun().config
        config.parse("$prefix/parseEmptyConfigFile.json")
        assertEquals(0, config.getNumberOfDevices())
    }

    @Test
    fun countNumberOfDevicesInRandomNetwork() {
        val config = SimulationRun().config
        config.parse("$prefix/countNumberOfDevicesInRandomNetwork.json")
        val network = config.jsonObjects.randomStarNetwork[0]
        val starNet = config.randStarNetworks[network.networkPrefix]!!
        assertEquals(1 + network.number, config.getNumberOfDevices())
        assertEquals(network.number, starNet.children.size)
    }

    @Test
    fun checkLinksInStarNetwork() {
        val config = SimulationRun().config
        config.parse("$prefix/checkLinksInStarNetwork.json")
        val networkPrefix = config.jsonObjects.randomStarNetwork[0].networkPrefix
        val starNet = config.randStarNetworks[networkPrefix]!!
        for (child in starNet.children) {
            assertTrue(child.linkManager.hasLink(starNet.root))
            assertTrue(starNet.root.linkManager.hasLink(child))
        }
    }

    @Test
    fun inStarNetworkChildrenDoNotKnowEachOther() {
        val config = SimulationRun().config
        config.parse("$prefix/inStarNetworkChildrenDoNotKnowEachOther.json")
        val starNet = config.randStarNetworks["garageA"]!!
        for (childA in starNet.children)
            for (childB in starNet.children)
                assertFalse(childA.linkManager.hasLink(childB))
    }

    @Test
    fun severalStarNetworksDifferInTheNetworkPrefix() {
        val config = SimulationRun().config
        config.parse("$prefix/severalStarNetworksDifferInTheNetworkPrefix.json")
        val starNetA = config.randStarNetworks["garageA"]!!
        val starNetB = config.randStarNetworks["garageB"]!!
        val starNetC = config.randStarNetworks["garageC"]!!

        assertEquals(10, starNetA.children.size)
        assertEquals(11, starNetB.children.size)
        assertEquals(12, starNetC.children.size)
    }

    @Test
    fun onlyOneMeshDevice() {
        val config = SimulationRun().config
        config.parse("$prefix/onlyOneMeshDevice.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.numOfDevices())
        assertEquals(1, config.getNumberOfDevices())
    }

    @Test
    fun onlySouthernMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/onlySouthernMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.mesh.size)
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh[0].size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
    }

    @Test
    fun onlyEasternMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/onlyEasternMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
        for (col in meshNet.mesh) {
            assertEquals(1, col.size)
        }
    }

    @Test
    fun moreSouthernThanEasternMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/moreSouthernThanEasternMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
        val rowSize = meshNet.mesh.size
        for (col in meshNet.mesh) {
            assertTrue(col.size > rowSize)
        }
    }

    @Test
    fun edgeMeshDevicesCannotReachEachOther() {
        val config = SimulationRun().config
        config.parse("$prefix/edgeMeshDevicesCannotReachEachOther.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = config.randMeshNetworks[networkPrefix]!!.mesh
        val northWest = mesh[0][0]
        val southWest = mesh[0][mesh[0].size - 1]
        val northEast = mesh[mesh.size - 1][0]
        val southEast = mesh[mesh.size - 1][mesh[mesh.size - 1].size - 1]

        assertFalse(northWest.linkManager.hasLink(northEast))
        assertFalse(northWest.linkManager.hasLink(southEast))
        assertFalse(northWest.linkManager.hasLink(southWest))
        assertFalse(northEast.linkManager.hasLink(southWest))
        assertFalse(northEast.linkManager.hasLink(southEast))
        assertFalse(southEast.linkManager.hasLink(southWest))
    }

    @Test
    fun manipulateJsonObjects() {
        val configFile = "$prefix/manipulateJsonObjects.json"
        val config = SimulationRun().config
        val json = JsonParser().fileToJson(configFile) as JsonParserObject
        val randomStarNetworks = json.getOrEmptyArray("randomStarNetwork")
        randomStarNetworks.add(JsonParser().stringToJson("{\"networkPrefix\":\"star2\",\"starRoot\":\"Tower1\",\"linkType\":\"WPAN\",\"deviceType\":\"StandAloneParkingSensor\",\"number\":3}"))
        config.parse(json, configFile, false)
        assertEquals(2, config.randStarNetworks.size)
    }
}
