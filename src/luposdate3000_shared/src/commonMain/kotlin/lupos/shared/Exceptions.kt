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

// evaluation exceptions --->>>
public abstract class EvaluationException public constructor(classname: String, msg: String) : Luposdate3000Exception(classname, msg)
public class UnknownDataFileException public constructor(filename: String) : EvaluationException("UnknownDataFileException", "Unknown filetype '$filename' during parsing to xml.")
public class EnpointRecievedInvalidPath public constructor(path: String) : EvaluationException("EnpointRecievedInvalidPath", "There was a not recognized request with path '$path'.")
public class UnreachableException public constructor() : EvaluationException("UnreachableException", "This should be unreachable.")
public class EmptyResultException public constructor() : EvaluationException("EmptyResultException", "")
public class TooManyIntermediateResultsException public constructor():EvaluationException("TooManyIntermediateResultsException","Too many intermediate results")

// known bugs --->>>
public class BugException public constructor(classname: String, bugname: String) : Luposdate3000Exception("BugException", "class '$classname' has bug '$bugname'.")

public class JenaBugException public constructor(bugname: String) : Luposdate3000Exception("JenaBugException", "Jena has bug: '$bugname'")
