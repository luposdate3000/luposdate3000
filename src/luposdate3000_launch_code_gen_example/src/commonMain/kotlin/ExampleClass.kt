package examplePackage
import lupos.s00misc.CodeGenerationAnnotation
import kotlin.jvm.JvmField
public class ExampleClass{
@JvmField
@CodeGenerationAnnotation(query="exampleQuery in annotation")
public val  exampleVar:String="exampleQuery"
}
