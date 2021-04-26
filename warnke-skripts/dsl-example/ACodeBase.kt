package lupos.codegen

@CodeDSLMarker
abstract class ACodeBase() {
    abstract fun prepareImports(parentFile: CodeFile)
}
