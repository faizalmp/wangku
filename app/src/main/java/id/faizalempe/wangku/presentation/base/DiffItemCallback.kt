package id.faizalempe.wangku.presentation.base

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version DiffItemCallback, v 0.1 08/12/22 14.56 by Faizal Muhammad Priyowibowo
 */
class DiffItemCallback<T : Any>(
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean,
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean
): DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = areContentsTheSame.invoke(oldItem, newItem)

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areItemsTheSame.invoke(oldItem, newItem)
}