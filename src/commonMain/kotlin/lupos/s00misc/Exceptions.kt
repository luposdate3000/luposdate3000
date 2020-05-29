package lupos.s00misc

class HistogramNotImplementedException() : Exception("Histograms are not implemented for this triple store index.")
class FileIONotImplementedException() : Exception("File IO not implemented.")
class ServiceNotImplementedException() : Exception("Service is currently not implemented.")
class SetNotImplementedException() : Exception("Set is currently not implemented.")
class TripleStoreModifyOperationsNotImplementedException() : Exception("Triple store has not implemented Insert and Delete.")
class IteratorBundleColumnModeNotImplementedException() : Exception("IteratorBundle is unable to convert to column Mode.")
class IteratorBundleRowModeNotImplementedException() : Exception("IteratorBundle is unable to convert to row Mode.")
class FunktionWontWorkWithThisImplementationException() : Exception("Funktion should not work with this implementation of the interface.")
class DatasetsNotImplementedException() : Exception("Datasets are not supported.")
