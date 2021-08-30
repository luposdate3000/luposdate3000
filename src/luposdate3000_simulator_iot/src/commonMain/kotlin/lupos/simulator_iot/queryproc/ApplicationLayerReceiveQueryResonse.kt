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
package lupos.simulator_iot.queryproc
import lupos.simulator_db.IPayload
import lupos.simulator_db.IUserApplication
import lupos.simulator_db.IUserApplicationLayer
import lupos.simulator_db.QueryResponsePackage
public class ApplicationLayerReceiveQueryResonse : IUserApplication {
    private lateinit var parent: IUserApplicationLayer
    override fun setRouter(router: IUserApplicationLayer) {
        parent = router
    }
    override fun startUp() {
    }
    override fun shutDown() {
    }
    override fun receive(pck: IPayload): IPayload? {
        if (pck is QueryResponsePackage) {
            return null
        } else {
            return pck
        }
    }
}
