import lupos.code_gen_test_00.DELETEINSERT5b
import lupos.code_gen_test_00.MOVE6
import lupos.code_gen_test_00.resourcesbsbmbiquery22553sparql2553
import lupos.code_gen_test_00.resourcesbsbmbiquery51853sparql1853
import lupos.code_gen_test_00.resourcessp2bq12b2sparql21
import lupos.code_gen_test_00.resourcessp2bq12b2sparql32978
import lupos.code_gen_test_00.resourcessp2bq12b4sparql973
import lupos.code_gen_test_00.resourcessp2bq12bsparql1294
import lupos.code_gen_test_00.resourcessp2bq4sparql1294
import lupos.code_gen_test_00.resourcessp2bq61sparql973
fun main() {
    val tests = mutableListOf<Pair<String, ()->Unit>>()
    tests.addAll(DELETEINSERT5b.getTests())
    tests.addAll(MOVE6.getTests())
    tests.addAll(resourcesbsbmbiquery51853sparql1853.getTests())
    tests.addAll(resourcesbsbmbiquery22553sparql2553.getTests())
    tests.addAll(resourcessp2bq12b2sparql21.getTests())
    tests.addAll(resourcessp2bq12b4sparql973.getTests())
    tests.addAll(resourcessp2bq61sparql973.getTests())
    tests.addAll(resourcessp2bq12bsparql1294.getTests())
    tests.addAll(resourcessp2bq4sparql1294.getTests())
    tests.addAll(resourcessp2bq12b2sparql32978.getTests())
}
