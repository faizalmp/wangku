package id.faizalempe.wangku.util.base

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DiffCallback, v 0.1 08/12/22 14.56 by Faizal Muhammad Priyowibowo
 */
class DiffCallback(
    private val oldList: List<Any> = emptyList(),
    private val newList: List<Any> = emptyList()
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].javaClass == newList[newItemPosition].javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
    }
}