package id.faizalempe.wangku.presentation.screen.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.faizalempe.domain.model.news.ArticleDto
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.databinding.FragmentNewsBinding
import id.faizalempe.wangku.databinding.ItemNewsBinding
import id.faizalempe.wangku.presentation.di.home.news.NewsModule
import id.faizalempe.wangku.presentation.di.home.news.DaggerNewsComponent
import id.faizalempe.wangku.util.base.BaseFragment
import id.faizalempe.wangku.util.base.GenericRecyclerViewAdapter
import id.faizalempe.wangku.util.ext.getViewBind
import id.faizalempe.wangku.util.view.LinearItemMarginDecoration
import javax.inject.Inject

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version NewsFragment, v 0.1 09/12/22 13.04 by Faizal Muhammad Priyowivowo
 */
class NewsFragment : BaseFragment<FragmentNewsBinding>(), NewsContract.View {

    @Inject
    lateinit var presenter: NewsPresenter

    private val newsAdapter by lazy {
        object : GenericRecyclerViewAdapter<ArticleDto, ItemNewsBinding>() {

            override fun inflateViewBinding(inflater: LayoutInflater, parent: ViewGroup): ItemNewsBinding =
                ItemNewsBinding.inflate(inflater, parent, false)

            override fun onBindItem(itemData: ArticleDto, position: Int) {
                with(itemBinding) {
                    tvArticleTitle.text = itemData.title
                    tvArticleContent.text = itemData.content
                }
            }

            override fun onItemClick(itemData: ArticleDto, position: Int) {
                // no-op
            }
        }
    }

    override fun inflateViewBinding(): FragmentNewsBinding = getViewBind(FragmentNewsBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
    }

    override fun onResume() {
        super.onResume()
        presenter.getNews()
    }

    override fun initView() {
        val itemSpace = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp)
        binding.rvNews.apply {
            addItemDecoration(
                LinearItemMarginDecoration(itemSpace)
            )
            adapter = newsAdapter
        }
        binding.flipperNews.clickRefresh { presenter.getNews() }
    }

    override fun showLoading() {
        binding.flipperNews.showLoading()
    }

    override fun showError(message: String) {
        binding.flipperNews.showError()
    }

    override fun showContent(news: NewsDto) {
        binding.flipperNews.showContent()
        newsAdapter.setData(news.articles)
    }

    private fun inject() {
        DaggerNewsComponent.builder()
            .applicationComponent(WangkuApp.getAppComponent(requireContext()))
            .newsModule(NewsModule(this))
            .build()
            .inject(this)
    }
}