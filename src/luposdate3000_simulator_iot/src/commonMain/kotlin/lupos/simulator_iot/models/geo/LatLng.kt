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

import kotlin.Long
import kotlin.math.abs
import kotlin.random.Random

/**
 *
 * A representation of a single point in latitude and longitude.
 * All data is handled in degrees and will be normalized if possible
 * to the +/- 90 latitude, +/- 180 longitude region.
 *
 *
 * Note that attempting to create a LatLng with invalid values
 * (NaN, negative infinity, positive infinity) will throw
 * IllegalArgumentExceptions.
 *
 * @author Tyler Coles
 */
internal class LatLng(latitude: Double, longitude: Double) {
    /**
     * Get the internal long representation of this point's latitude
     * in degrees. Intended for library use only.
     *
     * @return the internal representation of latitude in degrees.
     */
    private var latitude: Long = 0

    /**
     * Get the internal long representation of this point's longitude
     * in degrees. Intended for library use only.
     *
     * @return the internal representation of longitude in degrees.
     */
    private var longitude: Long = 0

    /**
     * Get latitude for this point in degrees.
     *
     * @return latitude in degrees.
     */
    internal fun getLatitude(): Double {
        return LatLngConfig.longToDouble(latitude)
    }

    /**
     * Get longitude for this point in degrees.
     *
     * @return longitude in degrees.
     */
    internal fun getLongitude(): Double {
        return LatLngConfig.longToDouble(longitude)
    }

    /**
     * Sets the latitude and longitude for this point.
     *
     * @param latitude the latitude in degrees.
     * @param longitude the longitude in degrees.
     */
    private fun setLatitudeLongitude(latitude: Double, longitude: Double) {
        setLatitude(latitude)
        if (abs(this.latitude) == 90000000L) {
            // At the poles all longitudes intersect. Simplify for later comparison.
            setLongitude(0.0)
        } else {
            setLongitude(longitude)
        }
    }

    /**
     * Sets the latitude for this point.
     */
    private fun setLatitude(latitude: Double) {
        val lat = LatLngTool.normalizeLatitude(latitude)
        this.latitude = LatLngConfig.doubleToLong(lat)
    }

    /**
     * Sets the longitude for this point.
     */
    private fun setLongitude(longitude: Double) {
        val lng = LatLngTool.normalizeLongitude(longitude)
        this.longitude = LatLngConfig.doubleToLong(lng)
    }

    internal companion object {
        /**
         * Creates a random latitude and longitude. (Not inclusive of (-90, 0))
         *
         * @return the random LatLng.
         */

        internal fun random(r: Random): LatLng {
            return LatLng(
                r.nextDouble() * -180.0 + 90.0,
                r.nextDouble() * -360.0 + 180.0
            )
        }
    }

    init {
        setLatitudeLongitude(latitude, longitude)
    }
}
