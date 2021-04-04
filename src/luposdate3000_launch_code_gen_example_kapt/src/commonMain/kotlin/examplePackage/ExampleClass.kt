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
package examplePackage
import lupos.s00misc.CodeGenerationAnnotation
import kotlin.jvm.JvmField
import lupos.endpoint.LuposdateEndpoint

public class ExampleClass {
    init {
        LuposdateEndpoint.importTurtleFiles(
            "resources/code-generation/example.n3",
            mutableMapOf()
        )
    }
    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String = "SELECT ?article ?pages WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER (?pages < 11)}"
    //public val exampleVar: String = "SELECT ?pages (?pages < 9 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages}"
    //public val exampleVar: String = "SELECT ?pages ?article (?pages/0 < 9 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER (?pages<= 20)}"
    //public val exampleVar: String = "SELECT ?pages ?article ?title  WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title .filter(?pages > 100)}"
    //public val exampleVar: String = "SELECT ?pages ?article (?pages as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages }"
    //public val exampleVar: String = "SELECT ?pages ?article ?pages2  WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://swrc.ontoware.org/ontology#pages> ?pages2 }"
    //public val exampleVar : String = "SELECT ?a ?b ?c WHERE {?a <a> ?b . ?a <b> ?c .}"
}

