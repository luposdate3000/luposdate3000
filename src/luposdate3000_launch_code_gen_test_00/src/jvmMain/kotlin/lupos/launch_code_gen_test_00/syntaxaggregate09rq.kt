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
package lupos.launch_code_gen_test_00
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.myPrintStackTrace
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import simora.addQuerySender
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.EPartitionModeExt
import lupos.shared.MemoryTable
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import simora.SimulationRun
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingImportPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingExecute
import lupos.simulator_db.luposdate3000.Application_Luposdate3000


public class syntaxaggregate09rq {
    internal val query = "SELECT (MAX(?x) AS ?y) {} \n" +
        ""

    public fun `syntaxaggregate09rq - Thread - Simple - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=false
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/syntaxaggregate09rq.kt:55"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    internal fun normalHelper(instance:Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "syntaxaggregate09rq - Thread - Simple - false" to ::`syntaxaggregate09rq - Thread - Simple - false`,
        )
    }
}
public fun main(){
    for((name,func) in syntaxaggregate09rq().getTests()){
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream{ out->
            out.println("started")
            try{
                func()
                out.println("passed")
            }catch(e:Error){
                out.println("failed")
                e.printStackTrace()
            }
        }
    }
}
