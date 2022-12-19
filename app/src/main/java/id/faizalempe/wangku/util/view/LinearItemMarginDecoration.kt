package id.faizalempe.wangku.util.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version LinearItemMarginDecoration, v 0.1 09/12/22 14.21 by Faizal Muhammad Priyowivowo
 */
class LinearItemMarginDecoration(
    private val space: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as? LinearLayoutManager ?: return
        if (position < parent.childCount) {
            when (layoutManager.orientation) {
                RecyclerView.HORIZONTAL -> outRect.right += space
                RecyclerView.VERTICAL -> outRect.bottom += space
            }
        }
    }
}