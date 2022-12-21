package id.faizalempe.domain

import id.faizalempe.domain.model.news.NewsDto
import id.faizalempe.domain.repository.NewsRepository
import id.faizalempe.domain.usecase.news.GetRemoteNews
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version GetRemoteNewsTest, v 0.1 21/12/22 13.05 by Faizal Muhammad Priyowibowo
 */
class GetRemoteNewsTest {

    private val scheduler by lazy { Schedulers.trampoline() }

    private val newsRepository: NewsRepository = mockk(relaxed = true)

    private val newsDto by lazy { NewsDto() }

    lateinit var getRemoteNews: GetRemoteNews

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getRemoteNews = GetRemoteNews(newsRepository)
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler }
    }

    @Test
    fun `get remote news return result`() {
        // given
        val page = 1
        val params = GetRemoteNews.Params(page)
        every { newsRepository.getRemoteNews(page) } returns Observable.just(newsDto)

        //when
        getRemoteNews.build(params)

        // then
        verify { newsRepository.getRemoteNews(page) }
    }
}