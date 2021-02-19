package lupos.s05tripleStore

public interface ITripleStoreDescriptionFactory {
    public fun addIndex(action: (ITripleStoreIndexDescriptionFactory) -> Unit): ITripleStoreDescriptionFactory
    public fun apply(other: ITripleStoreDescriptionFactory): ITripleStoreDescriptionFactory
}
