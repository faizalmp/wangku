package id.faizalempe.wangku.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import id.faizalempe.wangku.util.ext.clickDebounce

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GenericAdapter, v 0.1 08/12/22 14.17 by Faizal Muhammad Priyowibowo
 */
class GenericRecyclerViewAdapter<T : Any, VB: ViewBinding>(
    areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { old, new -> old == new },
    areItemsTheSame: (oldItem: T, newItem: T) -> Boolean = { old, new -> old == new },
    private val itemViewBinding: ViewGroup.() -> VB,
    private val onBindItem: VB.(itemData: T, position: Int) -> Unit = { _, _ -> },
    private val onItemClick: VB.(itemData: T, position: Int) -> Unit = { _, _ -> }
): ListAdapter<T, GenericRecyclerViewAdapter.GenericViewHolder<T, VB>>(
    DiffItemCallback(areContentsTheSame, areItemsTheSame)
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder<T, VB> = GenericViewHolder(itemViewBinding.invoke(parent))

    override fun onBindViewHolder(holder: GenericViewHolder<T, VB>, position: Int) {
        holder.apply { bind(getItem(adapterPosition), onBindItem, onItemClick) }
    }

    fun setData(newData: List<T>) = submitList(newData)

    fun addData(newData: List<T>) {
        val list = currentList.toMutableList().apply { addAll(newData) }
        submitList(list.toList())
    }

    fun clearData() = submitList(null)

    class GenericViewHolder<T, VB: ViewBinding>(
        private val itemBinding: VB
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            itemData: T,
            onBindItem: VB.(itemData: T, position: Int) -> Unit = { _, _ -> },
            onItemClick: VB.(itemData: T, position: Int) -> Unit = { _, _ -> }
        ) {
            onBindItem.invoke(itemBinding, itemData, adapterPosition)
            itemView.clickDebounce { onItemClick.invoke(itemBinding, itemData, adapterPosition) }
        }
    }
}