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

package lupos.simulator_iot.applications

import lupos.parser.IJsonParserValue
import lupos.parser.JsonParserObject
import lupos.simulator_iot.ILogger
import lupos.simulator_iot.RandomGenerator

public class ApplicationFactory_ParkingSensorFeature : IApplicationFeature {
    override fun getName(): String = "Sensor"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_ParkingSensor
}

public class ApplicationFactory_ParkingSensor : IApplication_Factory {
    override fun registerFeatures(features: MutableList<IApplicationFeature>) {
        features.add(ApplicationFactory_ParkingSensorFeature())
    }

    override fun create(json: IJsonParserValue, ownAddress: Int, logger: ILogger, outputDirectory: String, random: RandomGenerator): List<IApplicationStack_Actuator> {
        json as JsonParserObject
        if (json.getOrDefault("enabled", true)) {
            return listOf(
                Application_ParkingSensor(
                    json.getOrDefault("sendStartClockInSec", 0),
                    json.getOrDefault("rateInSec", 0),
                    json.getOrDefault("maxSamples", -1),
                    ownAddress,
                    random,
                    json.getOrDefault("area", 0),
                    json.getOrDefault("spotInArea", 0),
                )
            )
        }
        return listOf()
    }
}
