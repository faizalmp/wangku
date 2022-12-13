package id.faizalempe.wangku.presentation.screen.home.main

import android.os.Bundle
import android.view.View
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.databinding.FragmentMainBinding
import id.faizalempe.wangku.util.base.BaseFragment
import id.faizalempe.wangku.presentation.di.home.main.DaggerMainComponent
import id.faizalempe.wangku.presentation.di.home.main.MainModule
import id.faizalempe.wangku.util.ext.getViewBind
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MainFragment, v 0.1 09/12/22 13.04 by Faizal Muhammad Priyowivowo
 */
class MainFragment : BaseFragment<FragmentMainBinding>(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun inflateViewBinding(): FragmentMainBinding =
        getViewBind(FragmentMainBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
    }

    override fun initView() {
        binding.flipperMain.clickRefresh { presenter.getModularMain() }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun showLoading() {
        binding.flipperMain.showLoading()
    }

    override fun showError(message: String) {
        binding.flipperMain.showError()
    }

    override fun showContent(news: NewsDto) {
        binding.flipperMain.showContent()
        binding.tvTest.text = news.toString()
    }

    private fun inject() {
        DaggerMainComponent.builder()
            .applicationComponent(WangkuApp.getAppComponent(requireContext()))
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }
}