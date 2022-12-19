package id.faizalempe.wangku.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import id.faizalempe.wangku.util.ext.clickDebounce

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GenericAdapter, v 0.1 08/12/22 14.17 by Faizal Muhammad Priyowibowo
 */
abstract class GenericRecyclerViewAdapter<T : Any, VB: ViewBinding>(
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { old, new -> old == new },
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean = { old, new -> old == new },
): RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<T, VB>>() {

    private val diffCallback: DiffItemCallback<T> by lazy { DiffItemCallback(areContentsTheSame, areItemsTheSame) }
    private val differ: AsyncListDiffer<T> by lazy { AsyncListDiffer(this, diffCallback) }

    protected lateinit var itemBinding: VB

    abstract fun inflateViewBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBindItem(itemData: T, position: Int)

    abstract fun onItemClick(itemData: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T, VB> {
        val inflater = LayoutInflater.from(parent.context)
        itemBinding = inflateViewBinding(inflater, parent)
        return GenericViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: GenericViewHolder<T, VB>, position: Int) {
        holder.apply { bind(differ.currentList[adapterPosition], ::onBindItem, ::onItemClick) }
    }

    fun setData(newData: List<T>) = differ.submitList(newData)

    fun clearData() = differ.submitList(emptyList())

    class GenericViewHolder<T, VB: ViewBinding>(
        itemBinding: VB
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            itemData: T,
            onBindItem: (itemData: T, position: Int) -> Unit = { _, _ -> },
            onItemClick: (itemData: T, position: Int) -> Unit = { _, _ -> }
        ) {
            onBindItem.invoke(itemData, adapterPosition)
            itemView.clickDebounce { onItemClick.invoke(itemData, adapterPosition) }
        }
    }
}