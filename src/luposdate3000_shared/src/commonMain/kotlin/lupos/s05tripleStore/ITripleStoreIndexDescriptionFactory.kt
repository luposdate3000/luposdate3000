package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern

public interface ITripleStoreIndexDescriptionFactory {
    public fun simple(idx: EIndexPattern): ITripleStoreIndexDescriptionFactory
    public fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): ITripleStoreIndexDescriptionFactory
    public fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): ITripleStoreIndexDescriptionFactory
}
