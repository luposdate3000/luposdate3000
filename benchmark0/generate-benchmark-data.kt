import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

enum class ETripleComponentType {
    IRI,
    BLANK_NODE,
    STRING,
    INTEGER,
    DECIMAL,
    DOUBLE,
    BOOLEAN,
    STRING_TYPED,
    STRING_LANG,
}

fun generateTriples(args: Array<String>): Int {
    var targetNumberOfResults = 1L
    var numberOfPredicates = 2
    var blockCount = 1 // if there is a match, than x elements match in a row
    var trashCount = 0 // if there is no match, than x elements dont match in a row
    if (args.size > 0) {
        targetNumberOfResults = args[0].toLong()
    }
    if (args.size > 1) {
        numberOfPredicates = args[1].toInt()
    }
    if (args.size > 2) {
        blockCount = args[2].toInt()
    }
    if (args.size > 3) {
        trashCount = args[3].toInt()
    }
    var folderName = "/tmp/luposdate"
    if (args.size > 4) {
        folderName = args[4]
    }
    return generateTriples(targetNumberOfResults, numberOfPredicates, blockCount, trashCount, folderName)
}

fun generateTriples(targetNumberOfResults2: Long, numberOfPredicates: Int, blockCount: Int, trashCount: Int, folderName: String): Int {
    var targetNumberOfResults = targetNumberOfResults2
    val preventMultiplesOfList = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)
    val object_counter = 100
    File(folderName).mkdirs()
    var outN3: PrintWriter = File(folderName + "/intermediate.n3").printWriter()
    var outIntermediateDictionary = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.dictionary")))
    var outIntermediateTriples = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.triples")))
    var outIntermediateDictionaryStat = File(folderName + "/intermediate.n3.stat")
    var dictCounterBnode = 0
    var dictCounterIri = 0
    var outIntermediateTriplesStatCounter = 0
    val byteBuf = ByteArray(1)
    fun writeToDict(s: String) {
        val tmp: ByteArray
        if (s.startsWith("_:")) {
            dictCounterBnode++
            byteBuf[0] = ETripleComponentType.BLANK_NODE.ordinal.toByte()
            tmp = s.encodeToByteArray()
        } else {
            dictCounterIri++
            byteBuf[0] = ETripleComponentType.IRI.ordinal.toByte()
            tmp = s.substring(1, s.length - 1).encodeToByteArray()
        }
        outIntermediateDictionary.writeInt(tmp.size)
        outIntermediateDictionary.write(byteBuf)
        outIntermediateDictionary.write(tmp)
    }
// predefining unused values, to prevent mixing unwanted "new" values, which would break the target patterns
    writeToDict("_:_a1")
    writeToDict("<http://benchmark.com/unused>")
    val s_offset = 2 + object_counter + numberOfPredicates
    val p_offset = 2 + object_counter
    val o_offset = 2
    outN3.println("@prefix b: <http://benchmark.com/> .")
    for (i in 0 until object_counter) {
        outN3.println("_:a1 b:unused b:o${i.toString(16)} .")
        writeToDict("<http://benchmark.com/o${i.toString(16)}>")
        outIntermediateTriples.writeInt(0)
        outIntermediateTriples.writeInt(1)
        outIntermediateTriples.writeInt(o_offset + i)
        outIntermediateTriplesStatCounter++
    }
    for (i in 0 until numberOfPredicates) {
        outN3.println("_:a1 b:unused b:p$i .")
        writeToDict("<http://benchmark.com/p$i>")
        outIntermediateTriples.writeInt(0)
        outIntermediateTriples.writeInt(1)
        outIntermediateTriples.writeInt(p_offset + i)
        outIntermediateTriplesStatCounter++
    }
    var counter = 0
    loop@ while (targetNumberOfResults > 0) {
        writeToDict("_:_s${counter.toString(16)}")
        for (p in 0 until numberOfPredicates) {
            for (j in 0 until blockCount) {
                outN3.println("_:s${counter.toString(16)} b:p$p b:o${((j + counter) % object_counter).toString(16)} .")
                outIntermediateTriples.writeInt(s_offset + counter)
                outIntermediateTriples.writeInt(p_offset + p)
                outIntermediateTriples.writeInt(o_offset + ((j + counter) % object_counter))
                outIntermediateTriplesStatCounter++
            }
        }
        targetNumberOfResults--
        counter++
        if (trashCount > 0) {
            var trashcounter = 1
            for (p in 0 until numberOfPredicates) {
                for (j in 0 until trashCount) {
                    outN3.println("_:s${counter.toString(16)} b:p$p b:o${((j + counter) % object_counter).toString(16)} .")
                    writeToDict("_:_s${counter.toString(16)}")
                    outIntermediateTriples.writeInt(s_offset + counter)
                    outIntermediateTriples.writeInt(p_offset + p)
                    outIntermediateTriples.writeInt(o_offset + ((j + counter) % object_counter))
                    outIntermediateTriplesStatCounter++
                    counter++
                    trashcounter++
                }
            }
            var flag = true
            trashloop@ while (flag) {
                flag = false
                for (i in preventMultiplesOfList) {
                    if (trashcounter % i == 0) {
                        flag = true
                        writeToDict("_:_s${counter.toString(16)}")
                        outIntermediateTriples.writeInt(s_offset + counter)
                        outIntermediateTriples.writeInt(1)
                        outIntermediateTriples.writeInt(o_offset + (counter % object_counter))
                        outIntermediateTriplesStatCounter++
                        outN3.println("_:s${counter.toString(16)} b:unused b:o${(counter % object_counter).toString(16)} .")
                        counter++
                        trashcounter++
                    }
                }
            }
        }
    }
    outIntermediateTriples.close()
    outIntermediateDictionaryStat.printWriter().use { out ->
        out.println("total=${dictCounterBnode + dictCounterIri}")
        for (t in ETripleComponentType.values()) {
            if (t == ETripleComponentType.BLANK_NODE) {
                out.println("$t=$dictCounterBnode")
            } else if (t == ETripleComponentType.IRI) {
                out.println("$t=$dictCounterIri")
            } else {
                out.println("$t=0")
            }
        }
    }
    outN3.close()
    outIntermediateDictionary.close()
    outIntermediateTriples.close()
    return outIntermediateTriplesStatCounter
}
