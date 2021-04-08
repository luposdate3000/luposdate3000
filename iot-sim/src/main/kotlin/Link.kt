data class Link(
    val srcAddress: String,
    val destAddress: String,
    var distanceInMeters: Int,
    val linkType: LinkType
)

