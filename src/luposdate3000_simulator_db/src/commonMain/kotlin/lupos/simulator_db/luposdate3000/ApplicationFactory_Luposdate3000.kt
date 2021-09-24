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
import lupos.parser.IJsonParserValue
import lupos.parser.JsonParserObject
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplication_Factory
import lupos.simulator_db.ILogger
import lupos.simulator_db.RandomGenerator

public class ApplicationFactory_Luposdate3000 : IApplication_Factory {
    private val dbDeviceAddressesStoreList = mutableListOf<Int>()
    private val dbDeviceAddressesQueryList = mutableListOf<Int>()
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
                )
            )
        }
        return listOf()
    }
}
