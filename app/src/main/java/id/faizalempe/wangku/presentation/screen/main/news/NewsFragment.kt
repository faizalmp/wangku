package id.faizalempe.wangku.presentation.screen.main.news

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.faizalempe.core.ext.safe
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
        GenericRecyclerViewAdapter<ArticleDto, ItemNewsBinding>(
            areItemsTheSame = { oldItem, newItem -> oldItem.url == newItem.url },
            itemViewBinding = { inflater, parent -> ItemNewsBinding.inflate(inflater, parent, false) },
            onBindItem = { itemData, _ ->
                tvArticleTitle.text = itemData.title
                tvArticleContent.text = itemData.content
                ivArticle.loadImage(itemData.urlToImage)
            },
            onItemClick = { itemData, _ ->
                context.startNewContainerActivity(
                    screen = WangkuScreen.Webview,
                    param = WebviewArgs(url = itemData.url, title = itemData.title)
                )
            }
        )
    }

    private val paginationScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleIdx = (recyclerView.layoutManager as? LinearLayoutManager)
                    ?.findLastVisibleItemPosition()
                safe(lastVisibleIdx) { handlePaginationScroll(it) }
            }
        }
    }

    override fun inflateViewBinding(): FragmentNewsBinding = getViewBind(FragmentNewsBinding::inflate)

    override fun onDestroyView() {
        binding.rvNews.clearOnScrollListeners()
        super.onDestroyView()
    }

    override fun FragmentNewsBinding.init() {
        inject()
        presenter.attachView(lifecycle)
        initView()
        initListener()
        presenter.getNews(true)
    }

    override fun showLoading() = binding.flipperNews.showLoading()

    override fun showPaginationLoading() = binding.pbNewsLoading.visible()

    override fun showError(message: String) = binding.flipperNews.showError()

    override fun showContent(news: NewsDto, isFirstTimeLoad: Boolean) {
        with(binding) {
            flipperNews.showContent()
            if (isFirstTimeLoad) {
                newsAdapter.setData(news.articles)
            } else {
                pbNewsLoading.gone()
                newsAdapter.addData(news.articles)
            }
        }
    }

    private fun FragmentNewsBinding.initView() {
        rvNews.apply {
            addItemDecoration(
                LinearItemMarginDecoration(
                    space = context.getDimen(com.intuit.sdp.R.dimen._8sdp)
                )
            )
            adapter = newsAdapter
            addOnScrollListener(paginationScrollListener)
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

    private fun handlePaginationScroll(lastVisibleIdx: Int) {
        val size = newsAdapter.itemCount
        if (size <= lastVisibleIdx.inc()) presenter.getNews(false)
    }

}