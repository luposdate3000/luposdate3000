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
package lupos

import lupos.code_generator_shared.CodeGeneration
import org.jetbrains.kotlin.ksp.processing.CodeGenerator
import org.jetbrains.kotlin.ksp.processing.KSPLogger
import org.jetbrains.kotlin.ksp.processing.Resolver
import org.jetbrains.kotlin.ksp.processing.SymbolProcessor
import org.jetbrains.kotlin.ksp.symbol.KSAnnotated
import org.jetbrains.kotlin.ksp.symbol.KSAnnotation
import org.jetbrains.kotlin.ksp.symbol.KSCallableReference
import org.jetbrains.kotlin.ksp.symbol.KSClassDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSClassifierReference
import org.jetbrains.kotlin.ksp.symbol.KSDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSDeclarationContainer
import org.jetbrains.kotlin.ksp.symbol.KSDynamicReference
import org.jetbrains.kotlin.ksp.symbol.KSExpectActual
import org.jetbrains.kotlin.ksp.symbol.KSFile
import org.jetbrains.kotlin.ksp.symbol.KSFunctionDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSModifierListOwner
import org.jetbrains.kotlin.ksp.symbol.KSNode
import org.jetbrains.kotlin.ksp.symbol.KSParenthesizedReference
import org.jetbrains.kotlin.ksp.symbol.KSPropertyAccessor
import org.jetbrains.kotlin.ksp.symbol.KSPropertyDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSReferenceElement
import org.jetbrains.kotlin.ksp.symbol.KSTypeAlias
import org.jetbrains.kotlin.ksp.symbol.KSTypeArgument
import org.jetbrains.kotlin.ksp.symbol.KSTypeParameter
import org.jetbrains.kotlin.ksp.symbol.KSTypeReference
import org.jetbrains.kotlin.ksp.symbol.KSValueArgument
import java.io.File
import java.io.OutputStream

internal val mylogger = File("code-gen-ksp.log").printWriter() // file name relative to "~/.local/share/kotlin/daemon/"

public class SampleProcessor : SymbolProcessor {
    private lateinit var codeGenerator: CodeGenerator
    private lateinit var logger: KSPLogger
    override fun init(options: Map<String, String>, kotlinVersion: KotlinVersion, codeGenerator: CodeGenerator, logger: KSPLogger) {
        this.codeGenerator = codeGenerator
        this.logger = logger
        mylogger.println("LuposdateCodeGen :: init")
    }

    override fun process(resolver: Resolver) {
        mylogger.println("LuposdateCodeGen :: process")
        val files = resolver.getAllFiles()
        files.forEach { file2 ->
// CodeGeneration.generateSourceCode(className, packageName, variableName, variableValue, folderName, fileName)
            //   MyVerboseLogger.printAll("", file2)
            walkAll("", file2)
        }
    }

    private var variableName = ""
    private var packageName = ""
    private var className = ""
    private var hasAnnotation = false
    private var variableValue = ""
    override fun finish() {
        mylogger.println("LuposdateCodeGen :: finish")
        mylogger.flush()
    }

    private fun myWalkKSReferenceElement(indention: String, node: KSReferenceElement) {
        val myindent = indention + "  "
        node.typeArguments.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSCallableReference(indention: String, node: KSCallableReference) {
        val myindent = indention + "  "
        myWalkKSReferenceElement(indention, node)
        if (node.receiverType != null) {
            walkAll(myindent, node.receiverType!!)
        }
        node.functionParameters.forEach {
            walkAll(myindent, it)
        }
        walkAll(myindent, node.returnType)
    }

    private fun myWalkKSClassDeclaration(indention: String, node: KSClassDeclaration) {
        val myindent = indention + "  "
        packageName = node.packageName.asString()
        className = node.simpleName.asString()
        myWalkKSDeclaration(indention, node)
        myWalkKSDeclarationContainer(indention, node)
        if (node.primaryConstructor != null) {
            walkAll(myindent, node.primaryConstructor!!)
        }
        node.superTypes.forEach {
            walkAll(myindent, it)
        }
        node.getAllFunctions().forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSClassifierReference(indention: String, node: KSClassifierReference) {
        val myindent = indention + "  "
        myWalkKSReferenceElement(indention, node)
        if (node.qualifier != null) {
            walkAll(myindent, node.qualifier!!)
        }
    }

    private fun myWalkKSFile(indention: String, node: KSFile) {
        myWalkKSDeclarationContainer(indention, node)
        myWalkKSAnnotated(indention, node)
    }

    private fun myWalkKSDeclarationContainer(indention: String, node: KSDeclarationContainer) {
        val myindent = indention + "  "
        node.declarations.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSDynamicReference(indention: String, node: KSDynamicReference) {
        myWalkKSReferenceElement(indention, node)
    }

    private fun myWalkKSAnnotation(indention: String, node: KSAnnotation) {
        val myindent = indention + "  "
        walkAll(myindent, node.annotationType)
        node.arguments.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSAnnotated(indention: String, node: KSAnnotated) {
        val myindent = indention + "  "
        node.annotations.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSFunctionDeclaration(indention: String, node: KSFunctionDeclaration) {
        val myindent = indention + "  "
        myWalkKSDeclaration(indention, node)
        myWalkKSDeclarationContainer(indention, node)
        if (node.extensionReceiver != null) {
            walkAll(myindent, node.extensionReceiver!!)
        }
        if (node.returnType != null) {
            walkAll(myindent, node.returnType!!)
        }
        node.parameters.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSParenthesizedReference(indention: String, node: KSParenthesizedReference) {
        val myindent = indention + "  "
        walkAll(myindent, node.element)
    }

    private fun myWalkKSPropertyDeclaration(indention: String, node: KSPropertyDeclaration) {
        val myindent = indention + "  "
        hasAnnotation = false
        node.annotations.forEach { annotation ->
            if (annotation.shortName.asString() == "CodeGenerationAnnotationKSP") {
                variableValue = annotation.arguments.first().value as String
                hasAnnotation = true
            }
        }
        myWalkKSDeclaration(indention, node)
        variableName = node.simpleName.asString()
        if (hasAnnotation) {
            val out: OutputStream = codeGenerator.createNewFile(packageName, "${className}___$variableName", "kt")
            CodeGeneration.generateSourceCode(out, className, packageName, variableName, variableValue)
            //          mylogger.println("found the annotation at $packageName $className $variableName $variableValue")
            out.close()
// MyVerboseLogger.printAll("",node)
// mylogger.flush()
        }
        if (node.getter != null) {
            walkAll(myindent, node.getter!!)
        }
        if (node.setter != null) {
            walkAll(myindent, node.setter!!)
        }
        if (node.extensionReceiver != null) {
            walkAll(myindent, node.extensionReceiver!!)
        }
    }

    private fun myWalkKSTypeAlias(indention: String, node: KSTypeAlias) {
        val myindent = indention + "  "
        myWalkKSDeclaration(indention, node)
        walkAll(myindent, node.type)
    }

    private fun myWalkKSTypeParameter(indention: String, node: KSTypeParameter) {
        val myindent = indention + "  "
        myWalkKSDeclaration(indention, node)
        node.bounds.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSExpectActual(indention: String, node: KSExpectActual) {
    }

    private fun myWalkKSDeclaration(indention: String, node: KSDeclaration) {
        val myindent = indention + "  "
        myWalkKSModifierListOwner(indention, node)
        myWalkKSAnnotated(indention, node)
        myWalkKSExpectActual(indention, node)
        node.typeParameters.forEach {
            walkAll(myindent, it)
        }
    }

    private fun myWalkKSPropertyAccessor(indention: String, node: KSPropertyAccessor) {
        myWalkKSAnnotated(indention, node)
        myWalkKSModifierListOwner(indention, node)
//    walkAll(myindent, node.receiver)
    }

    private fun myWalkKSTypeReference(indention: String, node: KSTypeReference) {
        val myindent = indention + "  "
        myWalkKSAnnotated(indention, node)
        myWalkKSModifierListOwner(indention, node)
        if (node.element != null) {
            walkAll(myindent, node.element!!)
        }
    }

    private fun myWalkKSTypeArgument(indention: String, node: KSTypeArgument) {
        val myindent = indention + "  "
        myWalkKSAnnotated(indention, node)
        if (node.type != null) {
            walkAll(myindent, node.type!!)
        }
    }

    private fun myWalkKSValueArgument(indention: String, node: KSValueArgument) {
        myWalkKSAnnotated(indention, node)
    }

    private fun myWalkKSModifierListOwner(indention: String, node: KSModifierListOwner) {
    }

    private fun walkAll(indention: String, node: KSNode) {
        val myindent = indention + "  "
        if (false) {
        } else if (node is KSCallableReference) {
            myWalkKSCallableReference(myindent, node)
        } else if (node is KSClassDeclaration) {
            myWalkKSClassDeclaration(myindent, node)
        } else if (node is KSClassifierReference) {
            myWalkKSClassifierReference(myindent, node)
        } else if (node is KSFile) {
            myWalkKSFile(myindent, node)
        } else if (node is KSFunctionDeclaration) {
            myWalkKSFunctionDeclaration(myindent, node)
        } else if (node is KSParenthesizedReference) {
            myWalkKSParenthesizedReference(myindent, node)
        } else if (node is KSPropertyDeclaration) {
            myWalkKSPropertyDeclaration(myindent, node)
        } else if (node is KSTypeAlias) {
            myWalkKSTypeAlias(myindent, node)
        } else if (node is KSTypeParameter) {
            myWalkKSTypeParameter(myindent, node)
        } else if (node is KSDeclaration) {
            myWalkKSDeclaration(myindent, node)
        } else if (node is KSPropertyAccessor) {
            myWalkKSPropertyAccessor(myindent, node)
        } else if (node is KSTypeReference) {
            myWalkKSTypeReference(myindent, node)
        } else if (node is KSTypeArgument) {
            myWalkKSTypeArgument(myindent, node)
        } else if (node is KSValueArgument) {
            myWalkKSValueArgument(myindent, node)
        } else if (node is KSModifierListOwner) {
            myWalkKSModifierListOwner(myindent, node)
        } else if (node is KSAnnotated) {
            myWalkKSAnnotated(myindent, node)
        } else if (node is KSAnnotation) {
            myWalkKSAnnotation(myindent, node)
        } else if (node is KSDeclarationContainer) {
            myWalkKSDeclarationContainer(myindent, node)
        } else if (node is KSDynamicReference) {
            myWalkKSDynamicReference(myindent, node)
        }
    }
}

internal object MyVerboseLogger {

    private fun myPrintKSReferenceElement(indention: String, node: KSReferenceElement) {
        val myindent = indention + "  "
        mylogger.println("LuposdateCodeGen ::$indention typeArguments")
        node.typeArguments.forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSCallableReference(indention: String, node: KSCallableReference) {
        val myindent = indention + "  "
        myPrintKSReferenceElement(indention, node)
        if (node.receiverType != null) {
            mylogger.println("LuposdateCodeGen ::$indention receiverType")
            printAll(myindent, node.receiverType!!)
        }
        mylogger.println("LuposdateCodeGen ::$indention functionParameters")
        node.functionParameters.forEach {
            printAll(myindent, it)
        }
        mylogger.println("LuposdateCodeGen ::$indention returnType")
        printAll(myindent, node.returnType)
    }

    private fun myPrintKSClassDeclaration(indention: String, node: KSClassDeclaration) {
        val myindent = indention + "  "
        myPrintKSDeclaration(indention, node)
        myPrintKSDeclarationContainer(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention classKind ${node.classKind}")
        if (node.primaryConstructor != null) {
            mylogger.println("LuposdateCodeGen ::$indention primaryConstructor")
            printAll(myindent, node.primaryConstructor!!)
        }
        mylogger.println("LuposdateCodeGen ::$indention superTypes")
        node.superTypes.forEach {
            printAll(myindent, it)
        }
        mylogger.println("LuposdateCodeGen ::$indention isCompanionObject ${node.isCompanionObject}")
        mylogger.println("LuposdateCodeGen ::$indention getAllFunctions")
        node.getAllFunctions().forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSClassifierReference(indention: String, node: KSClassifierReference) {
        val myindent = indention + "  "
        myPrintKSReferenceElement(indention, node)
        if (node.qualifier != null) {
            mylogger.println("LuposdateCodeGen ::$indention qualifier")
            printAll(myindent, node.qualifier!!)
        }
        mylogger.println("LuposdateCodeGen ::$indention referencedName ${node.referencedName()}")
    }

    private fun myPrintKSFile(indention: String, node: KSFile) {
        myPrintKSDeclarationContainer(indention, node)
        myPrintKSAnnotated(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention packageName ${node.packageName.asString()}")
        mylogger.println("LuposdateCodeGen ::$indention fileName ${node.fileName}")
    }

    private fun myPrintKSDeclarationContainer(indention: String, node: KSDeclarationContainer) {
        val myindent = indention + "  "
        mylogger.println("LuposdateCodeGen ::$indention declarations")
        node.declarations.forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSDynamicReference(indention: String, node: KSDynamicReference) {
        myPrintKSReferenceElement(indention, node)
    }

    private fun myPrintKSAnnotation(indention: String, node: KSAnnotation) {
        val myindent = indention + "  "
        mylogger.println("LuposdateCodeGen ::$indention annotationType")
        printAll(myindent, node.annotationType)
        mylogger.println("LuposdateCodeGen ::$indention arguments")
        node.arguments.forEach {
            printAll(myindent, it)
        }
        mylogger.println("LuposdateCodeGen ::$indention shortName ${node.shortName.asString()}")
        mylogger.println("LuposdateCodeGen ::$indention useSiteTarget ${node.useSiteTarget}")
    }

    private fun myPrintKSAnnotated(indention: String, node: KSAnnotated) {
        val myindent = indention + "  "
        mylogger.println("LuposdateCodeGen ::$indention annotations")
        node.annotations.forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSFunctionDeclaration(indention: String, node: KSFunctionDeclaration) {
        val myindent = indention + "  "
        myPrintKSDeclaration(indention, node)
        myPrintKSDeclarationContainer(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention functionKind ${node.functionKind}")
        mylogger.println("LuposdateCodeGen ::$indention isAbstract ${node.isAbstract}")
        if (node.extensionReceiver != null) {
            mylogger.println("LuposdateCodeGen ::$indention extensionReceiver")
            printAll(myindent, node.extensionReceiver!!)
        }
        if (node.returnType != null) {
            mylogger.println("LuposdateCodeGen ::$indention returnType")
            printAll(myindent, node.returnType!!)
        }
        mylogger.println("LuposdateCodeGen ::$indention parameters")
        node.parameters.forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSParenthesizedReference(indention: String, node: KSParenthesizedReference) {
        val myindent = indention + "  "
        mylogger.println("LuposdateCodeGen ::$indention element")
        printAll(myindent, node.element)
    }

    private fun myPrintKSPropertyDeclaration(indention: String, node: KSPropertyDeclaration) {
        val myindent = indention + "  "
        myPrintKSDeclaration(indention, node)
        if (node.getter != null) {
            mylogger.println("LuposdateCodeGen ::$indention getter")
            printAll(myindent, node.getter!!)
        }
        if (node.setter != null) {
            mylogger.println("LuposdateCodeGen ::$indention setter")
            printAll(myindent, node.setter!!)
        }
        if (node.extensionReceiver != null) {
            mylogger.println("LuposdateCodeGen ::$indention extensionReceiver")
            printAll(myindent, node.extensionReceiver!!)
        }
        mylogger.println("LuposdateCodeGen ::$indention type ${node.type}")
        mylogger.println("LuposdateCodeGen ::$indention isMutable ${node.isMutable}")
        mylogger.println("LuposdateCodeGen ::$indention isDelegated ${node.isDelegated()}")
    }

    private fun myPrintKSTypeAlias(indention: String, node: KSTypeAlias) {
        val myindent = indention + "  "
        myPrintKSDeclaration(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention name ${node.name}")
        mylogger.println("LuposdateCodeGen ::$indention type")
        printAll(myindent, node.type)
    }

    private fun myPrintKSTypeParameter(indention: String, node: KSTypeParameter) {
        val myindent = indention + "  "
        myPrintKSDeclaration(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention name ${node.name}")
        mylogger.println("LuposdateCodeGen ::$indention variance ${node.variance}")
        mylogger.println("LuposdateCodeGen ::$indention isReified ${node.isReified}")
        mylogger.println("LuposdateCodeGen ::$indention bounds")
        node.bounds.forEach {
            printAll(myindent, it)
        }
    }

    private fun myPrintKSExpectActual(indention: String, node: KSExpectActual) {
        mylogger.println("LuposdateCodeGen ::$indention isActual ${node.isActual}")
        mylogger.println("LuposdateCodeGen ::$indention isExpect ${node.isExpect}")
    }

    private fun myPrintKSDeclaration(indention: String, node: KSDeclaration) {
        val myindent = indention + "  "
        myPrintKSModifierListOwner(indention, node)
        myPrintKSAnnotated(indention, node)
        myPrintKSExpectActual(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention simpleName ${node.simpleName.asString()}")
        mylogger.println("LuposdateCodeGen ::$indention qualifiedName ${node.qualifiedName?.asString()}")
        mylogger.println("LuposdateCodeGen ::$indention typeParameters")
        node.typeParameters.forEach {
            printAll(myindent, it)
        }
        mylogger.println("LuposdateCodeGen ::$indention packageName ${node.packageName.asString()}")
    }

    private fun myPrintKSPropertyAccessor(indention: String, node: KSPropertyAccessor) {
        myPrintKSAnnotated(indention, node)
        myPrintKSModifierListOwner(indention, node)
//    mylogger.println("LuposdateCodeGen ::$indention receiver")
//    printAll(myindent, node.receiver)
    }

    private fun myPrintKSTypeReference(indention: String, node: KSTypeReference) {
        val myindent = indention + "  "
        myPrintKSAnnotated(indention, node)
        myPrintKSModifierListOwner(indention, node)
        if (node.element != null) {
            mylogger.println("LuposdateCodeGen ::$indention element")
            printAll(myindent, node.element!!)
        }
    }

    private fun myPrintKSTypeArgument(indention: String, node: KSTypeArgument) {
        val myindent = indention + "  "
        myPrintKSAnnotated(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention variance ${node.variance}")
        if (node.type != null) {
            mylogger.println("LuposdateCodeGen ::$indention type")
            printAll(myindent, node.type!!)
        }
    }

    private fun myPrintKSValueArgument(indention: String, node: KSValueArgument) {
        myPrintKSAnnotated(indention, node)
        mylogger.println("LuposdateCodeGen ::$indention name ${node.name?.asString()}")
        mylogger.println("LuposdateCodeGen ::$indention isSpread ${node.isSpread}")
        mylogger.println("LuposdateCodeGen ::$indention value ${node.value}")
    }

    private fun myPrintKSModifierListOwner(indention: String, node: KSModifierListOwner) {
        mylogger.println("LuposdateCodeGen ::$indention modifiers ${node.modifiers}")
    }

    internal fun printAll(indention: String, node: KSNode) {
        val myindent = indention + "  "
        if (false) {
        } else if (node is KSCallableReference) {
            mylogger.println("LuposdateCodeGen ::$indention KSCallableReference")
            myPrintKSCallableReference(myindent, node)
        } else if (node is KSClassDeclaration) {
            mylogger.println("LuposdateCodeGen ::$indention KSClassDeclaration")
            myPrintKSClassDeclaration(myindent, node)
        } else if (node is KSClassifierReference) {
            mylogger.println("LuposdateCodeGen ::$indention KSClassifierReference")
            myPrintKSClassifierReference(myindent, node)
        } else if (node is KSFile) {
            mylogger.println("LuposdateCodeGen ::$indention KSFile")
            myPrintKSFile(myindent, node)
        } else if (node is KSFunctionDeclaration) {
            mylogger.println("LuposdateCodeGen ::$indention KSFunctionDeclaration")
            myPrintKSFunctionDeclaration(myindent, node)
        } else if (node is KSParenthesizedReference) {
            mylogger.println("LuposdateCodeGen ::$indention KSParenthesizedReference")
            myPrintKSParenthesizedReference(myindent, node)
        } else if (node is KSPropertyDeclaration) {
            mylogger.println("LuposdateCodeGen ::$indention KSPropertyDeclaration")
            myPrintKSPropertyDeclaration(myindent, node)
        } else if (node is KSTypeAlias) {
            mylogger.println("LuposdateCodeGen ::$indention KSTypeAlias")
            myPrintKSTypeAlias(myindent, node)
        } else if (node is KSTypeParameter) {
            mylogger.println("LuposdateCodeGen ::$indention KSTypeParameter")
            myPrintKSTypeParameter(myindent, node)
        } else if (node is KSDeclaration) {
            mylogger.println("LuposdateCodeGen ::$indention KSDeclaration")
            myPrintKSDeclaration(myindent, node)
        } else if (node is KSPropertyAccessor) {
            mylogger.println("LuposdateCodeGen ::$indention KSPropertyAccessor")
            myPrintKSPropertyAccessor(myindent, node)
        } else if (node is KSTypeReference) {
            mylogger.println("LuposdateCodeGen ::$indention KSTypeReference")
            myPrintKSTypeReference(myindent, node)
        } else if (node is KSTypeArgument) {
            mylogger.println("LuposdateCodeGen ::$indention KSTypeArgument")
            myPrintKSTypeArgument(myindent, node)
        } else if (node is KSValueArgument) {
            mylogger.println("LuposdateCodeGen ::$indention KSValueArgument")
            myPrintKSValueArgument(myindent, node)
        } else if (node is KSModifierListOwner) {
            mylogger.println("LuposdateCodeGen ::$indention KSModifierListOwner")
            myPrintKSModifierListOwner(myindent, node)
        } else if (node is KSAnnotated) {
            mylogger.println("LuposdateCodeGen ::$indention KSAnnotated")
            myPrintKSAnnotated(myindent, node)
        } else if (node is KSAnnotation) {
            mylogger.println("LuposdateCodeGen ::$indention KSAnnotation")
            myPrintKSAnnotation(myindent, node)
        } else if (node is KSDeclarationContainer) {
            mylogger.println("LuposdateCodeGen ::$indention KSDeclarationContainer")
            myPrintKSDeclarationContainer(myindent, node)
        } else if (node is KSDynamicReference) {
            mylogger.println("LuposdateCodeGen ::$indention KSDynamicReference")
            myPrintKSDynamicReference(myindent, node)
        }
    }
}
