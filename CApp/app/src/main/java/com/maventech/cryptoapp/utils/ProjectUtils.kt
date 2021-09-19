package com.matecho.wms.utils

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Danish on 6/3/2018.
 */
object ProjectUtils {
    //region genericIntentMethod
    fun genericIntent(context: Context?, className: Class<*>?, dataPassingBundle: Bundle?,
                      isFinish: Boolean) {
        if (context != null) {
            val intent = Intent()
            className?.let { intent.setClass(context, it) }
            if (dataPassingBundle != null) intent.putExtras(dataPassingBundle)
            context.startActivity(intent)
            if (isFinish) (context as Activity).finish()
        }
    }

    //endregion
    fun getBitmapFromDrawable(context: Context?, @DrawableRes drawableId: Int): Bitmap {
        val drawable = AppCompatResources.getDrawable(context!!, drawableId)
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else if (drawable is VectorDrawableCompat || drawable is VectorDrawable) {
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } else {
            throw IllegalArgumentException("unsupported drawable type")
        }
    }

    fun encodeImage(path: String?): String {
        val imagefile = File(path)
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(imagefile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val bm = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        //Base64.de
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    //region checkIsNetworkAvailable
    fun checkIsNetworkAvailable(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        if (connMgr != null) {
            activeNetwork = connMgr.activeNetworkInfo
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    //endregion
    //region showToast
    fun showToast(mContext: Context?, msg: Int) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    fun showToast(mContext: Context?, msg: String?) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    //endregion
    fun hideKeyboard(mContext: Context, view: View) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getRandomFileName(length: Int): String {
        val chars = "abcdefghijklmnopqrstuvwxyz"
        val rand = Random()
        val buf = StringBuilder()
        for (i in 0 until length) {
            buf.append(chars[rand.nextInt(chars.length)])
        }
        return buf.toString()
        //return "Picture-" + buf.toString() + ".jpg";
    }

    val currentDate: String
        get() {
            val c = Calendar.getInstance()
            val df = SimpleDateFormat("yyyy-MM-")
            return df.format(c.time)
        }

    fun getDateTime(dateString: String?): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        formatter.timeZone = TimeZone.getDefault()
        var value: Date? = null
        try {
            value = formatter.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        // dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatter.format(value)
    }

    fun getDate(dateString: String?): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        formatter.timeZone = TimeZone.getDefault()
        var value: Date? = null
        try {
            value = formatter.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateFormatter = SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'")
        // dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatter.format(value)
    }

    fun checkGPSEnabled(context: Context): Boolean {
        val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            false
        } else true
    }

    fun createGenericDialog(context: Context?, title: String?, message: String?) {
        AlertDialog.Builder(context!!).setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.no) { dialog, which -> dialog.dismiss() }.setPositiveButton(R.string.yes) { dialog, which -> }
                .show()
    }

    @Throws(ParseException::class)
    fun getDaysMonthsFromDate(dateString: String?, _DateFormat: String?): DateModel {
        @SuppressLint("SimpleDateFormat") val dateFormat = SimpleDateFormat(
                _DateFormat)
        //        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        val date = dateFormat.parse(dateString)
        val dateUTC = dateFormat.parse(dateFormat.format(date))
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        Date time = calendar.getTime();*/
        val dayOfTheWeek = DateFormat.format("EEEE", dateUTC) as String // Thursday
        val day = DateFormat.format("dd", dateUTC) as String // 20
        val monthString = DateFormat.format("MMM", dateUTC) as String // Jun
        val monthNumber = DateFormat.format("MM", dateUTC) as String // 06
        val year = DateFormat.format("yyyy", dateUTC) as String // 2013
        val hours = DateFormat.format("hh", dateUTC) as String // Thursday
        val minutes = DateFormat.format("mm", dateUTC) as String // 20
        val seconds = DateFormat.format("a", dateUTC) as String // Jun
        return DateModel(dayOfTheWeek + "",
                day + "",
                monthString + "",
                monthNumber + "",
                year + "",
                hours,
                minutes,
                seconds)
    }

    fun convertTime(_24HourTime: String?): TimeModel? {
        val timeModel: TimeModel? = null
        var parsedTime = ""
        try {
            val _24HourSDF = SimpleDateFormat("HH:mm:ss")
            val _12HourSDF = SimpleDateFormat("hh:mm a")
            val _24HourDt = _24HourSDF.parse(_24HourTime)
            parsedTime = _12HourSDF.format(_24HourDt)
        } catch (e: Exception) {
        }
        return timeModel
    }

    fun getFormatedNumber(number: String): String {
        return if (!number.isEmpty()) {
            val `val` = number.toDouble()
            NumberFormat.getNumberInstance(Locale.US).format(`val`)
        } else {
            "0"
        }
    }

    class TimeModel {
        var days: String? = null
        var hours: String
        var minutes: String
        var seconds: String

        constructor(days: String?, hours: String, minutes: String, seconds: String) {
            this.days = days
            this.hours = hours
            this.minutes = minutes
            this.seconds = seconds
        }

        constructor(hours: String, minutes: String, seconds: String) {
            this.hours = hours
            this.minutes = minutes
            this.seconds = seconds
        }

    }

    class DateModel(// Thursday
            var dayOfTheWeek: String, // 20
            var day: String,
            // Jun
            var monthString: String, // 06
            var monthNumber: String,
            // 2013
            var year: String,
            var hours: String, var minutes: String, var seconds: String)
}