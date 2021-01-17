package layer1.sim

import layer1.sim.Simulation.forceShutDown

/**
 * CloudimShutdown waits for termination of all CloudSim user entities to determine the end of
 * simulation. This class will be created by CloudSim upon initialisation of the simulation, i.e.
 * done via <tt>CloudSim.init()</tt> method. Hence, do not need to worry about creating an object of
 * this class. This object signals the end of simulation to CloudInformationService (GIS) entity.

 */
class ShutdownEntity(name: String) : Entity(name) {
    /**
     * The main method that shuts down hostList and Cloud Information Service (GIS). In addition,
     * this method writes down a report at the end of a simulation based on
     * <tt>reportWriterName</tt> defined in the Constructor. <br></br>
     * **NOTE:** This method shuts down grid hostList and GIS entities either <tt>AFTER</tt> all
     * grid users have been shut down or an entity requires an abrupt end of the whole simulation.
     * In the first case, the number of grid users given in the Constructor <tt>must</tt> be
     * correct. Otherwise, CloudSim package hangs forever or it does not terminate properly.
     */
    override fun processEvent(ev: Event) {
        if (ev.tag == CloudSimTags.ABRUPT_END_OF_SIMULATION) {
            forceShutDown()
        }
    }

    override fun startUpEntity() {
        // do nothing
    }

    override fun shutDownEntity() {
        // do nothing
    }
}
