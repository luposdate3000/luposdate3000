package lupos
@Target(
AnnotationTarget.ANNOTATION_CLASS, 
AnnotationTarget.CLASS, 
AnnotationTarget.CONSTRUCTOR, 
AnnotationTarget.FIELD, 
AnnotationTarget.FILE, 
AnnotationTarget.FUNCTION, 
AnnotationTarget.LOCAL_VARIABLE, 
AnnotationTarget.PROPERTY, 
AnnotationTarget.PROPERTY_GETTER, 
AnnotationTarget.PROPERTY_SETTER, 
AnnotationTarget.TYPE, 
AnnotationTarget.TYPEALIAS, 
AnnotationTarget.TYPE_PARAMETER, 
AnnotationTarget.VALUE_PARAMETER 
)
@Retention(AnnotationRetention.BINARY)
public annotation class ProguardKeepAnnotation
