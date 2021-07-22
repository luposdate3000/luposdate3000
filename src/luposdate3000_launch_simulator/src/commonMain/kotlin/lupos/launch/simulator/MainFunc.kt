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
package lupos.launch.simulator

import lupos.shared.Parallel
import lupos.simulator_iot.Evaluation
internal fun mainFunc(): Unit = Parallel.runBlocking {
    // Evaluation().simulate("${FilePaths.jvmResource}/Exception_2Sensors1Database.json")
    // Evaluation().simulate("${FilePaths.jvmResource}/anotherException_2Sensor1Database.json")
    // Evaluation().simulate("${FilePaths.jvmResource}/Exception_2DBwith1Sensor.json")
    // Evaluation().simulate("${FilePaths.jvmResource}/star.json")
    // Evaluation().measureStarPerformance(true)
    val evaluation = Evaluation()
    evaluation.evalMeshPerf()
    //evaluation.evalStarPerf()
    //evaluation.evalStarPerfWithDummy()
    //evaluation.evalStarPerfWithLuposdate()

}
