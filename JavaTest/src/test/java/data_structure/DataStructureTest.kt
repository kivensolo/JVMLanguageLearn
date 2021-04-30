package data_structure

import com.data_structure.queue.CircularQueue
import kotlinx.coroutines.delay

suspend fun main() {
    val circularQueue = CircularQueue<String>(8)
    delay(200)

    for (i in 1 until 10){
        println("offer item +1")
        circularQueue.offer("Haha_$i")
    }
    println("Circular info ${circularQueue.dumpInfo()}")
    println("Peek First data = ${circularQueue.peek()}")

    val data1 = circularQueue.pull()
    println("Pull data = $data1")
    val data2 = circularQueue.pull()
    println("Pull data2 = $data2")
    println("Circular info: ${circularQueue.dumpInfo()}")
    println("Circular dumpData: ${circularQueue.dumpData()}")

}