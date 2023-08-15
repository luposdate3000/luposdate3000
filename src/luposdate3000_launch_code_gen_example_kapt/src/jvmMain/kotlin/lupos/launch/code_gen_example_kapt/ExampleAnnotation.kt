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
package lupos.launch.code_gen_example_kapt

import lupos.endpoint.LuposdateEndpoint
import lupos.shared.CodeGenerationAnnotation
import lupos.shared.Luposdate3000Instance
import kotlin.jvm.JvmField

public class ExampleAnnotation(private val instance: Luposdate3000Instance) {
    // Importing a turtle file to query on
    init {
        LuposdateEndpoint.importTripleFile(
            instance,
            "resources/code-generation/example.n3",
        )
    }

    // The actual annotation, the generated function will be called exampleVar_evaluate() and will return the result of
    //  the query as a String
    @JvmField
    @CodeGenerationAnnotation
    // public val exampleVar: String = "SELECT ?article ?pages (?pages < 50 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages < 15)}"
    // public val exampleVar: String = "SELECT ?pages ?article (?pages < 100 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages}"
    // public val exampleVar: String = "SELECT ?pages ?article (?pages/0 < 9 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER (?pages<= 20)}"
    public val exampleVar: String = "SELECT ?pages ?article ?title  WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"
    // public val exampleVar: String = "SELECT ?pages ?article (?pages as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages }"
    // public val exampleVar: String = "SELECT ?pages ?article ?pages2  WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://swrc.ontoware.org/ontology#pages> ?pages2 }"
    // public val exampleVar : String = "SELECT ?a ?b ?c WHERE {?a <a> ?b . ?a <b> ?c .}"
}
