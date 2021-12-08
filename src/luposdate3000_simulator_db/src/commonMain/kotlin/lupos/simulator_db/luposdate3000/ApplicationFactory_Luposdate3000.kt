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

package lupos.simulator_db.luposdate3000

import simora.parser.IJsonParserValue
import simora.parser.JsonParserObject
import simora.simulator_iot.ILogger
import simora.simulator_iot.RandomGenerator
import simora.simulator_iot.applications.IApplicationFeature
import simora.simulator_iot.applications.IApplicationStack_Actuator
import simora.simulator_iot.applications.IApplication_Factory

public class ApplicationFactory_Luposdate3000FeatureStore : IApplicationFeature {
    override fun getName(): String = "DatabaseStore"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000 && application.hasStoreCapability()
}

public class ApplicationFactory_Luposdate3000FeatureQuery : IApplicationFeature {
    override fun getName(): String = "DatabaseQuery"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000 && application.hasQueryCapability()
}

public class ApplicationFactory_Luposdate3000FeatureAny : IApplicationFeature {
    override fun getName(): String = "Database"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000
}

public class ApplicationFactory_Luposdate3000 : IApplication_Factory {
    private val dbDeviceAddressesStoreList = mutableListOf<Int>()
    private val dbDeviceAddressesQueryList = mutableListOf<Int>()
    private var featureIDStore = -1
    private var featureIDQuery = -1
    private var featureIDAny = -1
    override fun registerFeatures(features: MutableList<IApplicationFeature>) {
        featureIDStore = features.size
        features.add(ApplicationFactory_Luposdate3000FeatureStore())
        featureIDQuery = features.size
        features.add(ApplicationFactory_Luposdate3000FeatureQuery())
        featureIDAny = features.size
        features.add(ApplicationFactory_Luposdate3000FeatureAny())
    }

    override fun create(json: IJsonParserValue, ownAddress: Int, logger: ILogger, outputDirectory: String, random: RandomGenerator): List<IApplicationStack_Actuator> {
        json as JsonParserObject
        if (json.getOrDefault("enabled", true)) {
            val databaseQuery = json.getOrDefault("databaseQuery", true)
            val databaseStore = json.getOrDefault("databaseStore", true) || !databaseQuery // at least one must be true
            if (databaseStore) {
                dbDeviceAddressesStoreList.add(ownAddress)
            }
            if (databaseQuery) {
                dbDeviceAddressesQueryList.add(ownAddress)
            }
            return listOf(
                Application_Luposdate3000(
                    json,
                    logger,
                    ownAddress,
                    "$outputDirectory/db_states/device$ownAddress",
                    dbDeviceAddressesStoreList,
                    dbDeviceAddressesQueryList,
                    featureIDStore,
                    featureIDQuery,
                    featureIDAny,
                )
            )
        }
        return listOf()
    }
}
