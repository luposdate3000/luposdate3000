package simulation

class EmptyEntityStub(name: String) : Entity(name) {
    override fun startUpEntity() {
    }
    override fun processEvent(ev: Event) {
    }
    override fun shutDownEntity() {
    }
}

class EmptyEventTypeStub : EventType {
    override fun getEventType(): Int {
        return 0
    }
}