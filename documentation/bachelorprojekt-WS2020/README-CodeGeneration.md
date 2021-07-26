## CodeGeneration

### Usage
You can annotate queries as String with`@JvmField
@CodeGenerationAnnotation`
(see [ExampleAnnotation](../../src/luposdate3000_launch_code_gen_example_kapt/src/jvmMain/kotlin/lupos/launch/code_gen_example_kapt/ExampleAnnotation.kt)),
those query will be processed, and the source code that is able to run 
the query will be placed into the build folder of the module it was used in.
*(/build/generated/source/kaptKotlin/main/\<packagename\>/\<Classname\>__\<variablename\>.kt)*  
In there a function ``public fun <Classname>.<Variablename>_evaluate(): String``
is generated, that will eventually evaluate all the operators and returns the result.
(see [ExampleAnnotation's main](../../src/luposdate3000_launch_code_gen_example_kapt/src/jvmMain/kotlin/lupos/launch/code_gen_example_kapt/MainFunc.kt))
### How it works
Whenever you annotate a query with ``@CodeGenerationAnnotation`` the ``process()``-Method
of [CodeGenerationGenerator](../../src/luposdate3000_code_generator_kapt/src/jvmMain/kotlin/lupos/code_generator_kapt/CodeGenerationGenerator.kt)
will be called. In there some variables are set, and it will eventually call ``generateSourceCode()``
that is located in [CodeGeneration](../../src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/CodeGeneration.kt)
, this is the function that will generate all the source code and prints it to the file. In order to evaluate a query we need 
to evaluate all the operators of the queries operatorgraph,
the trick is to replace some with specialized code that is 
optimized for that specific query. The first thing to print into the generated file is the `<Variablename>_evaluate()`
function, for that we simply evaluate the annotated query to an operatorgraph with `LuposdateEndpoint.evaluateSparqlToOpertorgraphA()`, in
`writeOperatorGraph()` we recursively iterate over all the operators and create a variable for each with its name
concatenated with its `uuid`, so we are able to use it later. Those variables are initialized with the operators
constructor, so we get a perfectly fine instance of that operator 
when the generated code is executed. Also, we can replace an operator we want to optimize with our 
own class-constructor, this is the case for [POPBind](../../src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/POPBindGenerator.kt),
[POPFilter](../../src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/POPFilterGenerator.kt)
and [POPJoinMerge](../../src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/POPJoinMergeGenerator.kt).
The optimized classes for those operators are written below `<Variablename>_evaluate()` and have the same name as their variable.  
In order to evaluate annotated queries at runtime with the operators we stored as variables we simply call
```LuposdateEndpoint.evaluateOperatorgraphToResult()``` within the generated file with the operatorgraph's root which is the operator we 
retrieved from `LuposdateEndpoint.evaluateSparqlToOpertorgraphA()`. 
