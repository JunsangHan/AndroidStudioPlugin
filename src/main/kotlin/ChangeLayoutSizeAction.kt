import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ChangeLayoutSizeAction : AnAction() {

    private val layoutForm = LayoutForm()

    override fun actionPerformed(event: AnActionEvent) {
        layoutForm.setListener(object: LayoutSizeChangeListener {
            override fun onChanged(info: LayoutSizeInfo) {
                event.project?.getNotEmptyDevices()?.forEach { device ->
                    device.setLayoutSize(info.width, info.height)
                    device.setLayoutDensity(info.dp)
                } ?: run {
                    event.project?.showNotification("no device connected")
                }

            }
        })

        event.project?.getNotEmptyDevices()?.forEach { device ->
            layoutForm.onShowing()
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }

}