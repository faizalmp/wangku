package id.faizalempe.wangku.presentation.screen.main.news

import android.view.LayoutInflater
import android.view.ViewGroup
import id.faizalempe.domain.model.news.ArticleDto
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.WangkuApp
import id.faizalempe.wangku.databinding.FragmentNewsBinding
import id.faizalempe.wangku.databinding.ItemNewsBinding
import id.faizalempe.wangku.navigation.WangkuScreen
import id.faizalempe.wangku.presentation.di.main.news.NewsModule
import id.faizalempe.wangku.presentation.screen.webview.WebviewArgs
import id.faizalempe.wangku.presentation.base.BaseFragment
import id.faizalempe.wangku.presentation.base.GenericRecyclerViewAdapter
import id.faizalempe.wangku.util.ext.*
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
                    ivArticle.loadImage(itemData.urlToImage)
                }
            }

            override fun onItemClick(itemData: ArticleDto, position: Int) {
                context.startNewContainerActivity(
                    screen = WangkuScreen.Webview,
                    param = WebviewArgs(url = itemData.url, title = itemData.title)
                )
            }
        }
    }

    override fun inflateViewBinding(): FragmentNewsBinding = getViewBind(FragmentNewsBinding::inflate)

    override fun onResume() {
        super.onResume()
        presenter.getNews()
    }

    override fun FragmentNewsBinding.init() {
        inject()
        presenter.attachView(lifecycle)
        initView()
        initListener()
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

    private fun FragmentNewsBinding.initView() {
        rvNews.apply {
            addItemDecoration(
                LinearItemMarginDecoration(
                    space = context.getDimen(com.intuit.sdp.R.dimen._8sdp)
                )
            )
            adapter = newsAdapter
        }
    }

    private fun FragmentNewsBinding.initListener() {
        flipperNews.clickRefresh { presenter.getNews() }
    }

    private fun inject() {
        WangkuApp.getAppComponent(requireContext())
            .plus(NewsModule(this))
            .inject(this)
    }

}