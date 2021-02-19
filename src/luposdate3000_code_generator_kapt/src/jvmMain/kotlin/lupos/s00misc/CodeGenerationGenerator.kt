package lupos.s00misc
// import com.squareup.kotlinpoet.*

import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.*

// dieses Tutorial hat mir sehr geholfen
// https://www.kotlindevelopment.com/generateforme/
// An dieser stelle wird häufig eine Annotation AutoService verwendet. Diese Annotation erzeigt lediglich die Datei "processor/src/main/resources/META-INF/services/javax.annotation.processing.Processor"
// Ich bevorzuge weniger Dependencies (da diese dann nicht kaputt gehen können), wenn diese nur so triviale Aufgaben erledigen.
// aus kompatibilitätsgründen für diese Beispiel java 1.8 ... luposdate3000 kompiliert zurzeit auch mit java 1.8, aber es sollte kein großens Problem sein, hier die Java-version zu erhöhen.
// @SupportedSourceVersion(SourceVersion.RELEASE_8)
// @SupportedAnnotationTypes("lupos.s00misc.CodeGenerationAnnotation")
@SupportedOptions(CodeGenerationGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
public class CodeGenerationGenerator : AbstractProcessor() {
    init {
        println("CodeGenerationGenerator init")
    }

    public companion object {
        public const val KAPT_KOTLIN_GENERATED_OPTION_NAME: String = "kapt.kotlin.generated"
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()
    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(CodeGenerationAnnotation::class.java.name)
    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"] ?: return false
        roundEnv.getElementsAnnotatedWith(CodeGenerationAnnotation::class.java)
            .forEach { element ->
                try {
                    // input ->
                    val className = element.getEnclosingElement().getSimpleName().toString()
                    val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
                    // die instanz der annotation mit all ihren variablen                    val annotation = element.getAnnotation(CodeGenerationAnnotation::class.java)
                    recoursivelyPrintTypeInformation(element.getEnclosingElement())
                    val variableName = element.getSimpleName().toString()
                    val variableValue = (element as VariableElement).getConstantValue().toString()
                    // output->
                    val folderName = "$kaptKotlinGeneratedDir/$packageName"
                    val fileName = "$folderName/${className}___$variableName.kt"
                    println("$className $packageName $variableName $variableValue into $fileName")
                    java.io.File(folderName).mkdirs()
                    java.io.File(fileName).printWriter().use { out ->
                        out.println("package $packageName")
                        out.println("public fun $className.${variableName}_evaluate():String=\"$variableValue\"")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        return true
    }

    internal fun recoursivelyPrintTypeInformation(element: Element, indention: String = "") {
        println("${indention}simpleName :: ${element.getSimpleName()}")
        println("${indention}kind :: ${element.getKind()}")
        println("${indention}modifiers :: ${element.getModifiers()}")
        println("${indention}annotation :: ${element.getAnnotation(CodeGenerationAnnotation::class.java)}")
        if (element is ExecutableElement) {
            println("${indention}subclass :: ExecutableElement")
        } else if (element is PackageElement) {
            println("${indention}subclass :: PackageElement")
        } else if (element is Parameterizable) {
            println("${indention}subclass :: Parameterizable")
        } else if (element is QualifiedNameable) {
            println("${indention}subclass :: QualifiedNameable")
        } else if (element is TypeElement) {
            println("${indention}subclass :: TypeElement")
        } else if (element is TypeParameterElement) {
            println("${indention}subclass :: TypeParameterElement")
        } else if (element is VariableElement) {
            println("${indention}subclass :: VariableElement")
            println("${indention}value :: ${element.getConstantValue()}")
        }
        for (element2 in element.getEnclosedElements()) {
            recoursivelyPrintTypeInformation(element2, "  $indention")
        }
    }
}
