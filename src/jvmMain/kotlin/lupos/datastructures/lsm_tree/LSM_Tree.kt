package lupos.datastructures.lsm_tree

import kotlin.jvm.JvmField

class NotFoundException(obj: Any) : Exception(obj.toString() + " not found!")
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// configuration and search methods/receiveRun: do with inline functions for avoiding calls to overridden methods
class LSM_Tree_Helper<K : Comparable<in K>, V>(@JvmField val directoryOfIndex: String? = null) {
    companion object {
        private var dir_counter = 0
        private val prefix = "LSM_"
        // should be locked, but no direct support in all platforms...
        private fun getNewDirectory(directoryOfIndex: String?): String {
            if (directoryOfIndex == null) {
                val currentDirCounter = LSM_Tree_Helper.dir_counter
                LSM_Tree_Helper.dir_counter++
                return LSM_Tree_Helper.prefix + currentDirCounter.toString()
            } else {
                return directoryOfIndex
            }
        }
    }

    val directory: String = LSM_Tree_Helper.getNewDirectory(directoryOfIndex)
}

// returns the value of the key, or null if the key is not found
inline fun <K, V> getOrNull(k: K, firstLevel: (k: K) -> V?, remainingLevels: (k: K) -> V?): V? = firstLevel(k)
        ?: remainingLevels(k)

// the following version throws an exception if the key is not found
inline fun <K, V> get(k: K, firstLevel: (k: K) -> V, remainingLevels: (k: K) -> V): V = try {
    firstLevel(k)
} catch (e: NotFoundException) {
    remainingLevels(k)
}

// range search, returns a function returning all key-value-pairs, which are equal to or greater than smallerKey, and which are equal to or smaller than biggerKey
// TODO: more efficient by having the iterators of all levels/runs at once and do not compare always with null values/do not consider already closed iterators
inline fun <K : Comparable<in K>, V> range(noinline firstLevel: () -> Pair<K, V>?, noinline remainingLevels: () -> Pair<K, V>?): () -> Pair<K, V>? {
    var firstLevelValue = firstLevel()
    var remainingLevelsValue = remainingLevels()
    return {
        if (firstLevelValue == null) {
            if (remainingLevelsValue == null) {
                null
            } else {
                val result = remainingLevelsValue
                remainingLevelsValue = remainingLevels()
                result
            }
        } else {
            if (remainingLevelsValue == null) {
                val result = firstLevelValue
                firstLevelValue = firstLevel()
                result
            } else {
                if (firstLevelValue!!.component1() < remainingLevelsValue!!.component1()) {
                    val result = firstLevelValue
                    firstLevelValue = firstLevel()
                    result
                } else {
                    val result = remainingLevelsValue
                    remainingLevelsValue = remainingLevels()
                    result
                }
            }
        }
    }
}

inline fun <K, V> rangeNoOrder(noinline firstLevel: () -> Pair<K, V>?, noinline remainingLevels: () -> Pair<K, V>?): () -> Pair<K, V>? {
    var firstLevelNoneEmpty = true
    return {
        if (firstLevelNoneEmpty) {
            val result = firstLevel()
            if (result != null) {
                result
            } else {
                firstLevelNoneEmpty = false
                remainingLevels()
            }
        } else {
            remainingLevels()
        }
    }
}

inline fun <K, V> put(k: K, v: V, isFirstLevelFull: () -> Boolean, putFirstLevel: (k: K, v: V) -> Unit, runOfFirstLevel: () -> Pair<Int, () -> Pair<K, V>?>, receiveRunFromFirstLevel: (Pair<Int, () -> Pair<K, V>?>) -> Unit, clearFirstLevel: () -> Unit) {
    if (isFirstLevelFull()) {
        receiveRunFromFirstLevel(runOfFirstLevel())
        clearFirstLevel()
    }
    putFirstLevel(k, v)
}

inline class HashMapIndexWithLazySorting<K : Comparable<in K>, V>(@JvmField val mainMemoryDatastructure: HashMap<K, V> = hashMapOf<K, V>()) {
    inline fun put(k: K, v: V) {
        this.mainMemoryDatastructure[k] = v
    }

    inline fun clear() {
        this.mainMemoryDatastructure.clear()
    }

    inline fun size(): Int = this.mainMemoryDatastructure.size
    // returns the value of the key, or null if the key is not found
    inline fun getOrNull(k: K): V? = this.mainMemoryDatastructure[k]

    // the following version throws an exception if the key is not found
    inline fun get(k: K): V = this.mainMemoryDatastructure[k] ?: throw NotFoundException(k)

    // range search, returns a function returning all key-value-pairs, which are equal to or greater than smallerKey, and which are equal to or smaller than biggerKey
    inline fun range(smallerKey: K, biggerKey: K): () -> Pair<K, V>? {
        val sortedListIt = this.mainMemoryDatastructure.filterKeys { it in smallerKey..biggerKey }.toList().sortedBy { it.first }.iterator()
        return {
            if (sortedListIt.hasNext()) {
                sortedListIt.next()
            } else {
                null
            }
        }
    }

    inline fun rangeNoOrder(smallerKey: K, biggerKey: K): () -> Pair<K, V>? {
        val iterator = this.mainMemoryDatastructure.filterKeys { it in smallerKey..biggerKey }.iterator()
        return {
            if (iterator.hasNext()) {
                val p = iterator.next()
                Pair<K, V>(p.key, p.value) // TODO: not optimal: generating a Pair-object for each Map.Entry-object!
            } else {
                null
            }
        }
    }

    inline fun getRun(): Pair<Int, () -> Pair<K, V>?> {
        val sortedListIt = this.mainMemoryDatastructure.toList().sortedBy { it.first }.iterator()
        val iterator = {
            if (sortedListIt.hasNext()) {
                sortedListIt.next()
            } else {
                null
            }
        }
        return Pair(this.mainMemoryDatastructure.size, iterator)
    }
}

// TODO: class implementing this interface...
interface Searchable<K, V, R> {
    class Level<K : Comparable<in K>, V, R : Searchable<K, V, R>>(@JvmField val level: Int, @JvmField val createRunFromFirstLevelInput: (Pair<Int, () -> Pair<K, V>?>) -> R) {
        var MAX_RUNS = 4
        var nextLevel: Level<K, V, R>? = null
        val runs: Array<R?> = arrayOfNulls<Any>(MAX_RUNS) as Array<R?>
        var numberOfRuns = 0
        fun createLevel(lavel: Int) = Level<K, V, R>(this.level + 1, this.createRunFromFirstLevelInput)
        fun receiveFromLowerLevel(r: R) {
            if (numberOfRuns + 1 >= MAX_RUNS) {
                if (this.nextLevel == null) {
                    this.nextLevel = this.createLevel(this.level + 1)
                }
                this.nextLevel!!.receiveFromLowerLevel(runs[0]!!.merge(runs))
                this.numberOfRuns = 0
            }
            runs[this.numberOfRuns] = r
            this.numberOfRuns++
        }

        fun receiveFromFirstLevel(input: Pair<Int, () -> Pair<K, V>?>) {
            receiveFromLowerLevel(this.createRunFromFirstLevelInput(input))
        }

        fun getOrNull(k: K): V? {
            for (i in 0..this.numberOfRuns - 1) {
                val result = this.runs[i]!!.getOrNull(k)
                if (result != null) {
                    return result
                }
            }
            return this.nextLevel?.getOrNull(k)
        }

        fun get(k: K): V {
            for (i in 0..this.numberOfRuns - 1) {
                try {
                    // in this way an inserted null value is treated correctly (in the case of that V is a nullable type)!
                    return this.runs[i]!!.get(k)
                } catch (e: NotFoundException) {
                    // do nothing and just continue the loop
                }
            }
            if (this.nextLevel != null) {
                return this.nextLevel!!.get(k)
            } else {
                throw NotFoundException(k)
            }
        }

        fun range(smallerKey: K, biggerKey: K): () -> Pair<K, V>? {
            val iterators =
                    if (this.nextLevel != null)
                        Array<() -> Pair<K, V>?>(this.numberOfRuns + 1, { i -> if (i < this.numberOfRuns) this.runs[i]!!.rangeNoOrder(smallerKey, biggerKey) else this.nextLevel!!.rangeNoOrder(smallerKey, biggerKey) })
                    else
                        Array<() -> Pair<K, V>?>(this.numberOfRuns, { i -> this.runs[i]!!.rangeNoOrder(smallerKey, biggerKey) })
            val currentValues =
                    Array<Pair<K, V>?>(iterators.size, { i -> iterators[i]() })
            var noneNullIndex = 0 // noneNullIndex is the index until which the iterators are not empty
            // determine noneNullIndex and at the same time create an array with continuously none-empty iterators
            for (i in 0 until iterators.size) {
                if (currentValues[i] != null) {
                    currentValues[noneNullIndex] = currentValues[i]
                    iterators[noneNullIndex] = iterators[i]
                    noneNullIndex++
                }
            }
            return rsfun@{
                while (noneNullIndex > 0) {
                    // determine index with minimum key
                    var minIndex = 0
                    var minKey = currentValues[0]!!.component1()
                    for (i in 1 until noneNullIndex) {
                        if (currentValues[i]!!.component1() < minKey) {
                            minIndex = i
                            minKey = currentValues[minIndex]!!.component1()
                        }
                    }
                    val result = currentValues[minIndex]
                    // dtermine already next value and check if it is none-empty
                    val nextValue = iterators[minIndex]()
                    if (nextValue == null) {
                        // reorder the array of continously none-empty iterators...
                        noneNullIndex--
                        currentValues[minIndex] = currentValues[noneNullIndex]
                        iterators[minIndex] = iterators[noneNullIndex]
                    } else {
                        currentValues[minIndex] = nextValue
                    }
                    return@rsfun result
                }
                null
            }
        }

        fun rangeNoOrder(smallerKey: K, biggerKey: K): () -> Pair<K, V>? {
            val iterators =
                    if (this.nextLevel != null)
                        Array<() -> Pair<K, V>?>(this.numberOfRuns + 1, { i -> if (i < this.numberOfRuns) this.runs[i]!!.rangeNoOrder(smallerKey, biggerKey) else this.nextLevel!!.rangeNoOrder(smallerKey, biggerKey) })
                    else
                        Array<() -> Pair<K, V>?>(this.numberOfRuns, { i -> this.runs[i]!!.rangeNoOrder(smallerKey, biggerKey) })
            var index = 0
            return rsfun@{
                while (index < iterators.size) {
                    val result = iterators[index]()
                    if (result != null) {
                        return@rsfun result
                    }
                    index++
                }
                null
            }
        }
    }

    fun getOrNull(k: K): V?
    fun get(k: K): V
    fun range(smallerKey: K, biggerKey: K): () -> Pair<K, V>?
    fun rangeNoOrder(smallerKey: K, biggerKey: K): () -> Pair<K, V>?
    fun merge(rs: Array<R?>): R
}

class LSM_Tree<K : Comparable<in K>, V> {
    var MAX_ENTRIES = 50000
    val firstLevel = HashMapIndexWithLazySorting<K, V>()
    inline fun getOrNull(k: K, remainingLevels: (k: K) -> V?): V? = getOrNull(k, { this.firstLevel.getOrNull(it) }, remainingLevels)
    inline fun get(k: K, remainingLevels: (k: K) -> V): V = get(k, { this.firstLevel.get(it) }, remainingLevels)
    inline fun put(k: K, v: V, receiveRunFromFirstLevel: (Pair<Int, () -> Pair<K, V>?>) -> Unit) {
        put(k, v, { this.firstLevel.size() >= MAX_ENTRIES }, { k: K, v: V -> { this.firstLevel.put(k, v) } }, { this.firstLevel.getRun() }, receiveRunFromFirstLevel, { this.firstLevel.clear() })
    }

    inline fun range(smallerKey: K, biggerKey: K, noinline remainingLevels: () -> Pair<K, V>?): () -> Pair<K, V>? = range(this.firstLevel.range(smallerKey, biggerKey), remainingLevels)
    inline fun rangeNoOrder(smallerKey: K, biggerKey: K, noinline remainingLevels: () -> Pair<K, V>?): () -> Pair<K, V>? = rangeNoOrder(this.firstLevel.rangeNoOrder(smallerKey, biggerKey), remainingLevels)
}
