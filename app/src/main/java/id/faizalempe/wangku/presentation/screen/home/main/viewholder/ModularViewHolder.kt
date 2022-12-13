package id.faizalempe.wangku.presentation.screen.home.main.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.faizalempe.domain.model.modular.ModularDto

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version ModularViewHolder, v 0.1 09/12/22 16.07 by Faizal Muhammad Priyowivowo
 */
abstract class ModularViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(itemData: ModularDto)
}