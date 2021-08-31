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
package lupos.code_gen_test_12
import lupos.shared.inline.File

public class MedicaltemporalproximitybyexclusionNOTEXISTS {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/MedicaltemporalproximitybyexclusionNOTEXISTS.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/MedicaltemporalproximitybyexclusionNOTEXISTS.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX ex: <http://www.w3.org/2009/sparql/docs/tests/data-sparql11/negation#> \n" +
        "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n" +
        "# The closest pre-operative physical examination \n" +
        "SELECT ?exam ?date {  \n" +
        "  ?exam a ex:PhysicalExamination;  \n" +
        "        dc:date ?date; \n" +
        "        ex:precedes ex:operation1 . \n" +
        "  ?op   a ex:SurgicalProcedure; dc:date ?opDT . \n" +
        "  FILTER NOT EXISTS { \n" +
        "    ?otherExam a ex:PhysicalExamination;  \n" +
        "               ex:follows ?exam; \n" +
        "               ex:precedes ex:operation1 \n" +
        "  }  \n" +
        "}"
}
