import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.runBlocking

object Main {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        val message = suspendWithDelay()
        println(message)
    }
}

fun callbackWithDelay(callback: (String) -> Unit) {
    val timer = Timer(false)
    timer.schedule(1000) {
        callback("done")
        timer.cancel()
    }
}

suspend fun suspendWithDelay(): String = suspendCoroutine { cont ->
    callbackWithDelay { message ->
        cont.resume(message)
    }
}
