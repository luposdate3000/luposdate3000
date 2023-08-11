import java.util.concurrent.TimeUnit
import java.lang.ProcessBuilder.Redirect
internal fun exec(clazz: Class<*>, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    return exec(clazz.name,args,jvmArgs)
}
internal fun exec(className:String, args: List<String> = emptyList(), jvmArgs: List<String> = emptyList()): Int {
    val javaHome = System.getProperty("java.home")
    val javaBin = javaHome + "/bin/java"
    val classpath = System.getProperty("java.class.path")
    val command = ArrayList<String>()
    command.add(javaBin)
    command.addAll(jvmArgs)
    command.add("-cp")
    command.add(classpath)
    command.add(className)
    command.addAll(args)
    val builder = ProcessBuilder(command)
    val env = builder.environment()
    env.putAll(System.getenv())
    builder.redirectError(Redirect.appendTo(java.io.File(className+".err")))
    builder.redirectOutput(Redirect.appendTo(java.io.File(className+".log")))
    val process = builder.start()
    process.waitFor(10, TimeUnit.SECONDS)
    process.destroyForcibly()
    process.waitFor()
    return process.exitValue()
}
public fun main(){
    val tests=listOf(
        "lupos.launch_code_gen_test_00.IRIURIKt",
    )
    val tests2=listOf(
        "lupos.launch_code_gen_test_00.INSERT02Kt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnodeKt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeKt",
        "lupos.launch_code_gen_test_00.INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolutionKt",
        "lupos.launch_code_gen_test_00.INSERTsamebnodetwiceKt",
        "lupos.launch_code_gen_test_00.MedicaltemporalproximitybyexclusionNOTEXISTSKt",
        "lupos.launch_code_gen_test_00.PapersparqldlQ1Kt",
        "lupos.launch_code_gen_test_00.PapersparqldlQ1rdfsKt",
        "lupos.launch_code_gen_test_00.PapersparqldlQ2Kt",
        "lupos.launch_code_gen_test_00.PapersparqldlQ3Kt",
        "lupos.launch_code_gen_test_00.PapersparqldlQ4Kt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildexactly1FemalerestrictionKt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildmax1FemalerestrictionKt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildmin1FemalerestrictionKt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildmin1restrictionKt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildsomeFemalerestrictionKt",
        "lupos.launch_code_gen_test_00.ParentquerywithhasChildsomeThingrestrictionKt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery11853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery12553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery41853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery42553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery51853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery52210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery52553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmbiquery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery111853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery112210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery112553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery21853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery22553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery71853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery72553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery81853sparql1853Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82210sparql2210Kt",
        "lupos.launch_code_gen_test_00.Resourcesbsbmexplorequery82553sparql2553Kt",
        "lupos.launch_code_gen_test_00.Resourcesbtc020sparql80Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent03sparql764Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent12sparql567Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent14sparql567Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent17sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent19sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent20sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcesmyqueriesubungent21sparql709Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq11sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq65sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq67sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq67sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq67sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq67sparql973Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq68sparql1294Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq68sparql1640Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq68sparql700Kt",
        "lupos.launch_code_gen_test_00.Resourcessp2bq68sparql973Kt",
        "lupos.launch_code_gen_test_00.STRAFTERdatatypingKt",
        "lupos.launch_code_gen_test_00.STRBEFOREdatatypingKt",
        "lupos.launch_code_gen_test_00.Simple1Kt",
        "lupos.launch_code_gen_test_00.Simple3Kt",
        "lupos.launch_code_gen_test_00.Simple5Kt",
        "lupos.launch_code_gen_test_00.Simple7Kt",
        "lupos.launch_code_gen_test_00.Simple8Kt",
        "lupos.launch_code_gen_test_00.SimpleDELETE1USINGKt",
        "lupos.launch_code_gen_test_00.SimpleDELETE4USINGKt",
        "lupos.launch_code_gen_test_00.SimpleDELETE7Kt",
        "lupos.launch_code_gen_test_00.Simpleinsertdatanamed1Kt",
        "lupos.launch_code_gen_test_00.Sparqldl03rqcombinedquerywithcomplexclassdescriptionKt",
        "lupos.launch_code_gen_test_00.Sparqldl10rqundistvarstestKt",
        "lupos.launch_code_gen_test_00.Sparqldl11rqdomaintestKt",
        "lupos.launch_code_gen_test_00.Sparqldl12rqrangetestKt",
        "lupos.launch_code_gen_test_00.Sparqldl13rqsameAsKt",
        "lupos.launch_code_gen_test_00.Sq14limitbyresourceKt",
        "lupos.launch_code_gen_test_00.SubclassquerywithhasChildsomeThingrestrictionKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromafullyboundminuendKt",
        "lupos.launch_code_gen_test_00.SubtractionwithMINUSfromapartiallyboundminuendKt",
        "lupos.launch_code_gen_test_00.TZKt",
    )
    tests.parallelStream().forEach{it->
              println(it+" start")
              exec(it, jvmArgs = listOf("-Xmx8g"))
              println(it+" done")
    }
}
