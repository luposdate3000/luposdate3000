package lupos.io.buffer

class JVMByteArrayPage {
    @JvmField // this does not generate any getter avoiding a virtual method call!
    val PAGESIZE = 8*1024
	@JvmField
    val byteArray = ByteArray(PAGESIZE)
    @JvmField
    var locked = 0
    @JvmField
    var modified = false

    constructor()

    inline fun getInt(address: Long): Int {
        val adr = address.toInt()
        return (0xFF and byteArray[adr].toInt()) or ((0xFF and byteArray[adr+1].toInt()) or ((0xFF and byteArray[adr+2].toInt()) or ((0xFF and byteArray[adr+3].toInt()) shl 8) shl 8) shl 8)
    }
    inline fun getByte(address: Long): Byte {
        return this.byteArray[address.toInt()]
    }
    inline fun putInt(address: Long, data: Int){
        this.modified = true
        val adr0 = address.toInt()
        this.byteArray[adr0] = data.toByte()
        val remaining1 = data ushr 8
        val adr1 = adr0 + 1
        this.byteArray[adr1] = remaining1.toByte()
        val remaining2 = remaining1 ushr 8
        val adr2 = adr1 + 1
        this.byteArray[adr2] = remaining2.toByte()
        val remaining3 = remaining2 ushr 8
        val adr3 = adr2 + 1
        this.byteArray[adr3] = remaining3.toByte()
    }
    inline fun putByte(address: Long, data: Byte){
        this.modified = true
        this.byteArray[address.toInt()] = data
    }
    inline fun putString(address: Long, data: String):Long {
        this.modified = true
        val size = data.length
        this.putInt(address, size)
        var pos = address + 4
        for(i in 0 until size) {
            val strChar = data[i]
            this.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
            pos++
            this.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
            pos++
        }
        return pos
    }
    inline fun getPageIndex():Long = 0L
    inline fun lock(){
        this.locked++
    }
    inline fun unlock(){
        this.locked--
    }
    inline fun isLocked():Boolean {
        return this.locked>0
    }
    inline fun release(){}
    inline fun isModified() = this.modified
}