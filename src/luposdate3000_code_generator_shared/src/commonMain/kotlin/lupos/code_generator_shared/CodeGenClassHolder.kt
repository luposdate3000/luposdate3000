package lupos.code_generator_shared

import lupos.shared.UUID_Counter
import lupos.shared.dynamicArray.ByteArrayWrapper

public class CodeGenClassHolder(
    private val className: String,
    private val children: Array<CodeGenClassHolder>,
    private val name: String = "",
    private val value: ByteArrayWrapper = ByteArrayWrapper()
) {
    private val uuid = UUID_Counter.getNextUUID()
    public fun getUUID(): Long {
        return uuid
    }
    public fun getChildren(): Array<CodeGenClassHolder> {
        return children
    }
    public fun getClassname(): String {
        return className
    }
    public fun getName(): String {
        return name
    }
    public fun getValue(): ByteArrayWrapper {
        return value
    }
}
