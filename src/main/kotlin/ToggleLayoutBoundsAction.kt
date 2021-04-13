import com.android.ddmlib.IDevice
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ToggleLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.getNotEmptyDevices()?.forEach { device ->
            device.toggleLayoutBounds()
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }

    private fun IDevice.toggleLayoutBounds() {
        executeShellCommand("getprop debug.layout", SingleLineReceiver {
            setLayoutBounds(it.toBoolean().not())
        })
    }

}

