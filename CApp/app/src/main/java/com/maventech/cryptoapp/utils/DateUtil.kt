package com.matecho.wms.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    val currentDate: String
        get() {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat(AppConstants.DATE_FORMAT2)
            return df.format(c)
        }

    // Start date
    val currentCalendarInstance: Calendar
        get() {
            val dt = DateUtil().currentDate // Start date
            val sdf = SimpleDateFormat("MM-dd-yyyy")
            val c = Calendar.getInstance()
            try {
                c.time = sdf.parse(dt)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return c
        }

    fun getDateFromString(stringDate: String?): Date? {
        var date: Date? = null
        val format = SimpleDateFormat(AppConstants.DATE_FORMAT2)
        try {
            date = format.parse(stringDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    companion object {
        fun addDays(date: Date?, days: Int): Date? {
            if (date != null) {
                val cal = Calendar.getInstance()
                cal.time = date
                cal.add(Calendar.DATE, days) //minus number would decrement the days
                return cal.time
            }
            return null
        }
    }
}