package lupos.simulator_iot.queryproc

import lupos.simulator_iot.queryproc.pck.SequencedPackage

internal class SequenceBuffer(
    internal val packetsReceivedSoFar: MutableList<SequencedPackage>,
    internal var expectedNumberOfPackages: Int)
