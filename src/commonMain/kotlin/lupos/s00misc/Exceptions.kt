package lupos.s00misc

import lupos.s00misc.Coverage

/* explicitly storing the classname has the advantage, that the classname is accessible in native code too, and not just in java via reflection */
abstract class Luposdate3000Exception(val classname: String, msg: String) : Exception(msg)

//
abstract class NotImplementedException(classname: String, msg: String) : Luposdate3000Exception(classname, msg)

class HistogramNotImplementedException() : NotImplementedException("HistogramNotImplementedException", "Histograms are not implemented in this triple store index.")
class FileIONotImplementedException() : NotImplementedException("FileIONotImplementedException", "File IO not implemented.")
class ServiceNotImplementedException() : NotImplementedException("ServiceNotImplementedException", "Service is currently not implemented.")
class TripleStoreModifyOperationsNotImplementedException() : NotImplementedException("TripleStoreModifyOperationsNotImplementedException", "Triple store has not implemented Insert and Delete.")
class IteratorBundleColumnModeNotImplementedException() : NotImplementedException("IteratorBundleColumnModeNotImplementedException", "IteratorBundle is unable to convert to column Mode.")
class IteratorBundleRowModeNotImplementedException() : NotImplementedException("IteratorBundleRowModeNotImplementedException", "IteratorBundle is unable to convert to row Mode.")
class SparqlFeatureNotImplementedException(name: String) : NotImplementedException("SparqlFeatureNotImplementedException", "Sparql feature '$name' not implemented.")
//
class FunktionWontWorkWithThisImplementationException() : Luposdate3000Exception("FunktionWontWorkWithThisImplementationException", "Funktion should not work with this implementation of the interface.")

class DictionaryCanNotInferTypeFromValueException() : Luposdate3000Exception("DictionaryCanNotInferTypeFromValueException", "Dictionary can not infer the type of given Value.")
//
abstract class SyntaxException(classname: String, msg: String) : Luposdate3000Exception(classname, msg)

class RecoursiveVariableDefinitionSyntaxException(name: String) : SyntaxException("RecoursiveVariableDefinitionSyntaxException", "Recoursive variable definition not allowed '$name'.")
class ProjectionDoubleDefinitionOfVariableSyntaxException(name: String) : SyntaxException("DoubleDefinitionOfVariableSyntaxException", "Projection must not contain same variable as bind and selection '${name}'.")
class AggregateNotAllowedSyntaxException : SyntaxException("AggregateNotAllowedSyntaxException", "Aggregates are not allowed here.")
class VariableNotDefinedSyntaxException(name: String) : SyntaxException("VariableNotDefinedSyntaxException", "Variable '$name' unknown here.")
//
