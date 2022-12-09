package id.faizalempe.wangku.presentation.util.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.faizalempe.wangku.presentation.util.ext.clickDebounce

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GenericAdapter, v 0.1 08/12/22 14.17 by Faizal Muhammad Priyowibowo
 */
class GenericRecyclerViewAdapter<T : Any>(
    @LayoutRes private val viewHolderResId: Int,
    private val onBindItem: View.(itemData: T, position: Int) -> Unit = { _, _ -> },
    private val onItemClick: (itemData: T, position: Int) -> Unit = { _, _ -> }
): RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<T>>() {

    private var diffCallback = DiffCallback()
    private val data = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(viewHolderResId, parent, false)
        return GenericViewHolder(view, onBindItem, onItemClick)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.apply {
            bind(data[adapterPosition])
        }
    }

    fun setData(newData: List<T>) {
        diffCallback = DiffCallback(data, newData)
        val result = DiffUtil.calculateDiff(diffCallback)
        data.run {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    fun clearData() {
        diffCallback = DiffCallback()
        val result = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        result.dispatchUpdatesTo(this)

    }

    class GenericViewHolder<T>(
        view: View,
        private val onBindItem: View.(itemData: T, position: Int) -> Unit = { _, _ -> },
        private val onItemClick: (itemData: T, position: Int) -> Unit = { _, _ -> }
    ) : RecyclerView.ViewHolder(view) {
        fun bind(itemData: T) {
            onBindItem.invoke(itemView, itemData, adapterPosition)
            itemView.clickDebounce { onItemClick.invoke(itemData, adapterPosition) }
        }
    }
}