val options=mapOf(
"chooseS00ResultFlow" to listOf("commonS00ResultFlowGenerateTestsMain","commonS00ResultFlowFastMain","commonS00ResultFlowExecuteTestsMain"),
"chooseS00Execution" to listOf("commonS00ExecutionSequentialMain","commonS00ExecutionParallelMain"),
"chooseS00Trace" to listOf("commonS00TraceOnMain","commonS00TraceOffMain"),
"chooseS03" to listOf("commonS03DictionaryNoneMain","commonS03DictionaryIntArrayMain"),
"chooseS05" to listOf("commonS05HashMapMain"),
"chooseS12" to listOf("jvmS12DummyMain","commonS12LocalMain"),
"chooseS14" to listOf("jvmS14KorioMain","commonS14NoneMain"),
"chooseS15" to listOf("commonS15LocalMain","commonS15DistributedMain")
)
val conflicts=listOf(
setOf("commonS00ExecutionParallelMain","commonS00TraceOnMain"),
setOf("commonS03DictionaryNoneMain","commonS00ResultFlowGenerateTestsMain","commonS00ResultFlowExecuteTestsMain"),
setOf("commonS12LocalMain","commonS15DistributedMain"),
setOf("commonS12LocalMain","jvmS14KorioMain"),
setOf("jvmS12DummyMain","commonS03DictionaryNoneMain"),
setOf("commonS00ResultFlowGenerateTestsMain","commonS15LocalMain")
)
val fastBuildHelper=setOf(
"commonS00ResultFlowGenerateTestsMain",
"commonS00ResultFlowExecuteTestsMain",
"commonS00ExecutionSequentialMain",
"commonS00TraceOnMain"
)
fun presentChoice(options:List<String>):String{
println("choose one of $options")
			while(true){
                                val input = readLine()
                                if(input!=null)
                                        if(options.contains(input))
                                        return input
                        }
}



val sourceFolders=mutableSetOf("commonMain")
for((k,choices) in options){
	val remainingChoices=mutableListOf<String>()
	for(choice in choices){
		var ok=true
		for (conflict in conflicts)
			if(conflict.contains(choice))
				for(sourceFolder in sourceFolders)
					if(conflict.contains(sourceFolder))
						ok=false
		if(ok)
			remainingChoices.add(choice)
	}
	when(remainingChoices.size){
		0->throw Exception("script error")
		1->sourceFolders.add(remainingChoices[0])
		else->{
			val choice=presentChoice(remainingChoices)
			sourceFolders.add(choice)
		}
	}
}
println("result :: ")
for(sourceFolder in sourceFolders)
	println(sourceFolder)
