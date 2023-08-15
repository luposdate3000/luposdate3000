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

import simora.ILogger
import simora.applications.IApplicationFeature
import simora.applications.IApplicationStack_Actuator
import simora.applications.IApplication_Factory
import simora.parser.IJsonParserValue
import simora.parser.JsonParserObject
import kotlin.random.Random

public class ApplicationFactory_Luposdate3000FeatureStore : IApplicationFeature {
    override fun getName(): String = "DatabaseStore"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000 && application.hasStoreCapability()
    override fun equals(other: Any?): Boolean = other is ApplicationFactory_Luposdate3000FeatureStore
}

public class ApplicationFactory_Luposdate3000FeatureQuery : IApplicationFeature {
    override fun getName(): String = "DatabaseQuery"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000 && application.hasQueryCapability()
    override fun equals(other: Any?): Boolean = other is ApplicationFactory_Luposdate3000FeatureQuery
}

public class ApplicationFactory_Luposdate3000FeatureAny : IApplicationFeature {
    override fun getName(): String = "Database"
    override fun hasFeature(application: IApplicationStack_Actuator): Boolean = application is Application_Luposdate3000
    override fun equals(other: Any?): Boolean = other is ApplicationFactory_Luposdate3000FeatureAny
}

public class ApplicationFactory_Luposdate3000 : IApplication_Factory {
    internal val applicationFactory_Luposdate3000FeatureStore = ApplicationFactory_Luposdate3000FeatureStore()
    internal val applicationFactory_Luposdate3000FeatureQuery = ApplicationFactory_Luposdate3000FeatureQuery()
    internal val applicationFactory_Luposdate3000FeatureAny = ApplicationFactory_Luposdate3000FeatureAny()
    private val dbDeviceAddressesStoreList = mutableListOf<Int>()
    private val dbDeviceAddressesQueryList = mutableListOf<Int>()
    private var featureIDStore = -1
    private var featureIDQuery = -1
    private var featureIDAny = -1
    override fun registerFeatures(features: MutableList<IApplicationFeature>) {
        features.add(applicationFactory_Luposdate3000FeatureStore)
        featureIDStore = features.indexOf(applicationFactory_Luposdate3000FeatureStore)
        features.add(applicationFactory_Luposdate3000FeatureQuery)
        featureIDQuery = features.indexOf(applicationFactory_Luposdate3000FeatureQuery)
        features.add(applicationFactory_Luposdate3000FeatureAny)
        featureIDAny = features.indexOf(applicationFactory_Luposdate3000FeatureAny)
    }

    override fun create(json: IJsonParserValue, ownAddress: Int, logger: ILogger, outputDirectory: String, random: Random, factories: MutableMap<String, IApplication_Factory>): List<IApplicationStack_Actuator> {
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
                    json.getOrDefault("tryLocalExecution", true),
                ),
            )
        }
        return listOf()
    }
}
