package id.faizalempe.wangku.presentation.screen.home.stats

import id.faizalempe.wangku.databinding.FragmentStatsBinding
import id.faizalempe.wangku.util.base.BaseFragment
import id.faizalempe.wangku.util.ext.getViewBind

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MainFragment, v 0.1 09/12/22 13.04 by Faizal Muhammad Priyowivowo
 */
class StatsFragment : BaseFragment<FragmentStatsBinding>() {

    override fun inflateViewBinding(): FragmentStatsBinding =
        getViewBind(FragmentStatsBinding::inflate)

    override fun initView() {

    }
}