package id.faizalempe.core.constant

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version WangkuConstant, v 0.1 08/12/22 15.27 by Faizal Muhammad Priyowibowo
 */
object WangkuConstant {

    object Presentation {
        const val NO_RESOURCES = -1
    }

    object Data {

        object News {
            const val DEFAULT_COUNTRY = "us"
            const val DEFAULT_CATEGORY = "business"
            const val DEFAULT_PAGE = 1
            const val DEFAULT_PAGE_SIZE = 10
        }
    }

    object Time {
        const val CLICK_DELAY = 1000L
        const val SPLASH_DELAY = 3000L
    }

    object TransactionCategory {
        const val INCOME = "INCOME"
        const val EXPENSE = "EXPENSE"
        val listCategory = listOf(INCOME, EXPENSE)
    }
}