package lupos.s00misc

/* explicitly storing the classname has the advantage, that the classname is accessible in native code too, and not just in java via reflection */
abstract class Luposdate3000Exception(val classname: String, msg: String) : Exception(msg)

abstract class NotImplementedException(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
class HistogramNotImplementedException() : NotImplementedException("HistogramNotImplementedException", "Histograms are not implemented for this triple store index.")
class FileIONotImplementedException() : NotImplementedException("FileIONotImplementedException", "File IO not implemented.")
class ServiceNotImplementedException() : NotImplementedException("ServiceNotImplementedException", "Service is currently not implemented.")
class SetNotImplementedException() : NotImplementedException("SetNotImplementedException", "Set is currently not implemented.")
class TripleStoreModifyOperationsNotImplementedException() : NotImplementedException("TripleStoreModifyOperationsNotImplementedException", "Triple store has not implemented Insert and Delete.")
class IteratorBundleColumnModeNotImplementedException() : NotImplementedException("IteratorBundleColumnModeNotImplementedException", "IteratorBundle is unable to convert to column Mode.")
class IteratorBundleRowModeNotImplementedException() : NotImplementedException("IteratorBundleRowModeNotImplementedException", "IteratorBundle is unable to convert to row Mode.")
class FunktionWontWorkWithThisImplementationException() : Luposdate3000Exception("FunktionWontWorkWithThisImplementationException", "Funktion should not work with this implementation of the interface.")
class DatasetsNotImplementedException() : NotImplementedException("DatasetsNotImplementedException", "Datasets are not supported.")
