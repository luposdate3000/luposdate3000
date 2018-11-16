package lupos.io.buffer

import lupos.misc.bit0
import lupos.misc.bit1
import lupos.misc.bit2

inline fun compareInt(int1:Int, int2:Int) = int1-int2
inline fun deserializeInt(page:Page, address:Long) = page.getInt(address)
inline fun serializeInt(page:Page, address:Long, value:Int){ page.putInt(address, value) }
inline fun serializedSizeOfInt(value:Int) = 4L

/* Int stored in variable number of bytes
* First byte: 2 to 3 bits for encoding how many bytes are necessary: 00 -> 1 Byte, 01 -> 2 Bytes, 10 -> 3 Bytes, 110 -> 4 Bytes, 111 -> 5 Bytes
* Int is stored in the remaining 6 to 5 bits in the first byte and the remaining bytes
*/
inline fun serializedSizeOfCompressedInt(value:Int):Long {
    if(value and 0b01111111_11111111_11111111_11_000000==0){
        return 1
    } else if(value and 0b01111111_11111111_11_00000000_000000==0){
        return 2
    } else if(value and 0b01111111_11_00000000_00000000_000000==0){
        return 3
    } else if(value and 0b011_00000000_00000000_00000000_00000==0){
        return 4
    } else {
        return 5
    }
}

inline fun first2to3BytesOfCompressedInt(value:Int):Byte {
    if(value and 0b01111111_11111111_11111111_11_000000==0){
        return 0
    } else if(value and 0b01111111_11111111_11_00000000_000000==0){
        return 1
    } else if(value and 0b01111111_11_00000000_00000000_000000==0){
        return 2
    } else if(value and 0b011_00000000_00000000_00000000_00000==0){
        return 3
    } else {
        return 7
    }
}

inline fun serializeCompressedInt(page:Page, address:Long, value:Int){
    val startingBits = first2to3BytesOfCompressedInt(value)
    if(startingBits<3){
        page.putByte(address, (startingBits + ((value and 0b111111) shl 2)).toByte())
        if(startingBits>0){
            val address1 = address+1
            page.putByte(address1, ((value and 0b11111111_000000) shr 6).toByte())
            if(startingBits>1){
                val address2 = address+2
                page.putByte(address2, ((value and 0b11111111_00000000_000000) shr 14).toByte())
            }
        }
    } else {
        page.putByte(address, (startingBits + ((value and 0b11111) shl 3)).toByte())
        val address1 = address+1
        page.putByte(address1, ((value and 0b11111111_00000) shr 5).toByte())
        val address2 = address+2
        page.putByte(address2, ((value and 0b11111111_00000000_00000) shr 13).toByte())
        val address3 = address+3
        page.putByte(address3, ((value and 0b11111111_00000000_00000000_00000) shr 21).toByte())
        if(startingBits==7.toByte()){
            val address4 = address+4
            page.putByte(address4, ((value and 0b011_00000000_00000000_00000000_00000) shr 29).toByte())
        }
    }
}

inline fun deserializeCompressedInt(page:Page, address:Long):Int {
    val first = page.getByte(address)
    if(first.bit0()){
        if(first.bit1()){
            if(first.bit2()){
                return ((first.toInt() and 0b11111000) shr 3) + ((0xFF and page.getByte(address+1).toInt()) shl 5) + ((0xFF and page.getByte(address+2).toInt()) shl 13) + ((0xFF and page.getByte(address+3).toInt()) shl 21) + ((0xFF and page.getByte(address+4).toInt()) shl 29)
            } else {
                return ((first.toInt() and 0b11111000) shr 3) + ((0xFF and page.getByte(address+1).toInt()) shl 5) + ((0xFF and page.getByte(address+2).toInt()) shl 13) + ((0xFF and page.getByte(address+3).toInt()) shl 21)
            }
        } else {
            return ((first.toInt() and 0b11111100) shr 2) + ((0xFF and page.getByte(address+1).toInt()) shl 6)
        }
    } else {
        if(first.bit1()){
            return ((first.toInt() and 0b11111100) shr 2) + ((0xFF and page.getByte(address+1).toInt()) shl 6) + ((0xFF and page.getByte(address+2).toInt()) shl 14)
        } else {
            return ((first.toInt() and 0b11111100) shr 2)
        }
    }
}

/* difference encoding for sorted Int... */
inline fun deserializeInt(page:Page, address:Long, previous:Int) = deserializeCompressedInt(page, address) + previous
inline fun serializeInt(page:Page, address:Long, value:Int, previous:Int){ serializeCompressedInt(page, address, value-previous) }
inline fun serializedSizeOfInt(value:Int, previous:Int) = serializedSizeOfCompressedInt(value-previous)