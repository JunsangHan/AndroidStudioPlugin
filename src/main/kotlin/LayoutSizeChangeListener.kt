interface LayoutSizeChangeListener {
    fun onChanged(info: LayoutSizeInfo)
}
data class LayoutSizeInfo(
    var width: Int,
    var height: Int,
    var dp: Int
)