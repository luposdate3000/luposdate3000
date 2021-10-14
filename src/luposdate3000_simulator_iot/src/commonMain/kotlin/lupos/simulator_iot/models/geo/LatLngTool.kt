/*
 *    Copyright 2010 Tyler Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
* Modified on June 3rd, 2021 by Johann Mantler.
* The code was translated into Kotlin and cut to the required parts.
* */
package lupos.simulator_iot.models.geo

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 *
 *
 * Primary calculations and tools.
 *
 *
 *
 *
 * Note: distance calculations are done using the Haversine formula which uses a
 * spherical approximation of the Earth. Values are known to differ from reality
 * by as much as 0.3% so if complete accuracy is very important to you, you
 * should be using a different library. Furthermore, by default this library
 * uses the mean radius of the Earth (6371.009 km). If your calculations are
 * localized to a particular region of the Earth, there may be values to use for
 * this radius which will yield more accurate results.
 *
 *
 * @author Tyler Coles
 */
internal object LatLngTool {
    /**
     * Distance between two points.
     *
     * @param point1
     * the first point.
     * @param point2
     * the second point.
     * @param unit
     * the unit of measure in which to receive the result.
     * @return the distance in the chosen unit of measure.
     */
    internal fun distance(point1: LatLng, point2: LatLng, unit: LengthUnit): Double {
        return distanceInRadians(point1, point2) * LatLngConfig.getEarthRadius(unit)
    }

    /**
     *
     *
     * This "distance" function is mostly for internal use. Most users will
     * simply rely upon [.distance]
     *
     *
     *
     *
     * Yields the internal angle for an arc between two points on the surface of
     * a sphere in radians. This angle is in the plane of the great circle
     * connecting the two points measured from an axis through one of the points
     * and the center of the Earth. Multiply this value by the sphere's radius
     * to get the length of the arc.
     *
     *
     * @param point1
     * the first point.
     * @param point2
     * the second point.
     * @return the internal angle for the arc connecting the two points in
     * radians.
     */
    private fun distanceInRadians(point1: LatLng, point2: LatLng): Double {
        val lat1R = toRadians(point1.getLatitude())
        val lat2R = toRadians(point2.getLatitude())
        val dLatR = abs(lat2R - lat1R)
        val dLngR = abs(toRadians(point2.getLongitude() - point1.getLongitude()))
        val a = sin(dLatR / 2) * sin(dLatR / 2) + (
            cos(lat1R) * cos(lat2R) *
                sin(dLngR / 2) * sin(dLngR / 2)
            )
        return 2 * atan2(sqrt(a), sqrt(1 - a))
    }

    /**
     * Converts an angle measured in degrees to an approximately
     * equivalent angle measured in radians.  The conversion from
     * degrees to radians is generally inexact.
     *
     * @return the measurement of the angle `angleInDegrees`
     * in radians.
     */
    private fun toRadians(angleInDegrees: Double): Double {
        return angleInDegrees / 180.0 * PI
    }

    /**
     * Converts an angle measured in radians to an approximately
     * equivalent angle measured in degrees.  The conversion from
     * radians to degrees is generally inexact; users should
     * *not* expect `cos(toRadians(90.0))` to exactly
     * equal `0.0`.
     *
     * @return the measurement of the angle `angleInRadians`
     * in degrees.
     */
    private fun toDegrees(angleInRadians: Double): Double {
        return angleInRadians * 180.0 / PI
    }

    /**
     *
     *
     * Calculate the end point of traveling along a great-circle path from a
     * given starting point with a given initial bearing for a known distance.
     *
     *
     * @param start
     * the starting point.
     * @param initialBearing
     * the initial bearing.
     * @param distance
     * the distance to travel.
     * @param unit
     * the unit in which distance is measured.
     * @return the end point.
     */
    internal fun travel(
        start: LatLng,
        initialBearing: Double,
        distance: Double,
        unit: LengthUnit
    ): LatLng {
        val bR = toRadians(initialBearing)
        val lat1R = toRadians(start.getLatitude())
        val lon1R = toRadians(start.getLongitude())
        val dR = distance / LatLngConfig.getEarthRadius(unit)
        val a = sin(dR) * cos(lat1R)
        val lat2 = asin(sin(lat1R) * cos(dR) + a * cos(bR))
        val lon2 = (
            lon1R +
                atan2(sin(bR) * a, cos(dR) - sin(lat1R) * sin(lat2))
            )
        return LatLng(toDegrees(lat2), toDegrees(lon2))
    }

    /**
     * Clamp latitude to +/- 90 degrees.
     *
     * @param latitude
     * in degrees.
     * @return the normalized latitude.
     */
    internal fun normalizeLatitude(latitude: Double): Double {
        return if (latitude > 0) {
            latitude.coerceAtMost(90.0)
        } else {
            latitude.coerceAtLeast(-90.0)
        }
    }

    /**
     * Convert longitude to be within the +/- 180 degrees range.
     *
     * @param longitude
     * in degrees.
     * @return the normalized longitude.
     * Returns positive infinity, or negative infinity.
     */
    internal fun normalizeLongitude(longitude: Double): Double {
        var longitudeResult = longitude % 360
        if (longitudeResult > 180) {
            val diff = longitudeResult - 180
            longitudeResult = -180 + diff
        } else if (longitudeResult < -180) {
            val diff = longitudeResult + 180
            longitudeResult = 180 + diff
        }
        return longitudeResult
    }

    /** Convenient static values for bearings.  */
    internal object Bearing {
        const val NORTH = 0.0
        const val EAST = 90.0
        const val SOUTH = 180.0
    }
}
