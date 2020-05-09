package lupos.s01io.buffer
import kotlin.jvm.JvmField


/**
 * This is one element of the doubly linked list.
 * The list should have a dummy element, which succeeding element is the head of the list
 * and its previous element is the tail of the list.
 * The empty list contains a dummy element referring to itself.
 * The dummy element avoids checking many special cases when inserting and removing elements...
 *
 * @param <T> the type of the keys to be stored...
 */
class CachedEntry<T, V>(@JvmField val key: T, @JvmField val value: V) {
    @JvmField
    var before: CachedEntry<T, V> = this
    @JvmField
    var after: CachedEntry<T, V> = this

    /**
     * Constructs one element in the doubly linked list and inserts this
     * element after the given other element (in our use case before is the dummy).
     *
     * @param key the key to be stored
     * @param before the element after which the newly created element is stored
     */
    constructor(key: T, value: V, before: CachedEntry<T, V>) : this(key, value) {
        this.insertAfter(before)
    }

    /**
     * removes the current element from the doubly linked list
     */
    inline fun remove() {
        this.before.after = this.after
        this.after.before = this.before
    }

    /**
     * inserts this element after the given element
     * @param before the element after which this element is inserted
     */
    inline fun insertAfter(before: CachedEntry<T, V>) {
        this.before = before
        this.after = before.after
        before.after = this
        this.after.before = this
    }
}

/**
 * This replacement strategy returns the number of the least recently used
 * item if the cache is full.
 */
class LeastRecentlyUsed<T, V>(@JvmField val dummyKey: T, @JvmField val dummyValue: V, @JvmField val size: Int) {
    @JvmField
    val entries = HashMap<T, CachedEntry<T, V>>(size)
    @JvmField
    val dummy = CachedEntry<T, V>(dummyKey, dummyValue)

    inline fun getEntry(key: T): CachedEntry<T, V>? {
        return this.entries.get(key)
    }

    inline fun accessNow(entry: CachedEntry<T, V>) {
        entry.remove()
        entry.insertAfter(this.dummy)
    }

    inline fun addNewEntry(entry: CachedEntry<T, V>) {
        entry.insertAfter(this.dummy)
        this.entries.put(entry.key, entry)
    }

    inline fun replaceLeastRecentlyUsed(): V {
        val leastRecentlyUsed = this.dummy.before
        leastRecentlyUsed.remove()
        val key = leastRecentlyUsed.key
        this.entries.remove(key)
        return leastRecentlyUsed.value
    }

    inline fun replaceLeastRecentlyUsed(crossinline negativeCheck: (CachedEntry<T, V>) -> Boolean)
            : CachedEntry<T, V> {
        var leastRecentlyUsed = this.dummy.before
        // assume that there is at least one entry (not being the dummy) in the list
        while (negativeCheck(leastRecentlyUsed)) {
            leastRecentlyUsed = leastRecentlyUsed.before
            if (leastRecentlyUsed === this.dummy) {
                throw Error("All pages in the cache are locked, "
                        + "but we need to replace one page"
                        + "... Plase check the code if unlocking "
                        + "of pages have been forgotten...")
            }
        }
        leastRecentlyUsed.remove()
        val key = leastRecentlyUsed.key
        this.entries.remove(key)
        return leastRecentlyUsed
    }

    fun release(key: T) {
        val entry = this.entries.get(key)
        if (entry != null) {
            entry.remove()
            this.entries.remove(key)
        }
    }

    fun releaseAll() {
        this.entries.clear()
        this.dummy.before = this.dummy
        this.dummy.after = this.dummy
    }
}
