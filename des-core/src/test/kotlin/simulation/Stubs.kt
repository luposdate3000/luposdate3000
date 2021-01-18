package simulation

class EntityStub(name: String) : Entity(name) {
    override fun startUpEntity() {
    }
    override fun processEvent(ev: Event) {
    }
    override fun shutDownEntity() {
    }
    override fun getEventSendingDelay(destination: Entity, type: EventType, data: Any?): Double {
        return 0.0
    }
}

class EventTypeStub : EventType {
    override fun getEventType(): Int {
        return 0
    }
}