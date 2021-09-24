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

import lupos.parser.IJsonParserValue
import lupos.simulator_db.IApplication_Factory

public class ApplicationFactory_DatabaseDummy : IApplication_Factory:IApplication_Factory{
    override fun create(json: IJsonParserValue): List<IApplicationStack_Actuator> {
        json as JsonParserObject
        if (json.getOrDefault("enabled", true)) {
numberOfDatabases++
                        databaseStore = true
                        databaseQuery = true
                        dbDeviceAddressesStoreList.add(ownAddress)
                        dbDeviceAddressesQueryList.add(ownAddress)
                        return listOf(
                            Application_DatabaseDummy(
                                json,
                                simRun.logger,
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
