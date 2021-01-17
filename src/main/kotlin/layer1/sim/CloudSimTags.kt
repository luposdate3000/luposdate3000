package layer1.sim

/**
 * Contains various static command tags that indicate a type of action that needs to be undertaken
 * by CloudSim entities when they receive or send events. **NOTE:** To avoid conflicts with other
 * tags, CloudSim reserves negative numbers, 0 - 299, and 9600.
 *
 */
class CloudSimTags private constructor() {
    companion object {
        /** Starting constant value for cloud-related tags  */
        private const val BASE = 0

        /** Starting constant value for network-related tags  */
        private const val NETBASE = 100

        /** Denotes boolean <tt>true</tt> in <tt>int</tt> value  */
        const val TRUE = 1

        /** Denotes boolean <tt>false</tt> in <tt>int</tt> value  */
        const val FALSE = 0

        /** Denotes the default baud rate for some CloudSim entities  */
        const val DEFAULT_BAUD_RATE = 9600

        /** Schedules an entity without any delay  */
        const val SCHEDULE_NOW = 0.0

        /** Denotes the end of simulation  */
        const val END_OF_SIMULATION = -1

        /**
         * Denotes an abrupt end of simulation. That is, one event of this type is enough for
         * [ShutdownEntity] to trigger the end of the simulation
         */
        const val ABRUPT_END_OF_SIMULATION = -2

        /**
         * Denotes insignificant simulation entity or time. This tag will not be used for identification
         * purposes.
         */
        const val INSIGNIFICANT = BASE + 0

        /** Sends an Experiment object between UserEntity and Broker entity  */
        const val EXPERIMENT = BASE + 1

        /**
         * Denotes a grid resource to be registered. This tag is normally used between
         * CloudInformationService and CloudResouce entity.
         */
        const val REGISTER_RESOURCE = BASE + 2

        /**
         * Denotes a grid resource, that can support advance reservation, to be registered. This tag is
         * normally used between CloudInformationService and CloudResouce entity.
         */
        const val REGISTER_RESOURCE_AR = BASE + 3

        /**
         * Denotes a list of all hostList, including the ones that can support advance reservation. This
         * tag is normally used between CloudInformationService and CloudSim entity.
         */
        const val RESOURCE_LIST = BASE + 4

        /**
         * Denotes a list of hostList that only support advance reservation. This tag is normally used
         * between CloudInformationService and CloudSim entity.
         */
        const val RESOURCE_AR_LIST = BASE + 5

        /**
         * Denotes grid resource characteristics information. This tag is normally used between CloudSim
         * and CloudResource entity.
         */
        const val RESOURCE_CHARACTERISTICS = BASE + 6

        /**
         * Denotes grid resource allocation policy. This tag is normally used between CloudSim and
         * CloudResource entity.
         */
        const val RESOURCE_DYNAMICS = BASE + 7

        /**
         * Denotes a request to get the total number of Processing Elements (PEs) of a resource. This
         * tag is normally used between CloudSim and CloudResource entity.
         */
        const val RESOURCE_NUM_PE = BASE + 8

        /**
         * Denotes a request to get the total number of free Processing Elements (PEs) of a resource.
         * This tag is normally used between CloudSim and CloudResource entity.
         */
        const val RESOURCE_NUM_FREE_PE = BASE + 9

        /**
         * Denotes a request to record events for statistical purposes. This tag is normally used
         * between CloudSim and CloudStatistics entity.
         */
        const val RECORD_STATISTICS = BASE + 10

        /** Denotes a request to get a statistical list.  */
        const val RETURN_STAT_LIST = BASE + 11

        /**
         * Denotes a request to send an Accumulator object based on category into an event scheduler.
         * This tag is normally used between ReportWriter and CloudStatistics entity.
         */
        const val RETURN_ACC_STATISTICS_BY_CATEGORY = BASE + 12

        /**
         * Denotes a request to register a CloudResource entity to a regional CloudInformationService
         * (GIS) entity
         */
        const val REGISTER_REGIONAL_GIS = BASE + 13

        /**
         * Denotes a request to get a list of other regional GIS entities from the system GIS entity
         */
        const val REQUEST_REGIONAL_GIS = BASE + 14

        /**
         * Denotes request for grid resource characteristics information. This tag is normally used
         * between CloudSim and CloudResource entity.
         */
        const val RESOURCE_CHARACTERISTICS_REQUEST = BASE + 15

        /** This tag is used by an entity to send ping requests  */
        const val INFOPKT_SUBMIT = NETBASE + 5

        /** This tag is used to return the ping request back to sender  */
        const val INFOPKT_RETURN = NETBASE + 6

        /**
         * Denotes the return of a Cloudlet back to sender. This tag is normally used by CloudResource
         * entity.
         */
        const val CLOUDLET_RETURN = BASE + 20

        /**
         * Denotes the submission of a Cloudlet. This tag is normally used between CloudSim User and
         * CloudResource entity.
         */
        const val CLOUDLET_SUBMIT = BASE + 21

        /**
         * Denotes the submission of a Cloudlet with an acknowledgement. This tag is normally used
         * between CloudSim User and CloudResource entity.
         */
        const val CLOUDLET_SUBMIT_ACK = BASE + 22

        /** Cancels a Cloudlet submitted in the CloudResource entity.  */
        const val CLOUDLET_CANCEL = BASE + 23

        /** Denotes the status of a Cloudlet.  */
        const val CLOUDLET_STATUS = BASE + 24

        /** Pauses a Cloudlet submitted in the CloudResource entity.  */
        const val CLOUDLET_PAUSE = BASE + 25

        /**
         * Pauses a Cloudlet submitted in the CloudResource entity with an acknowledgement.
         */
        const val CLOUDLET_PAUSE_ACK = BASE + 26

        /** Resumes a Cloudlet submitted in the CloudResource entity.  */
        const val CLOUDLET_RESUME = BASE + 27

        /**
         * Resumes a Cloudlet submitted in the CloudResource entity with an acknowledgement.
         */
        const val CLOUDLET_RESUME_ACK = BASE + 28

        /** Moves a Cloudlet to another CloudResource entity.  */
        const val CLOUDLET_MOVE = BASE + 29

        /**
         * Moves a Cloudlet to another CloudResource entity with an acknowledgement.
         */
        const val CLOUDLET_MOVE_ACK = BASE + 30

        /**
         * Denotes a request to create a new VM in a Datacentre With acknowledgement information sent by
         * the Datacentre
         */
        const val VM_CREATE = BASE + 31

        /**
         * Denotes a request to create a new VM in a Datacentre With acknowledgement information sent by
         * the Datacentre
         */
        const val VM_CREATE_ACK = BASE + 32

        /**
         * Denotes a request to destroy a new VM in a Datacentre
         */
        const val VM_DESTROY = BASE + 33

        /**
         * Denotes a request to destroy a new VM in a Datacentre
         */
        const val VM_DESTROY_ACK = BASE + 34

        /**
         * Denotes a request to migrate a new VM in a Datacentre
         */
        const val VM_MIGRATE = BASE + 35

        /**
         * Denotes a request to migrate a new VM in a Datacentre With acknowledgement information sent
         * by the Datacentre
         */
        const val VM_MIGRATE_ACK = BASE + 36

        /**
         * Denotes an event to send a file from a user to a datacenter
         */
        const val VM_DATA_ADD = BASE + 37

        /**
         * Denotes an event to send a file from a user to a datacenter
         */
        const val VM_DATA_ADD_ACK = BASE + 38

        /**
         * Denotes an event to remove a file from a datacenter
         */
        const val VM_DATA_DEL = BASE + 39

        /**
         * Denotes an event to remove a file from a datacenter
         */
        const val VM_DATA_DEL_ACK = BASE + 40

        /**
         * Denotes an internal event generated in a PowerDatacenter
         */
        const val VM_DATACENTER_EVENT = BASE + 41

        /**
         * Denotes an internal event generated in a Broker
         */
        const val VM_BROKER_EVENT = BASE + 42
        const val Network_Event_UP = BASE + 43
        const val Network_Event_send = BASE + 44
        const val RESOURCE_Register = BASE + 45
        const val Network_Event_DOWN = BASE + 46
        const val Network_Event_Host = BASE + 47
        const val NextCycle = BASE + 48
    }

    /** Private Constructor  */
    init {
        throw UnsupportedOperationException("CloudSim Tags cannot be instantiated")
    }
}
