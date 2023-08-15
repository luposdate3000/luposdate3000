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

package lupos.simulator_db.dummyImpl

import simora.ILogger
import simora.applications.IApplicationFeature
import simora.applications.IApplicationStack_Actuator
import simora.applications.IApplication_Factory
import simora.parser.IJsonParserValue
import simora.parser.JsonParserObject
import kotlin.random.Random

public class ApplicationFactory_DatabaseDummyFeature : IApplicationFeature {
    override fun getName(): String = "Database"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_DatabaseDummy
    override fun equals(other: Any?): Boolean = other is ApplicationFactory_DatabaseDummyFeature
}

public class ApplicationFactory_DatabaseDummy : IApplication_Factory {
    internal val applicationFactory_DatabaseDummyFeature = ApplicationFactory_DatabaseDummyFeature()
    private val dbDeviceAddressesStoreList = mutableListOf<Int>()
    private val dbDeviceAddressesQueryList = mutableListOf<Int>()
    private var featureID = -1
    override fun registerFeatures(features: MutableList<IApplicationFeature>) {
        features.add(applicationFactory_DatabaseDummyFeature)
        featureID = features.indexOf(applicationFactory_DatabaseDummyFeature)
    }

    override fun create(json: IJsonParserValue, ownAddress: Int, logger: ILogger, outputDirectory: String, random: Random, factories: MutableMap<String, IApplication_Factory>): List<IApplicationStack_Actuator> {
        json as JsonParserObject
        if (json.getOrDefault("enabled", true)) {
            dbDeviceAddressesStoreList.add(ownAddress)
            dbDeviceAddressesQueryList.add(ownAddress)
            return listOf(
                Application_DatabaseDummy(
                    json,
                    logger,
                    ownAddress,
                    "$outputDirectory/db_states/device$ownAddress",
                    dbDeviceAddressesStoreList,
                    dbDeviceAddressesQueryList,
                    featureID,
                ),
            )
        }
        return listOf()
    }
}
