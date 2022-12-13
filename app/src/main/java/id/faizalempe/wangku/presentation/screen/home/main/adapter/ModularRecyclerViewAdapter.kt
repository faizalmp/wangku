package id.faizalempe.wangku.presentation.screen.home.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.faizalempe.domain.model.modular.ModularDto
import id.faizalempe.domain.model.modular.ModularType
import id.faizalempe.domain.model.modular.ModularViewType
import id.faizalempe.wangku.presentation.screen.home.main.viewholder.*
import id.faizalempe.wangku.util.base.DiffCallback
import id.faizalempe.wangku.presentation.screen.home.main.viewholder.ModularViewHolder

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GenericAdapter, v 0.1 08/12/22 14.17 by Faizal Muhammad Priyowibowo
 */
class ModularRecyclerViewAdapter : RecyclerView.Adapter<ModularViewHolder>() {

    private var diffCallback = DiffCallback()
    private val data = mutableListOf<ModularDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModularViewHolder {
        return mapViewHolderResId(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int = mapViewType(data[position])

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ModularViewHolder, position: Int) {
        holder.apply { bind(data[adapterPosition]) }
    }

    fun setData(newData: List<ModularDto>) {
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

    private fun mapViewType(modularDto: ModularDto): Int = when(modularDto.type) {
        ModularType.Profile -> ModularViewType.PROFILE
        ModularType.Balance -> ModularViewType.BALANCE
        ModularType.Shortcut -> ModularViewType.SHORTCUT
        ModularType.News -> ModularViewType.NEWS
        else -> ModularViewType.UNKNOWN
    }

    private fun mapViewHolderResId(parent: ViewGroup, viewType: Int): ModularViewHolder = when(viewType) {
        ModularViewType.PROFILE -> ProfileViewHolder(
            inflate(parent, ProfileViewHolder.layoutResId)
        )
        ModularViewType.BALANCE -> BalanceViewHolder(
            inflate(parent, BalanceViewHolder.layoutResId)
        )
        ModularViewType.SHORTCUT -> ShortcutViewHolder(
            inflate(parent, ShortcutViewHolder.layoutResId)
        )
        ModularViewType.NEWS -> NewsViewHolder(
            inflate(parent, NewsViewHolder.layoutResId)
        )
        else -> UnknownViewHolder(
            inflate(parent, UnknownViewHolder.layoutResId)
        )
    }

    private fun inflate(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): View = LayoutInflater
        .from(parent.context)
        .inflate(layoutResId, parent, false)
}