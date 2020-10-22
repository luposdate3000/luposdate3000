package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.concurrent.Semaphore
import kotlin.jvm.JvmField

internal actual class ParallelThreadJob(action: () -> Unit) : Thread({ action() }) {
}
