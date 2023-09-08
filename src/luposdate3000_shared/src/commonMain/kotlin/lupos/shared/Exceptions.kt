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
package lupos.shared
import kotlin.jvm.JvmField

/* explicitly storing the classname has the advantage, that the classname is accessible in native code too, and not just via reflection */
public abstract class Luposdate3000Exception public constructor(@JvmField public val classname: String, msg: String) : Exception(msg)

// syntax exceptions --->>>
public abstract class SyntaxException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class VariableNotDefinedSyntaxException public constructor(classname: String, name: String) : SyntaxException("VariableNotDefinedSyntaxException", "Variable '$name' unknown within '$classname'.")
public class GroupByColumnMissing public constructor(name: String) : SyntaxException("GroupByColumnMissing", "Group By requires the column '$name', which does not exist within this GroupBy-Clause.")
public class GroupByDuplicateColumnException public constructor() : SyntaxException("GroupByDuplicateColumnException", "no duplicate columns allowed in group-by.")
public class XMLNotParseableException public constructor() : SyntaxException("XMLNotParseableException", "Xml is not parseable.")
public class UnableToOutputResultException public constructor() : SyntaxException("UnableToOutputResultException", "Unable to output the desired result format.")
public class IncompatibleInputException public constructor() : SyntaxException("IncompatibleInputException", "incompatible input.")

// evaluation exceptions --->>>
public abstract class EvaluationException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class UnknownDataFileException public constructor(filename: String) : EvaluationException("UnknownDataFileException", "Unknown filetype '$filename' during parsing to xml.")
public class EnpointRecievedInvalidPath public constructor(path: String) : EvaluationException("EnpointRecievedInvalidPath", "There was a not recognized request with path '$path'.")
public class UnreachableException public constructor() : EvaluationException("UnreachableException", "This should be unreachable.")
public class EmptyResultException public constructor() : EvaluationException("EmptyResultException", "")
public class TooManyIntermediateResultsException public constructor() : EvaluationException("TooManyIntermediateResultsException", "Too many intermediate results")
public class UnknownTripleStoreTypeException public constructor() : EvaluationException("UnknownTripleStoreTypeException", "Unknown TripleStoreType")
public class NoValidIndexFoundException public constructor() : EvaluationException("NoValidIndexFoundException", "No valid index found.")
public class GraphAlreadyExistsException public constructor() : EvaluationException("GraphAlreadyExistsException", "Graph already exists.")
public class ConnectionFailedException public constructor(status: Int?) : EvaluationException("ConnectionFailedException", "Remote returned $status.")
public class NoMorePagesException public constructor() : EvaluationException("NoMorePagesException", "No more pages available.")
public class OperationCanNotBeLocalException public constructor() : EvaluationException("OperationCanNotBeLocalException", "Operation can not be local.")
public class EOFException public constructor(avail: Any? = null) : EvaluationException("EOFException", "eof (available=$avail)")
public class IncompatibleTypesDuringCompareException public constructor() : EvaluationException("IncompatibleTypesDuringCompareException", "provided types can not be compared")
public class InvalidInputException public constructor(input: String? = null) : EvaluationException("InvalidInputException", "Invalid input '$input'.")
public class CrashInputStreamException public constructor() : EvaluationException("CrashInputStreamException", "Not implemented network transmission.")

// known bugs --->>>
public class BugException public constructor(classname: String, bugname: String) : Luposdate3000Exception("BugException", "class '$classname' has bug '$bugname'.")

public class JenaBugException public constructor(bugname: String) : Luposdate3000Exception("JenaBugException", "Jena has bug: '$bugname'")

public fun Throwable.myPrintStackTrace(location: String) {
    println(stackTraceToString())
    println("caught at $location")
}
public fun Throwable.myPrintStackTraceAndThrowAgain(location: String): Nothing {
    println(stackTraceToString())
    println("re-throw at $location")
    throw this
}
