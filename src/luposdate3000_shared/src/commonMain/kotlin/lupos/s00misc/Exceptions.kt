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
/* explicitly storing the classname has the advantage, that the classname is accessible in native code too, and not just via reflection */
public abstract class Luposdate3000Exception public constructor(public val classname: String, msg: String) : Exception(msg)
// not implemented exceptions --->>>
public open class NotImplementedException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class HistogramNotImplementedException public constructor(classname: String) : NotImplementedException("HistogramNotImplementedException", "Histograms not implemented in '$classname'.")
public class IteratorBundleColumnModeNotImplementedException public constructor() : NotImplementedException("IteratorBundleColumnModeNotImplementedException", "IteratorBundle is unable to convert to column Mode.")
public class IteratorBundleRowModeNotImplementedException public constructor() : NotImplementedException("IteratorBundleRowModeNotImplementedException", "IteratorBundle is unable to convert to row Mode.")
public class SparqlFeatureNotImplementedException public constructor(name: String) : NotImplementedException("SparqlFeatureNotImplementedException", "Sparql feature '$name' not implemented.")
public class EvaluateNotImplementedException public constructor(classname: String) : NotImplementedException("EvaluateNotImplementedException", "Evaluate not implemented in '$classname'.")
public class ToSparqlNotImplementedException public constructor(classname: String) : NotImplementedException("ToSparqlNotImplementedException", "toSparql not implemented in '$classname'.")
public class GraphVariablesNotImplementedException public constructor(classname: String) : NotImplementedException("GraphVariablesNotImplementedException", "variables at graph-name position are currently not implemented")
public class UnknownManifestException public constructor(classname: String, msg: String) : NotImplementedException("UnknownManifestException", "$classname :: $msg")
public class DirectoryCompareNotImplementedException public constructor() : NotImplementedException("DirectoryCompareNotImplementedException", "Comparing directories is not implemented")
public class GraphVarHistogramsNotImplementedException public constructor() : NotImplementedException("GraphVarHistogramsNotImplementedException", "histograms for triples using graph variable not implemented.")
public class TriplePatternsContainingTheSameVariableTwiceNotImplementedException public constructor() : NotImplementedException("TriplePatternsContainingTheSameVariableTwiceNotImplementedException", "triple pattern currently must not contain the same variable twice.")
// syntax exceptions --->>>
public abstract class SyntaxException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class RecoursiveVariableDefinitionSyntaxException public constructor(name: String) : SyntaxException("RecoursiveVariableDefinitionSyntaxException", "Recoursive variable definition not allowed '$name'.")
public class ProjectionDoubleDefinitionOfVariableSyntaxException public constructor(name: String) : SyntaxException("DoubleDefinitionOfVariableSyntaxException", "Projection must not contain same variable as bind and selection '$name'.")
public class AggregateNotAllowedSyntaxException public constructor() : SyntaxException("AggregateNotAllowedSyntaxException", "Aggregates are not allowed here.")
public class VariableNotDefinedSyntaxException public constructor(classname: String, name: String) : SyntaxException("VariableNotDefinedSyntaxException", "Variable '$name' unknown within '$classname'.")
public class GroupByClauseNotUsedException public constructor() : SyntaxException("GroupByClauseNotUsedException", "expression within group-by-clauses must be bound to a variable.")
public class GroupByColumnMissing public constructor(name: String) : SyntaxException("GroupByColumnMissing", "Group By requires the column '$name', which does not exist within this GroupBy-Clause.")
public class GroupByDuplicateColumnException public constructor() : SyntaxException("GroupByDuplicateColumnException", "no duplicate columns allowed in group-by.")
public class XMLNotParseableException public constructor() : SyntaxException("XMLNotParseableException", "Xml is not parseable.")
// evaluation exceptions --->>>
public abstract class EvaluationException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class DatasetImportFailedException public constructor(file: String) : EvaluationException("DatasetImportFailedException", "importing the dataset '$file' failed")
public class IncompatibleTypesDuringCompareException public constructor() : EvaluationException("IncompatibleTypesDuringCompareException", "The provided types are incompatible.")
public class CanNotCastBNodeToDoubleException public constructor() : EvaluationException("CanNotCastBNodeToDoubleException", "Can not cast BNode to Double.")
public class CanNotCastBNodeToDecimalException public constructor() : EvaluationException("CanNotCastBNodeToDecimalException", "Can not cast BNode to Decimal.")
public class CanNotCastBNodeToIntException public constructor() : EvaluationException("CanNotCastBNodeToIntException", "Can not cast BNode to Int.")
public class CanNotCastBNodeToBooleanException public constructor() : EvaluationException("CanNotCastBNodeToBooleanException", "Can not cast BNode to Boolean.")
public class CanNotCastBooleanToDoubleException public constructor() : EvaluationException("CanNotCastBooleanToDoubleException", "Can not cast Boolean to Double.")
public class CanNotCastBooleanToDecimalException public constructor() : EvaluationException("CanNotCastBooleanToDecimalException", "Can not cast Boolean to Decimal.")
public class CanNotCastBooleanToIntException public constructor() : EvaluationException("CanNotCastBooleanToIntException", "Can not cast Boolean to Int.")
public class CanNotCastUndefToDoubleException public constructor() : EvaluationException("CanNotCastUndefToDoubleException", "Can not cast Undef to Double.")
public class CanNotCastUndefToDecimalException public constructor() : EvaluationException("CanNotCastUndefToDecimalException", "Can not cast Undef to Decimal.")
public class CanNotCastUndefToIntException public constructor() : EvaluationException("CanNotCastUndefToIntException", "Can not cast Undef to Int.")
public class CanNotCastUndefToBooleanException public constructor() : EvaluationException("CanNotCastUndefToBooleanException", "Can not cast Undef to Boolean.")
public class CanNotCastErrorToDoubleException public constructor() : EvaluationException("CanNotCastErrorToDoubleException", "Can not cast Error to Double.")
public class CanNotCastErrorToDecimalException public constructor() : EvaluationException("CanNotCastErrorToDecimalException", "Can not cast Error to Decimal.")
public class CanNotCastErrorToIntException public constructor() : EvaluationException("CanNotCastErrorToIntException", "Can not cast Error to Int.")
public class CanNotCastErrorToBooleanException public constructor() : EvaluationException("CanNotCastErrorToBooleanException", "Can not cast Error to Boolean.")
public class CanNotCastIriToDoubleException public constructor() : EvaluationException("CanNotCastIriToDoubleException", "Can not cast Iri to Double.")
public class CanNotCastIriToDecimalException public constructor() : EvaluationException("CanNotCastIriToDecimalException", "Can not cast Iri to Decimal.")
public class CanNotCastIriToIntException public constructor() : EvaluationException("CanNotCastIriToIntException", "Can not cast Iri to Int.")
public class CanNotCastIriToBooleanException public constructor() : EvaluationException("CanNotCastIriToBooleanException", "Can not cast Iri to Boolean.")
public class CanNotCastDateTimeToDoubleException public constructor() : EvaluationException("CanNotCastDateTimeToDoubleException", "Can not cast DateTime to Double.")
public class CanNotCastDateTimeToDecimalException public constructor() : EvaluationException("CanNotCastDateTimeToDecimalException", "Can not cast DateTime to Decimal.")
public class CanNotCastDateTimeToIntException public constructor() : EvaluationException("CanNotCastDateTimeToIntException", "Can not cast DateTime to Int.")
public class CanNotCastDateTimeToBooleanException public constructor() : EvaluationException("CanNotCastDateTimeToBooleanException", "Can not cast DateTime to Boolean.")
public class CanNotCastLiteralToDoubleException public constructor() : EvaluationException("CanNotCastLiteralToDoubleException", "Can not cast Literal to Double.")
public class CanNotCastLiteralToDecimalException public constructor() : EvaluationException("CanNotCastLiteralToDecimalException", "Can not cast Literal to Decimal.")
public class CanNotCastLiteralToIntException public constructor() : EvaluationException("CanNotCastLiteralToIntException", "Can not cast Literal to Int.")
public class UnknownOperatorTypeInXMLException public constructor(type: String) : EvaluationException("UnknownOperatorTypeInXMLException", "Unknown type '$type' during parsing xml file.")
public class UnknownDataFileException public constructor(filename: String) : EvaluationException("UnknownDataFileException", "Unknown filetype '$filename' during parsing to xml.")
public class EnpointRecievedInvalidPath public constructor(path: String) : EvaluationException("EnpointRecievedInvalidPath", "There was a not recognized request with path '$path'.")
public class GraphNameAlreadyExistsDuringCreateException public constructor(name: String) : EvaluationException("GraphNameAlreadyExistsDuringCreateException", "The graph '$name' already exists before creation.")
public class GraphNameNotExistsDuringDeleteException public constructor(name: String) : EvaluationException("GraphNameNotExistsDuringDeleteException", "The graph '$name' did not exist before deletion.")
public class GraphNameNotFoundException public constructor(name: String) : EvaluationException("GraphNameNotFoundException", "The graph '$name' does not exist.")
public class UnreachableException public constructor() : EvaluationException("UnreachableException", "This should be unreachable.")
public class EmptyResultException public constructor() : EvaluationException("EmptyResultException", "")
// known bugs --->>>
public class BugException public constructor(classname: String, bugname: String) : Luposdate3000Exception("BugException", "class '$classname' has bug '$bugname'.")
// used to indicate, that intentionally every exception is caught here
public typealias DontCareWhichException = Throwable
public class JenaBugException public constructor(bugname: String) : Luposdate3000Exception("JenaBugException", "Jena has bug: '$bugname'")
