package lupos.s00misc

import com.squareup.kotlinpoet.*
import java.io.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.*

// dieses Tutorial hat mir sehr geholfen
// https://www.kotlindevelopment.com/generateforme/

// An dieser stelle wird häufig eine Annotation AutoService verwendet. Diese Annotation erzeigt lediglich die Datei "processor/src/main/resources/META-INF/services/javax.annotation.processing.Processor"
// Ich bevorzuge weniger Dependencies (da diese dann nicht kaputt gehen können), wenn diese nur so triviale Aufgaben erledigen.

// aus kompatibilitätsgründen für diese Beispiel java 1.8 ... luposdate3000 kompiliert zurzeit auch mit java 1.8, aber es sollte kein großens Problem sein, hier die Java-version zu erhöhen.

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("lupos.s00misc.CodeGenerationAnnotation")
@SupportedOptions(CodeGenerationGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class CodeGenerationGenerator : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

//    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()
//    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(CodeGenerationAnnotation::class.java.name)

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"] ?: return false
        roundEnv.getElementsAnnotatedWith(CodeGenerationAnnotation::class.java)
            .forEach { element ->
                val packagename = processingEnv.elementUtils.getPackageOf(element).toString()
                val classname = element.getEnclosingElement().getSimpleName().toString()
                val annotation = element.getAnnotation(CodeGenerationAnnotation::class.java)
                val variablename = element.getSimpleName().toString()
                println("CodeGenerationGenerator :: $packagename $classname $variablename")
// throw Exception("finish")
/*
                        val fileName = "CodeGenerationGenerator_${classname}_${funcname}"
                        println("processing $packagename $classname $funcname into ${kaptKotlinGeneratedDir}/$packagename/$fileName.kt")
//-->> ab hier wird der source-code in die Datei (die mit obigen print statement ausgegeben wurde) geschrieben.
// Hier kann jede art und weise verwendet werden, die Dateien schreibt.
// Es ist nicht nötig kotlin-poet zu verwenden, wenn Ihr das nicht wollt.
                        FileSpec.builder(packagename, fileName)
                                .addFunction(FunSpec.builder("${classname}.${funcname}")
                                        .addStatement("return \"${query}\"")
                                        .build())
                                .build()
                                .writeTo(File(kaptKotlinGeneratedDir))
//<<-- bis hier
*/
            }
        return true
    }
}
