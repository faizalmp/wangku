package id.faizalempe.wangku.presentation.screen.main.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.faizalempe.wangku.util.ext.getDimen
import id.faizalempe.wangku.util.ext.startNewContainerActivity
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
import id.faizalempe.wangku.util.ext.getViewBind
import id.faizalempe.wangku.util.ext.loadImage
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

    private val infiniteScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        }
    }

    override fun inflateViewBinding(): FragmentNewsBinding = getViewBind(FragmentNewsBinding::inflate)

    override fun onResume() {
        super.onResume()
        presenter.getNews(true)
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        binding.rvNews.clearOnScrollListeners()
        super.onDestroyView()
    }

    override fun FragmentNewsBinding.init() {
        inject()
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
            addOnScrollListener(infiniteScrollListener)
        }
    }

    private fun FragmentNewsBinding.initListener() {
        flipperNews.clickRefresh { presenter.getNews(true) }
    }

    private fun inject() {
        WangkuApp.getAppComponent(requireContext())
            .plus(NewsModule(this))
            .inject(this)
    }

}