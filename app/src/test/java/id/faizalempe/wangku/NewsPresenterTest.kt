package id.faizalempe.wangku

import id.faizalempe.core.ext.OnSuccessCallback
import id.faizalempe.domain.usecase.news.GetRemoteNews
import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.wangku.presentation.screen.main.news.NewsContract
import id.faizalempe.wangku.presentation.screen.main.news.NewsPresenter
import io.mockk.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version NewsPresenterTest, v 0.1 19/12/22 19.19 by Faizal Muhammad Priyowibowo
 */
class NewsPresenterTest {


    private val view: NewsContract.View = mockk(relaxed = true)

    private val getRemoteNews: GetRemoteNews = mockk(relaxed = true)

    lateinit var presenter: NewsPresenter

    private val newsDto by lazy { NewsDto() }

    private val scheduler by lazy { Schedulers.trampoline() }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = NewsPresenter(view, getRemoteNews)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `test get news return result`() {
        // given
        val params = GetRemoteNews.Params(1)
        every { getRemoteNews.observe(params, any(), any()) } answers {
            secondArg<OnSuccessCallback<NewsDto>>().invoke(newsDto)
        }

        // when
        presenter.getNews()

        // then
        verify {
            view.showLoading()
            view.showContent(any())
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}