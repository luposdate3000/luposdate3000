package layer1.sim
import sun.java2d.cmm.ColorTransform.Simulation


/**
 * Predicates are used to select events from the deferred queue.
 */
abstract class Predicate {
    /**
     * The match function which must be overridden when writing a new predicate. The function is
     * called with each event in the deferred queue as its parameter when a
     * `Simulation.select()` call is made by the user.
     */
    abstract fun match(event: Event?): Boolean
}
