package layer1.sim

/**
 * A predicate which will match any event on the deferred event queue.
 */
class PredicateAny : Predicate() {

    override fun match(event: Event?): Boolean {
        return true
    }
}
