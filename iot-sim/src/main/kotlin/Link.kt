data class Link(
    val srcAddress: String,
    val destAddress: String,
    var distanceInMeters: Double,
    val linkType: LinkType
)

