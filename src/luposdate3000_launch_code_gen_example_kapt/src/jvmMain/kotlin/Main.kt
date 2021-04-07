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
import examplePackage.BenchmarkClass
import examplePackage.ExampleAnnotation
import examplePackage.exampleVar_evaluate
import lupos.launch.code_gen_example_kapt.mainFunc


public fun main(args: Array<String>) {
    var flag = false
    mainFunc()
    val example = ExampleAnnotation()
    println(example.exampleVar_evaluate())
/*
    val benchmark = BenchmarkClass()
    var time = benchmark.startTimer()
    println("Elapsed time generated ${time/(1000*1000)} ms")
    var time2 = benchmark.startTimerEndpoint()
    println("Elapsed time non-generated ${time2/(1000*1000)} ms")
    println("Elapsed time factor ${time2/time}")
*/
}
