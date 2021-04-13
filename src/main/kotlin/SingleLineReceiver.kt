import com.android.ddmlib.MultiLineReceiver

class SingleLineReceiver(private val processFirstLine: (response: String) -> Unit) : MultiLineReceiver() {

    private var closed = false

    override fun processNewLines(lines: Array<out String>?) {
        lines?.getOrNull(0)?.let { firstLine ->
            processFirstLine(firstLine)
            closed = true
        }
    }

    override fun isCancelled() = closed
}