package id.faizalempe.wangku.util.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import id.faizalempe.wangku.util.ext.clickDebounce

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GenericAdapter, v 0.1 08/12/22 14.17 by Faizal Muhammad Priyowibowo
 */
abstract class GenericRecyclerViewAdapter<T : Any, VB: ViewBinding>(
) : RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<T>>() {

    private var diffCallback = DiffCallback()
    private val data = mutableListOf<T>()

    protected lateinit var itemBinding: VB

    abstract fun inflateViewBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBindItem(itemData: T, position: Int)

    abstract fun onItemClick(itemData: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        itemBinding = inflateViewBinding(inflater, parent)
        return GenericViewHolder(itemBinding.root, ::onBindItem, ::onItemClick)
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
        private val onBindItem: (itemData: T, position: Int) -> Unit = { _, _ -> },
        private val onItemClick: (itemData: T, position: Int) -> Unit = { _, _ -> }
    ) : RecyclerView.ViewHolder(view) {

        fun bind(itemData: T) {
            onBindItem.invoke(itemData, adapterPosition)
            itemView.clickDebounce { onItemClick.invoke(itemData, adapterPosition) }
        }
    }
}