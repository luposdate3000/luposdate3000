/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.shared

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.plus
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import lupos.shared_inline.DateHelper
import kotlin.jvm.JvmField

public sealed class ValueDefinition : Comparable<ValueDefinition> {
    public /*suspend*/ abstract fun toXMLElement(partial: Boolean): XMLElement
    public abstract fun valueToString(): String?
    public abstract fun toDouble(): Double
    public abstract fun toDecimal(): BigDecimal
    public abstract fun toInt(): BigInteger
    public abstract fun toBoolean(): Boolean

    public abstract operator fun compareTo(value: BigInteger): Int
    public abstract operator fun compareTo(value: BigDecimal): Int
    public abstract operator fun compareTo(value: String): Int
    public abstract operator fun compareTo(value: ValueStringBase): Int
    public abstract operator fun compareTo(value: ValueIri): Int
    public abstract operator fun compareTo(value: ValueBnode): Int
    public abstract operator fun compareTo(value: ValueBoolean): Int

    public abstract operator fun plus(value: BigInteger): ValueDefinition
    public abstract operator fun plus(value: ValueInteger): ValueDefinition
    public abstract operator fun plus(value: BigDecimal): ValueDefinition
    public abstract operator fun plus(value: ValueBoolean): ValueDefinition
    public abstract operator fun plus(other: ValueDefinition): ValueDefinition

    public abstract operator fun minus(value: BigInteger): ValueDefinition
    public abstract operator fun minus(value: ValueInteger): ValueDefinition
    public abstract operator fun minus(value: BigDecimal): ValueDefinition
    public abstract operator fun minus(value: ValueBoolean): ValueDefinition
    public abstract operator fun minus(other: ValueDefinition): ValueDefinition

    public abstract operator fun times(value: BigInteger): ValueDefinition
    public abstract operator fun times(value: ValueInteger): ValueDefinition
    public abstract operator fun times(value: BigDecimal): ValueDefinition
    public abstract operator fun times(value: ValueBoolean): ValueDefinition
    public abstract operator fun times(other: ValueDefinition): ValueDefinition

    public abstract operator fun div(value: BigInteger): ValueDefinition
    public abstract operator fun div(value: ValueInteger): ValueDefinition
    public abstract operator fun div(value: BigDecimal): ValueDefinition
    public abstract operator fun div(value: ValueBoolean): ValueDefinition
    public abstract operator fun div(other: ValueDefinition): ValueDefinition

    public companion object {
        public fun convertToValueDefinition(value: Boolean): ValueDefinition {
            return ValueBoolean(value)
        }

        public fun convertToValueDefinition(value: BigInteger): ValueDefinition {
            return ValueInteger(value)
        }

        public fun convertToValueDefinition(value: ValueDefinition): ValueDefinition {
            return value
        }

        public fun convertToValueDefinition(value: BigDecimal): ValueDefinition {
            return ValueDecimal(value)
        }

        public fun convertToValueDefinition(value: String): ValueDefinition {
            return ValueSimpleLiteral("", value)
        }

        public operator fun invoke(tmp: String?): ValueDefinition {
            if (tmp == null || tmp.isEmpty()) {
                return ValueUndef()
            }
            if (tmp.startsWith("_:")) {
                return ValueBnode(tmp.substring(2, tmp.length))
            }
            if (tmp.startsWith("<") && tmp.endsWith(">")) {
                return ValueIri(tmp.substring(1, tmp.length - 1))
            }
            try {
                return ValueInteger(BigInteger.parseString(tmp, 10))
            } catch (e: Exception) {
// e.printStackTrace() this is handled correctly
            }
            if (!tmp.contains("e") && !tmp.contains("E")) {
                try {
                    return ValueDecimal(BigDecimal.parseString(tmp, 10))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            try {
                return ValueDouble(tmp.toDouble())
            } catch (e: Exception) {
// e.printStackTrace() this is handled correctly
            }
            if (!tmp.endsWith("" + tmp[0])) {
                val typeIdx = tmp.lastIndexOf("" + tmp[0] + "^^<")
                val langIdx = tmp.lastIndexOf("" + tmp[0] + "@")
                return if (tmp.endsWith(">") && typeIdx > 0) {
                    ValueTypedLiteral("" + tmp[0], tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                } else {
                    SanityCheck.check { langIdx > 0 }
                    ValueLanguageTaggedLiteral("" + tmp[0], tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                }
            }
            return ValueSimpleLiteral("" + tmp[0], tmp.substring(1, tmp.length - 1))
        }
    }

    public fun toSparql(): String {
        return valueToString() ?: return "UNDEF"
    }

    public override operator fun compareTo(other: ValueDefinition): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueBnode(@JvmField public var value: String) : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueBnode").addAttribute("value", "" + value)
    public override fun valueToString(): String = "_:$value"
    public override fun equals(other: Any?): Boolean {
        if (other is ValueBnode) {
            return value == other.value
        }
        return false
    }

    public override fun toDouble(): Nothing = throw CanNotCastBNodeToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastBNodeToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastBNodeToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastBNodeToBooleanException()
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBnode) {
            return 1
        }
        return value.compareTo(other.value)
    }

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun plus(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun plus(other: ValueDefinition): ValueDefinition = ValueError()

    public override operator fun minus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun minus(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = ValueError()

    public override operator fun times(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun times(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun times(other: ValueDefinition): ValueDefinition = ValueError()

    public override operator fun div(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun div(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun div(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun div(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun div(other: ValueDefinition): ValueDefinition = ValueError()

    public override operator fun compareTo(value: BigInteger): Int = 1
    public override operator fun compareTo(value: BigDecimal): Int = 1
    public override operator fun compareTo(value: String): Int = 1
    public override operator fun compareTo(value: ValueStringBase): Int = 1
    public override operator fun compareTo(value: ValueIri): Int = 1
    public override operator fun compareTo(value: ValueBnode): Int = this.value.compareTo(value.toString())
    public override operator fun compareTo(value: ValueBoolean): Int = 1
}

public class ValueBoolean(@JvmField public var value: Boolean, x: Boolean) : ValueDefinition() {
    public companion object {
        @JvmField
        internal val localTrue = ValueBoolean(true, true)

        @JvmField
        internal val localFalse = ValueBoolean(false, true)
        public operator fun invoke(value: Boolean): ValueBoolean {
            return if (value) {
                localTrue
            } else {
                localFalse
            }
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueBoolean").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    public override fun equals(other: Any?): Boolean {
        if (other is ValueBoolean) {
            return value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override fun toDouble(): Nothing = throw CanNotCastBooleanToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastBooleanToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastBooleanToIntException()
    public override fun toBoolean(): Boolean = value
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueBoolean) {
            throw IncompatibleTypesDuringCompareException()
        } else if (value == other.value) {
            return 0
        } else if (value && !other.value) {
            return 1
        }
        return -1
    }

    public override fun hashCode(): Int = value.hashCode()

    public override operator fun plus(value: BigInteger): ValueDefinition {
        return if (this.value) {
            ValueInteger(value + BigInteger(1))
        } else {
            ValueInteger(value)
        }
    }

    public override operator fun plus(value: ValueInteger): ValueDefinition {
        return if (this.value) {
            (value + BigInteger(1))
        } else {
            value
        }
    }

    public override operator fun plus(value: BigDecimal): ValueDefinition {
        return if (this.value) {
            ValueDecimal(value + (1.0).toBigDecimal())
        } else {
            ValueDecimal(value)
        }
    }

    public override operator fun plus(value: ValueBoolean): ValueDefinition {
        return if (this.value) {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(2))
            } else {
                ValueInteger(BigInteger(1))
            }
        } else {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(1))
            } else {
                ValueInteger(BigInteger(0))
            }
        }
    }

    public override operator fun plus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(2))
        } else if (other is ValueBoolean && !this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(1))
        } else if (other is ValueBoolean && this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(1))
        } else if (other is ValueBoolean && !this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return ValueDecimal(other.toDecimal() + (1.0).toBigDecimal())
        } else if (!this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return other
        } else if (this.value && other is ValueInteger) {
            return ValueInteger(other.toInt() + BigInteger(1))
        } else if (!this.value && (other is ValueInteger)) {
            return other
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun minus(value: BigInteger): ValueDefinition {
        return if (this.value) {
            ValueInteger(BigInteger(1) - value)
        } else {
            ValueInteger(BigInteger(0) - value)
        }
    }

    public override operator fun minus(value: ValueInteger): ValueDefinition {
        return if (this.value) {
            ValueInteger(BigInteger(1) - value.toInt())
        } else {
            ValueInteger(BigInteger(0) - value.toInt())
        }
    }

    public override operator fun minus(value: BigDecimal): ValueDefinition {
        return if (this.value) {
            ValueDecimal(1.0.toBigDecimal() - value)
        } else {
            ValueDecimal(0.0.toBigDecimal() - value)
        }
    }

    public override operator fun minus(value: ValueBoolean): ValueDefinition {
        return if (this.value) {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(0))
            } else {
                ValueInteger(BigInteger(1))
            }
        } else {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(-1))
            } else {
                ValueInteger(BigInteger(0))
            }
        }
    }

    public override operator fun minus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (other is ValueBoolean && !this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(-1))
        } else if (other is ValueBoolean && this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(1))
        } else if (other is ValueBoolean && !this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return ValueDecimal((1.0).toBigDecimal() - other.toDecimal())
        } else if (!this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return ValueDecimal((0.0).toBigDecimal() - other.toDecimal())
        } else if (this.value && other is ValueInteger) {
            return ValueInteger(BigInteger(1) - other.toInt())
        } else if (!this.value && other is ValueInteger) {
            return ValueInteger(BigInteger(0) - other.toInt())
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun times(value: BigInteger): ValueDefinition {
        return if (this.value) {
            ValueInteger(value)
        } else {
            ValueInteger(BigInteger(0))
        }
    }

    public override operator fun times(value: ValueInteger): ValueDefinition {
        return if (this.value) {
            value
        } else {
            ValueInteger(BigInteger(0))
        }
    }

    public override operator fun times(value: BigDecimal): ValueDefinition {
        return if (this.value) {
            ValueDecimal(value)
        } else {
            ValueDecimal(0.0.toBigDecimal())
        }
    }

    public override operator fun times(value: ValueBoolean): ValueDefinition {
        return if (this.value) {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(1))
            } else {
                ValueInteger(BigInteger(0))
            }
        } else {
            ValueInteger(BigInteger(0))
        }
    }

    public override operator fun times(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(1))
        } else if (other is ValueBoolean && !this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (other is ValueBoolean && this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (other is ValueBoolean && !this.value && !other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return ValueDecimal(other.toDecimal() * (1.0).toBigDecimal())
        } else if (!this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            return ValueDecimal((0.0).toBigDecimal())
        } else if (this.value && other is ValueInteger) {
            return other
        } else if (!this.value && other is ValueInteger) {
            return ValueInteger(BigInteger(0))
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun div(value: BigInteger): ValueDefinition {
        return if (this.value) {
            if (value != BigInteger(0)) {
                ValueInteger(BigInteger(1) / value)
            } else {
                ValueError()
            }
        } else {
            if (value != BigInteger(0)) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueInteger): ValueDefinition {
        return if (this.value) {
            if (value.toInt() != BigInteger(0)) {
                ValueInteger(BigInteger(1) / value.toInt())
            } else {
                ValueError()
            }
        } else {
            if (value.toInt() != BigInteger(0)) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: BigDecimal): ValueDefinition {
        return if (this.value) {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal(1.0.toBigDecimal() / value)
            } else {
                ValueError()
            }
        } else {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal(0.0.toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueBoolean): ValueDefinition {
        return if (this.value) {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(1))
            } else {
                ValueError()
            }
        } else {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(1))
        } else if (other is ValueBoolean && !this.value && other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (other is ValueBoolean && this.value && !other.toBoolean()) {
            return ValueError()
        } else if (other is ValueBoolean && !this.value && !other.toBoolean()) {
            return ValueError()
        } else if (this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            if (other.toDouble() != 0.0) {
                return ValueDecimal(1.0.toBigDecimal() / other.toDecimal())
            } else {
                ValueError()
            }
        } else if (!this.value && (other is ValueDecimal || other is ValueDouble || other is ValueFloat)) {
            if (other.toDouble() != 0.0) {
                return ValueDecimal(0.0.toBigDecimal() / other.toDecimal())
            } else {
                ValueError()
            }
        } else if (this.value && other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueInteger(BigInteger(1) / other.toInt())
            } else {
                ValueError()
            }
        } else if (!this.value && other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueInteger(BigInteger(0) / other.toInt())
            } else {
                ValueError()
            }
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun compareTo(value: BigInteger): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigDecimal): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int {
        if (this.value == value.toBoolean()) {
            return 0
        } else if (this.value && !value.toBoolean()) {
            return 1
        }
        return -1
    }
}

public sealed class ValueNumeric : ValueDefinition()

public class ValueUndef : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueUndef")
    public override fun valueToString(): String? = null
    public override fun equals(other: Any?): Boolean {
        if (other is ValueUndef) {
            return true
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Nothing = throw CanNotCastUndefToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastUndefToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastUndefToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastUndefToBooleanException()
    public override fun hashCode(): Int = 0

    public override operator fun plus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigInteger): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigDecimal): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueBnode): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueError : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueError")
    public override fun valueToString(): String? = null
    public override fun equals(other: Any?): Boolean = throw IncompatibleTypesDuringCompareException()
    public override fun toDouble(): Nothing = throw CanNotCastErrorToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastErrorToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastErrorToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastErrorToBooleanException()
    public override fun hashCode(): Int = 0

    public override operator fun plus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigInteger): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigDecimal): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueBnode): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

public sealed class ValueStringBase(@JvmField public val delimiter: String, @JvmField public val content: String) : ValueDefinition() {
    public override operator fun plus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun plus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun times(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(other: ValueDefinition): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueInteger): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: BigDecimal): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun div(value: ValueBoolean): ValueDefinition = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigInteger): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigDecimal): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: String): Int = valueToString()!!.compareTo(value!!)
    public override operator fun compareTo(value: ValueStringBase): Int = valueToString()!!.compareTo(value.valueToString()!!)
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()

    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        }
        return valueToString()!!.compareTo(other.valueToString()!!)
    }

    public override fun toBoolean(): Boolean = content.isNotEmpty()
    public override fun toDouble(): Nothing = throw CanNotCastLiteralToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastLiteralToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastLiteralToIntException()
}

public class ValueLanguageTaggedLiteral(delimiter: String, content: String, @JvmField public val language: String) : ValueStringBase(delimiter, content) {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueLanguageTaggedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("language", "" + language)
    public override fun valueToString(): String = "$delimiter$content$delimiter@$language"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueLanguageTaggedLiteral) {
            language == other.language && content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode() + language.hashCode()
}

public class ValueSimpleLiteral(delimiter: String, content: String) : ValueStringBase(delimiter, content) {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueSimpleLiteral").addAttribute("delimiter", delimiter).addAttribute("content", content)
    public override fun valueToString(): String = delimiter + content + delimiter
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueSimpleLiteral) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode()
}

public class ValueTypedLiteral(delimiter: String, content: String, @JvmField public val type_iri: String, b: Boolean) : ValueStringBase(delimiter, content) {
    public companion object {
        public operator fun invoke(delimiter: String, content: String, type_iri: String): ValueDefinition {
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> {
                    return ValueBoolean(content.toBoolean())
                }
                "http://www.w3.org/2001/XMLSchema#integer" -> {
                    return ValueInteger(BigInteger.parseString(content, 10))
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#float" -> {
                    return ValueFloat(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
                    return ValueDecimal(BigDecimal.parseString(content, 10))
                }
                "http://www.w3.org/2001/XMLSchema#dateTime" -> {
                    return ValueDateTime("$delimiter$content$delimiter^^<$type_iri>")
                }
                else -> {
                    return ValueTypedLiteral(delimiter, content, type_iri, true)
                }
            }
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueTypedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)
    public override fun valueToString(): String = "$delimiter$content$delimiter^^<$type_iri>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueTypedLiteral && type_iri == other.type_iri) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode() + type_iri.hashCode()
}

public class ValueDecimal(@JvmField public var value: BigDecimal) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDecimal").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"" + value.toPlainString() + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueDecimal) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Double = value.doubleValue()
    public override fun toDecimal(): BigDecimal = value
    public override fun toInt(): BigInteger = value.toBigInteger()
    public override fun toBoolean(): Boolean = value != BigDecimal.parseString("0.0", 10)
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(BigDecimal.fromBigInteger(other.value))
        } else if (other is ValueDecimal) {
            value.compareTo(other.value)
        } else if (other is ValueDouble) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueInteger(this.value.toBigInteger().plus(value))
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueInteger(this.value.toBigInteger().plus(value.value))
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueDecimal(this.value.plus(value))
    public override operator fun plus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueDecimal(this.value + 1.0.toBigDecimal())
        } else {
            ValueDecimal(this.value)
        }
    }

    public override operator fun plus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDecimal(this.value + 1.0.toBigDecimal())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDecimal(this.value)
        } else if (other is ValueDecimal) {
            return ValueDecimal(other.toDecimal() + this.value)
        }
        return ValueDecimal(this.value + (other.toDecimal()))
    }

    public override operator fun minus(value: BigInteger): ValueDefinition = ValueDecimal(this.value.minus(BigDecimal.fromBigInteger(value)))
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueDecimal(this.value.minus(value.toDecimal()))
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueDecimal(this.value.minus(value))
    public override operator fun minus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueDecimal(this.value - 1.0.toBigDecimal())
        } else {
            ValueDecimal(this.value)
        }
    }

    public override operator fun minus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDecimal(this.value - 1.0.toBigDecimal())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDecimal(this.value)
        } else if (other is ValueDecimal) {
            return ValueDecimal(this.value - other)
        }
        return ValueDecimal(this.value - other.toDecimal())
    }

    public override operator fun times(value: BigInteger): ValueDefinition = ValueDecimal(this.value.times(BigDecimal.fromBigInteger(value)))
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueDecimal(this.value.times(value.toDecimal()))
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueDecimal(this.value.times(value))
    public override operator fun times(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueDecimal(this.value)
        } else {
            ValueDecimal(0.0.toBigDecimal())
        }
    }

    public override operator fun times(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDecimal(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDecimal(0.0.toBigDecimal())
        } else if (other is ValueDecimal) {
            return ValueDecimal(this.value * other)
        }
        return ValueDecimal(this.value * other.toDecimal())
    }

    public override operator fun div(value: BigInteger): ValueDefinition {
        return if (this.value != 0.0.toBigDecimal()) {
            if (value != BigInteger.fromInt(0)) {
                ValueDecimal(this.value / BigDecimal.fromBigInteger(value))
            } else {
                ValueError()
            }
        } else {
            if (value != BigInteger.fromInt(0)) {
                ValueDecimal(0.0.toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueInteger): ValueDefinition {
        return if (this.value != 0.0.toBigDecimal()) {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueDecimal(this.value / value.toDecimal())
            } else {
                ValueError()
            }
        } else {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueDecimal(0.0.toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: BigDecimal): ValueDefinition {
        return if (this.value != 0.0.toBigDecimal()) {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal(this.value / value)
            } else {
                ValueError()
            }
        } else {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal(0.0.toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueBoolean): ValueDefinition {
        return if (this.value != 0.0.toBigDecimal()) {
            if (value.toBoolean()) {
                ValueDecimal(this.value)
            } else {
                ValueError()
            }
        } else {
            if (value.toBoolean()) {
                ValueDecimal(0.0.toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && this.value != 0.0.toBigDecimal() && other.toBoolean()) {
            return ValueDecimal(this.value)
        } else if (other is ValueBoolean && this.value == 0.0.toBigDecimal() && other.toBoolean()) {
            return ValueDecimal(0.0.toBigDecimal())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueError()
        } else if (other is ValueDecimal || other is ValueDouble || other is ValueFloat) {
            if (other.toDouble() != 0.0) {
                return ValueDecimal(this.value / other)
            } else {
                ValueError()
            }
        } else if (other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueDecimal(this.value / other.toDecimal())
            } else {
                ValueError()
            }
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun compareTo(value: BigInteger): Int = this.value.compareTo(BigDecimal.fromBigInteger(value))
    public override operator fun compareTo(value: BigDecimal): Int = this.value.compareTo(value)
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueDouble(@JvmField public var value: Double) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDouble").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#double>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueDouble) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Double = value
    public override fun toDecimal(): BigDecimal = value.toBigDecimal()
    public override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger()
    public override fun toBoolean(): Boolean = value > 0 || value < 0
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDouble) {
            value.compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueDouble(this.value.plus(value.doubleValue()))
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueDouble(this.value.plus(value.toDouble()))
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueDouble(this.value.plus(value.doubleValue()))
    public override operator fun plus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueDouble(this.value + 1.0.toBigDecimal().doubleValue())
        } else {
            ValueDouble(this.value)
        }
    }

    public override operator fun plus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDouble(this.value + 1.0.toBigDecimal().doubleValue())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDouble(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueDouble(other.toDouble() + this.value)
        }
        return ValueDouble(this.value + other.toDouble())
    }

    public override operator fun minus(value: BigInteger): ValueDefinition = ValueDouble(this.value.minus(value.doubleValue()))
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueDouble(this.value.minus(value.toDouble()))
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueDouble(this.value.minus(value.doubleValue()))
    public override operator fun minus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueDouble(this.value - 1.0.toBigDecimal().doubleValue())
        } else {
            ValueDouble(this.value)
        }
    }

    public override operator fun minus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDouble(this.value - 1.0.toBigDecimal().doubleValue())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDouble(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueDouble(this.value - other.toDouble())
        }
        return ValueDouble(this.value - other.toDouble())
    }

    public override operator fun times(value: BigInteger): ValueDefinition = invoke(this.value.times(value.doubleValue()).toString())
    public override operator fun times(value: ValueInteger): ValueDefinition = invoke(this.value.times(value.toDouble()).toString())
    public override operator fun times(value: BigDecimal): ValueDefinition = invoke(this.value.times(value.doubleValue()).toString())
    public override operator fun times(value: ValueBoolean): ValueDefinition {
        if (value.toBoolean()) {
            return ValueDouble(this.value)
        } else {
            return ValueDouble(0.0)
        }
    }

    public override operator fun times(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDouble(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueDouble(0.0)
        } else if (other is ValueDecimal || other is ValueFloat || other is ValueDouble) {
            return ValueDouble(other.toDouble() * this.value)
        }
        return ValueDouble(this.value * other.toDouble())
    }

    public override operator fun div(value: BigInteger): ValueDefinition {
        return if (this.value != 0.0) {
            if (value != BigInteger.fromInt(0)) {
                ValueDouble(this.value / value.doubleValue())
            } else {
                ValueError()
            }
        } else {
            if (value != BigInteger.fromInt(0)) {
                ValueDouble(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueInteger): ValueDefinition {
        return if (this.value != 0.0) {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueDouble(this.value / value.toDouble())
            } else {
                ValueError()
            }
        } else {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueDouble(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: BigDecimal): ValueDefinition {
        return if (this.value != 0.0) {
            if (value != 0.0.toBigDecimal()) {
                ValueDouble(this.value / value.doubleValue())
            } else {
                ValueError()
            }
        } else {
            if (value != 0.0.toBigDecimal()) {
                ValueDouble(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueBoolean): ValueDefinition {
        return if (this.value != 0.0) {
            if (value.toBoolean()) {
                ValueDouble(this.value)
            } else {
                ValueError()
            }
        } else {
            if (value.toBoolean()) {
                ValueDouble(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueDouble(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueError()
        } else if (other is ValueDecimal || other is ValueDouble || other is ValueFloat) {
            if (other.toDouble() != 0.0) {
                return ValueDouble(this.value / other.toDouble())
            } else {
                ValueError()
            }
        } else if (other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueDouble(this.value / other.toDouble())
            } else {
                ValueError()
            }
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun compareTo(value: BigInteger): Int = this.value.compareTo(value.doubleValue())
    public override operator fun compareTo(value: BigDecimal): Int = this.value.compareTo(value.doubleValue())
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueFloat(@JvmField public var value: Double) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueFloat").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#float>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueFloat) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDecimal(): BigDecimal = value.toBigDecimal()
    public override fun toDouble(): Double = value
    public override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger()
    public override fun toBoolean(): Boolean = value > 0 || value < 0
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDouble) {
            value.compareTo(other.value)
        } else if (other is ValueFloat) {
            value.compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override operator fun plus(value: BigInteger): ValueDefinition = invoke(this.value.plus(value.doubleValue()).toString())
    public override operator fun plus(value: ValueInteger): ValueDefinition = invoke(this.value.plus(value.toDouble()).toString())
    public override operator fun plus(value: BigDecimal): ValueDefinition = invoke(this.value.plus(value.doubleValue()).toString())
    public override operator fun plus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueFloat(this.value + 1.0.toBigDecimal().doubleValue())
        } else {
            ValueFloat(this.value)
        }
    }

    public override operator fun plus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueFloat(this.value + 1.0.toBigDecimal().doubleValue())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueFloat(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueFloat(other.toDouble() + this.value)
        }
        return ValueFloat(this.value + other.toDouble())
    }

    public override operator fun minus(value: BigInteger): ValueDefinition = ValueFloat(this.value.minus(value.doubleValue()))
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueFloat(this.value.minus(value.toDouble()))
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueFloat(this.value.minus(value.doubleValue()))
    public override operator fun minus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueFloat((this.value - 1.0.toBigDecimal().doubleValue()))
        } else {
            ValueFloat(this.value)
        }
    }

    public override operator fun minus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueFloat(this.value - 1.0.toBigDecimal().doubleValue())
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueFloat(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueFloat(this.value - other.toDouble())
        }
        return ValueFloat(this.value - other.toDouble())
    }

    public override operator fun times(value: BigInteger): ValueDefinition = ValueFloat(this.value.times(value.doubleValue()))
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueFloat(this.value.times(value.toDouble()))
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueFloat(this.value.times(value.doubleValue()))
    public override operator fun times(value: ValueBoolean): ValueDefinition {
        if (value.toBoolean()) {
            return ValueFloat(this.value)
        } else {
            return ValueFloat(0.0)
        }
    }

    public override operator fun times(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueFloat(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueFloat(0.0)
        } else if (other is ValueDecimal || other is ValueFloat || other is ValueDouble) {
            return ValueFloat(other.toDouble() * this.value)
        }
        return ValueFloat(this.value + other.toDouble())
    }

    public override operator fun div(value: BigInteger): ValueDefinition {
        return if (this.value != 0.0) {
            if (value != BigInteger.fromInt(0)) {
                ValueFloat(this.value / value.doubleValue())
            } else {
                ValueError()
            }
        } else {
            if (value != BigInteger.fromInt(0)) {
                ValueFloat(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueInteger): ValueDefinition {
        return if (this.value != 0.0) {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueFloat(this.value / value.toDouble())
            } else {
                ValueError()
            }
        } else {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueFloat(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: BigDecimal): ValueDefinition {
        return if (this.value != 0.0) {
            if (value != 0.0.toBigDecimal()) {
                ValueFloat(this.value / value.doubleValue())
            } else {
                ValueError()
            }
        } else {
            if (value.doubleValue() != 0.0) {
                ValueFloat(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueBoolean): ValueDefinition {
        return if (this.value != 0.0) {
            if (value.toBoolean()) {
                ValueFloat(this.value)
            } else {
                ValueError()
            }
        } else {
            if (value.toBoolean()) {
                ValueFloat(0.0)
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueFloat(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueError()
        } else if (other is ValueDecimal || other is ValueDouble || other is ValueFloat) {
            if (other.toDouble() != 0.0) {
                return ValueFloat(this.value / other.toDouble())
            } else {
                ValueError()
            }
        } else if (other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueFloat(this.value / other.toDouble())
            } else {
                ValueError()
            }
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun compareTo(value: BigInteger): Int = this.value.compareTo(value.doubleValue())
    public override operator fun compareTo(value: BigDecimal): Int = this.value.compareTo(value.doubleValue())
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

// Drehung
@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigDecimal.compareTo(value: ValueDefinition): Int = -value.compareTo(this)

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigInteger.plus(value: ValueDefinition): BigInteger = value.plus(this).toInt()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigDecimal.plus(value: ValueDefinition): BigDecimal = value.plus(this).toDecimal()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigInteger.minus(value: ValueDefinition): BigInteger = BigInteger(-1) * value.minus(this).toInt()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigDecimal.minus(value: ValueDefinition): BigDecimal = (-1.0).toBigDecimal() * value.minus(this).toDecimal()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigInteger.times(value: ValueDefinition): BigInteger = value.times(this).toInt()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigDecimal.times(value: ValueDefinition): BigDecimal = value.times(this).toDecimal()

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigInteger.div(value: ValueDefinition): BigInteger {
    if (this != BigInteger.fromInt(0)) {
        return (BigInteger(1) / value.div(this).toInt())
    } else {
        return BigInteger(0)
    }
}

@Suppress("NOTHING_TO_INLINE")
public inline operator fun BigDecimal.div(value: ValueDefinition): BigDecimal {
    if (this != 0.0.toBigDecimal()) {
        return (1.0.toBigDecimal() / value.div(this).toDecimal())
    } else {
        return 0.0.toBigDecimal()
    }
}

public class ValueInteger(@JvmField public var value: BigInteger) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueInteger").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueInteger) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDecimal(): BigDecimal = BigDecimal.fromBigInteger(value)
    public override fun toDouble(): Double = value.doubleValue()
    public override fun toInt(): BigInteger = value
    public override fun toBoolean(): Boolean = value != BigInteger.parseString("0", 10)
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value)
        } else if (other is ValueDecimal) {
            BigDecimal.fromBigInteger(value).compareTo(other.value)
        } else if (other is ValueDouble) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueInteger(this.value.plus(value))
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueInteger(this.value.plus(value.toInt()))
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueDecimal(BigDecimal.fromBigInteger(this.value).plus(value))
    public override operator fun plus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueInteger((this.value + BigInteger(1)))
        } else {
            ValueInteger(this.value)
        }
    }

    public override operator fun plus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueInteger(this.value + BigInteger(1))
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueInteger(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueDecimal(BigDecimal.fromBigInteger(this.value).plus(other))
        } else if (other is ValueInteger) {
            return ValueInteger(other.toInt() + this.value)
        }
        return ValueError()
    }

    public override operator fun minus(value: BigInteger): ValueDefinition = ValueInteger(this.value.minus(value))
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueInteger(this.value.minus(value.toInt()))
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueDecimal(BigDecimal.fromBigInteger(this.value).minus(value))
    public override operator fun minus(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueInteger(this.value - BigInteger(1))
        } else {
            ValueInteger(this.value)
        }
    }

    public override operator fun minus(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueInteger(this.value - BigInteger(1))
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueInteger(this.value)
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueDecimal(BigDecimal.fromDouble(this.value.doubleValue() - other.toDouble()))
        } else if (other is ValueInteger) {
            return ValueInteger(this.value - other.toInt())
        }
        return ValueError()
    }

    public override operator fun times(value: BigInteger): ValueDefinition = ValueInteger(this.value * value)
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueInteger(this.value.times(value.toInt()))
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueDecimal(BigDecimal.fromBigInteger(this.value).times(value))
    public override operator fun times(value: ValueBoolean): ValueDefinition {
        return if (value.toBoolean()) {
            ValueInteger(this.value)
        } else {
            ValueInteger(BigInteger(0))
        }
    }

    public override operator fun times(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueInteger(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueInteger(BigInteger(0))
        } else if (other is ValueDecimal || other is ValueFloat) {
            return ValueDecimal(BigDecimal.fromDouble(other.toDouble() * this.value.doubleValue()))
        } else if (other is ValueInteger) {
            return ValueInteger(other.toInt() * this.value)
        }
        return ValueError()
    }

    public override operator fun div(value: BigInteger): ValueDefinition {
        return if (this.value != BigInteger(0)) {
            if (value != BigInteger.fromInt(0)) {
                ValueDecimal(BigDecimal.fromDouble(this.value.doubleValue() / value.doubleValue()))
            } else {
                ValueError()
            }
        } else {
            if (value != BigInteger(0)) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueInteger): ValueDefinition {
        return if (this.value != BigInteger.fromInt(0)) {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueDecimal(BigDecimal.fromDouble(this.value.doubleValue() / value.toDouble()))
            } else {
                ValueError()
            }
        } else {
            if (value.toInt() != BigInteger.fromInt(0)) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: BigDecimal): ValueDefinition {
        return if (this.value != BigInteger.fromInt(0)) {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal(BigDecimal.fromBigInteger(this.value) / value)
            } else {
                ValueError()
            }
        } else {
            if (value != 0.0.toBigDecimal()) {
                ValueDecimal((0.0).toBigDecimal())
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(value: ValueBoolean): ValueDefinition {
        return if (this.value != BigInteger.fromInt(0)) {
            if (value.toBoolean()) {
                ValueInteger(this.value)
            } else {
                ValueError()
            }
        } else {
            if (value.toBoolean()) {
                ValueInteger(BigInteger(0))
            } else {
                ValueError()
            }
        }
    }

    public override operator fun div(other: ValueDefinition): ValueDefinition {
        if (other is ValueBnode || other is ValueIri || other is ValueDateTime || other is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        } else if (other is ValueBoolean && other.toBoolean()) {
            return ValueInteger(this.value)
        } else if (other is ValueBoolean && !other.toBoolean()) {
            return ValueError()
        } else if (other is ValueDecimal || other is ValueDouble || other is ValueFloat) {
            if (other.toDouble() != 0.0) {
                return ValueDecimal(BigDecimal.fromDouble(this.value.doubleValue() / other.toDouble()))
            } else {
                ValueError()
            }
        } else if (this.value != BigInteger.fromInt(0) && other is ValueInteger) {
            if (other.toInt() != BigInteger.fromInt(0)) {
                return ValueDecimal(BigDecimal.fromDouble(this.value.doubleValue() / other.toDouble()))
            } else {
                ValueError()
            }
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override operator fun compareTo(value: BigInteger): Int {
        return (this.value - value).intValue()
    }

    public override operator fun compareTo(value: BigDecimal): Int {
        return (this.value - BigInteger.tryFromDouble(value.doubleValue())).intValue()
    }

    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueIri(@JvmField public var iri: String) : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueIri").addAttribute("value", "" + iri)
    public override fun valueToString(): String = "<$iri>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueIri) {
            iri == other.iri
        } else {
            false
        }
    }

    public override fun toDouble(): Double = throw CanNotCastIriToDoubleException()
    public override fun toDecimal(): BigDecimal = throw CanNotCastIriToDecimalException()
    public override fun toInt(): BigInteger = throw CanNotCastIriToIntException()
    public override fun toBoolean(): Boolean = throw CanNotCastIriToBooleanException()
    public override fun hashCode(): Int = iri.hashCode()

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun plus(other: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun plus(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun minus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun minus(other: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun times(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun times(other: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun times(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun div(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun div(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun div(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun div(other: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun div(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun compareTo(value: BigDecimal): Int = 1
    public override operator fun compareTo(value: BigInteger): Int = 1
    public override operator fun compareTo(value: String): Int = 1
    public override operator fun compareTo(value: ValueStringBase): Int = 1
    public override operator fun compareTo(value: ValueBnode): Int = 1
    public override operator fun compareTo(value: ValueIri): Int = iri.compareTo(value.iri)
    public override operator fun compareTo(value: ValueBoolean): Int = 1
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueIri) {
            return 1
        }
        return iri.compareTo(other.iri)
    }
}

public class ValueDateTime : ValueDefinition {
    @JvmField
    public val year: BigInteger

    @JvmField
    public val month: Int

    @JvmField
    public val day: Int

    @JvmField
    public val hours: Int

    @JvmField
    public val minutes: Int

    @JvmField
    public val seconds: BigDecimal

    @JvmField
    public val timezoneHours: Int

    @JvmField
    public val timezoneMinutes: Int

    public override operator fun plus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun plus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun plus(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun plus(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun minus(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun minus(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun minus(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun minus(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun times(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun times(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun times(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun times(value: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun times(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun div(value: BigInteger): ValueDefinition = ValueError()
    public override operator fun div(value: ValueInteger): ValueDefinition = ValueError()
    public override operator fun div(value: BigDecimal): ValueDefinition = ValueError()
    public override operator fun div(other: ValueBoolean): ValueDefinition = ValueError()
    public override operator fun div(other: ValueDefinition): ValueDefinition = ValueError()
    public override operator fun compareTo(value: BigInteger): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: BigDecimal): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: String): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueStringBase): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueBoolean): Int = throw IncompatibleTypesDuringCompareException()
    public override operator fun compareTo(value: ValueIri): Int = -1
    public override operator fun compareTo(value: ValueBnode): Int = -1
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueDateTime) {
            ValueError()
        } else if (year != other.year) {
            return year.compareTo(other.year)
        } else if (month != other.month) {
            return month.compareTo(other.month)
        } else if (day != other.day) {
            return day.compareTo(other.day)
        } else if (hours != other.hours) {
            return hours.compareTo(other.hours)
        } else if (minutes != other.minutes) {
            return minutes.compareTo(other.minutes)
        } else if (seconds != other.seconds) {
            return seconds.compareTo(other.seconds)
        } else if (timezoneHours != other.timezoneHours) {
            return timezoneHours.compareTo(other.timezoneHours)
        } else if (timezoneMinutes != other.timezoneMinutes) {
            return timezoneMinutes.compareTo(other.timezoneMinutes)
        }
        return 0
    }

    public constructor() : super() {
        val time = DateHelper()
        year = BigInteger.parseString(time.year().toString(), 10)
        month = time.month()
        day = time.day()
        hours = time.hours()
        minutes = time.minutes()
        seconds = BigDecimal.parseString(time.seconds().toString(), 10)
        timezoneHours = 0
        timezoneMinutes = 0
    }

    public constructor(str2: String) : super() {
        val str = str2.substring(0, str2.indexOf(str2[0], 1) + 1)
        var idx = 0
        var idx2 = str.indexOf('-', 2)
        if (idx2 < idx) {
            idx2 = str.length - 1
        }
        if (idx2 > idx) {
            year = BigInteger.parseString(str.substring(idx + 1, idx2), 10)
            idx = idx2
            idx2 = str.indexOf('-', idx + 1)
            if (idx2 < idx) {
                idx2 = str.length - 1
            }
            if (idx2 > idx) {
                month = str.substring(idx + 1, idx2).toInt()
                idx = idx2
                idx2 = str.indexOf('T', idx + 1)
                if (idx2 < idx) {
                    idx2 = str.length - 1
                }
                if (idx2 > idx) {
                    day = str.substring(idx + 1, idx2).toInt()
                    idx = idx2
                    idx2 = str.indexOf(':', idx + 1)
                    if (idx2 < idx) {
                        idx2 = str.length - 1
                    }
                    if (idx2 > idx) {
                        hours = str.substring(idx + 1, idx2).toInt()
                        idx = idx2
                        idx2 = str.indexOf(':', idx + 1)
                        if (idx2 < idx) {
                            idx2 = str.length - 1
                        }
                        if (idx2 > idx) {
                            minutes = str.substring(idx + 1, idx2).toInt()
                            idx = idx2
                            val idxa = str.indexOf('Z', idx + 1)
                            val idxb = str.indexOf('+', idx + 1)
                            val idxc = str.indexOf('-', idx + 1)
                            if (idxa > idx) {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxa), 10)
                                timezoneHours = 0
                                timezoneMinutes = 0
                            } else if (idxb > idx) {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxb), 10)
                                idx = idxb
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx + 1, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length - 1).toInt()
                                } else {
                                    timezoneHours = -1
                                    timezoneMinutes = -1
                                }
                            } else if (idxc > idx) {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxc), 10)
                                idx = idxc
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx + 1, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length - 1).toInt()
                                } else {
                                    timezoneHours = -1
                                    timezoneMinutes = -1
                                }
                            } else {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, str.length - 1), 10)
                                timezoneHours = -1
                                timezoneMinutes = -1
                            }
                        } else {
                            minutes = 0
                            seconds = BigDecimal.parseString("0.0", 10)
                            timezoneHours = -1
                            timezoneMinutes = -1
                        }
                    } else {
                        hours = 0
                        minutes = 0
                        seconds = BigDecimal.parseString("0.0", 10)
                        timezoneHours = -1
                        timezoneMinutes = -1
                    }
                } else {
                    day = 0
                    hours = 0
                    minutes = 0
                    seconds = BigDecimal.parseString("0.0", 10)
                    timezoneHours = -1
                    timezoneMinutes = -1
                }
            } else {
                month = 0
                day = 0
                hours = 0
                minutes = 0
                seconds = BigDecimal.parseString("0.0", 10)
                timezoneHours = -1
                timezoneMinutes = -1
            }
        } else {
            year = BigInteger.parseString("0", 10)
            month = 0
            day = 0
            hours = 0
            minutes = 0
            seconds = BigDecimal.parseString("0.0", 10)
            timezoneHours = -1
            timezoneMinutes = -1
        }
    }

    public fun getTZ(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "Z"
        }
        if (timezoneHours == -1 && timezoneMinutes == -1) {
            return ""
        }
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }

    public fun getTimeZone(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        return ""
    }

    public override fun valueToString(): String {
        val secondsString2 = seconds.toString().split(".")
        var secondsString = secondsString2[0].padStart(2, '0')
        if (secondsString2.size > 1 && secondsString2[1] != "0") {
            secondsString += "." + secondsString2[1]
        }
        return if (timezoneHours == -1 && timezoneMinutes == -1) {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:$secondsString-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    public override fun equals(other: Any?): Boolean {
        if (other is ValueDateTime) {
            return valueToString() == other.valueToString()
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override fun hashCode(): Int = valueToString().hashCode()
    public override fun toDouble(): Double = throw CanNotCastDateTimeToDoubleException()
    public override fun toDecimal(): BigDecimal = throw CanNotCastDateTimeToDecimalException()
    public override fun toInt(): BigInteger = throw CanNotCastDateTimeToIntException()
    public override fun toBoolean(): Boolean = throw CanNotCastDateTimeToBooleanException()
}
