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

/**
 * A utility class for handling units and unit conversions
 * within this library.
 *
 * @author Tyler Coles
 */
internal enum class LengthUnit(
    /**
     * Retrieve the scale factor between this unit and the primary
     * length unit.
     *
     * @return the scale factor.
     */
    private val scaleFactor: Double
) {
    /**
     * Miles, using the scale factor 0.6213712 miles per kilometer.
     */
    MILE(0.6213712),

    /**
     * Nautical miles, using the scale factor 0.5399568 nautical miles per kilometer.
     */
    NAUTICAL_MILE(0.5399568),

    /**
     * Rods, using the scale factor 198.8387815 rods to the kilometer.
     * Because your car gets forty rods to the hogshead and that's
     * they way you likes it.
     */
    ROD(198.8387815),

    /**
     * Kilometers, the primary unit.
     */
    KILOMETER(1.0),

    /**
     * Meters, for ease of use.
     */
    METER(1000.0);

    /**
     * Convert a value of this unit type to the units specified
     * in the parameters.
     *
     * @param toUnit the unit to convert to.
     * @param value the value to convert.
     * @return the converted value.
     */
    internal fun convertTo(toUnit: LengthUnit, value: Double): Double {
        if (this == toUnit) {
            return value
        }
        var newValue = value
        if (this != PRIMARY) {
            newValue /= scaleFactor // Convert to primary unit.
        }
        if (toUnit != PRIMARY) {
            newValue *= toUnit.scaleFactor // Convert to destination unit.
        }
        return newValue
    }

    internal companion object {
        /**
         * The primary length unit. All scale factors are relative
         * to this unit. Any conversion not involving the primary
         * unit will first be converted to this unit, then to
         * the desired unit.
         */
        internal val PRIMARY = KILOMETER
    }
}
