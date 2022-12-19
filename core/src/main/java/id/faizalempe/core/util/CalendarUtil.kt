package id.faizalempe.core.util

import android.annotation.SuppressLint
import id.faizalempe.core.ext.safe
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * @author Faizal Muhammad Priyowibowo (faizal.priyowibowo@dana.id)
 * @version CalendarUtil, v 0.1 18/12/22 21.20 by Faizal Muhammad Priyowibowo
 */
@SuppressLint("SimpleDateFormat")
object CalendarUtil {

    object Format {
        const val DATE_FORMAT = "yyyy-MM-dd"
        const val MONTH_FORMAT = "MMM"
    }

    fun getCurrentCalendar() = Calendar.getInstance()

    fun getCurrentYear() = getCurrentCalendar().get(Calendar.YEAR)

    fun getCurrentMonth() = getCurrentCalendar().get(Calendar.MONTH)

    fun getCurrentDayOfMonth() = getCurrentCalendar().get(Calendar.DAY_OF_MONTH)

    fun getCalendarDate(year: Int, month: Int, dayOfMonth: Int) = getCurrentCalendar().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, dayOfMonth)
    }

    fun getCurrentDate(format: String = Format.DATE_FORMAT): String =
        getCurrentCalendar().toDateFormat(format)

    fun Calendar.toDateFormat(format: String = Format.DATE_FORMAT): String =
        SimpleDateFormat(format).format(this.time)

    fun String.toCalendar(format: String = Format.DATE_FORMAT): Calendar = getCurrentCalendar().apply {
        val sdf = SimpleDateFormat(format)
        safe(sdf.parse(this@toCalendar)) { time = it }
    }


}