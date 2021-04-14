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
package lupos.s00misc

import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.PackageElement
import javax.lang.model.element.Parameterizable
import javax.lang.model.element.QualifiedNameable
import javax.lang.model.element.TypeElement
import javax.lang.model.element.TypeParameterElement
import javax.lang.model.element.VariableElement


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
    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        mutableSetOf(CodeGenerationAnnotation::class.java.name)

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"] ?: return false
        roundEnv.getElementsAnnotatedWith(CodeGenerationAnnotation::class.java)
            .forEach { element ->
                try {
                    // input ->
                    val className = element.enclosingElement.simpleName.toString()
                    val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
                    // die instanz der annotation mit all ihren variablen                    val annotation = element.getAnnotation(CodeGenerationAnnotation::class.java)
                    recoursivelyPrintTypeInformation(element.enclosingElement)
                    val variableName = element.simpleName.toString()
                    val variableValue = (element as VariableElement).constantValue.toString()
                    // output->
                    val folderName = "$kaptKotlinGeneratedDir/$packageName"
                    val fileName = "$folderName/${className}___$variableName.kt"
                    val buf = MyPrintWriter(true)
                    generateSourceCode(className, packageName, variableName, variableValue, folderName, fileName)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        return true
    }

    internal fun recoursivelyPrintTypeInformation(element: Element, indention: String = "") {
        println("${indention}simpleName :: ${element.simpleName}")
        println("${indention}kind :: ${element.kind}")
        println("${indention}modifiers :: ${element.modifiers}")
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
            println("${indention}value :: ${element.constantValue}")
        }
        for (element2 in element.enclosedElements) {
            recoursivelyPrintTypeInformation(element2, "  $indention")
        }
    }

}
