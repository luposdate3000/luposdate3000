package lupos.s00misc
import kotlin.jvm.JvmField
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS,
 AnnotationTarget.FUNCTION,
 AnnotationTarget.VALUE_PARAMETER,
 AnnotationTarget.EXPRESSION,
AnnotationTarget.PROPERTY,
AnnotationTarget.FIELD,
AnnotationTarget.LOCAL_VARIABLE
)
public annotation class CodeGenerationAnnotation(@JvmField public val query:String)
