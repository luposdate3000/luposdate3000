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

package lupos.simulator_db.unit

import simora.simulator_iot.Evaluation
import lupos.test.SparqlTestSuiteConverterToUnitTest
import kotlin.test.Test

class IntegrationTest {
    /*
    SELECT count(*) WHERE {?s ?p ?o .}
    SELECT (count(*) + 1.0 as ?c) WHERE {?s ?p ?o .}
    */
    @Test
    fun test_campusNoSamples_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campusNoSamples_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campusNoSamples_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campusNoSamples_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campusNoSamples_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campusNoSamples_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campusNoSamples_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campusNoSamples_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingFull4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingFull4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRandom4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRandom4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingRing4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingRing4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributed_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q0_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastEnabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q1_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q1_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q2_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q2_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q3_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q3_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q4_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q4_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q5_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q5_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q6_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q6_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q7_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q7_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q8_luposdate3000_by_id_S_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }

    @Test
    fun test_campus_scenarioParkingUniform4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast() {
        if (!SparqlTestSuiteConverterToUnitTest.minifyMode) {
            Evaluation().evalConfigFileMerge(listOf("../luposdate3000_simulator_db/src/jvmTest/resources/_campus_scenarioParkingUniform4_distributedWithQueryHops_Q8_luposdate3000_by_key_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled_routing_RPL_Fast.json"))
        }
    }
}
