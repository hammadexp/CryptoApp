package com.matecho.wms.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.matecho.wms.service.ApiClient
import com.matecho.wms.utils.AppConstants.DATE_FORMAT_TZ
import com.maventech.cryptoapp.R
import java.io.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun redirectToActivity(context: Application, activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        removeUserDataToNull(context)
        context.startActivity(intent)
    }

    fun saveToken(context: Context) {
        val sharedPreference = SharedPreference(context)
        AppConstants.TOKEN =
            "Bearer " + sharedPreference.getStringValue(context.getString(R.string.token))
    }

    fun saveToken(context: Context, token: String?) {
        val sharedPreference = SharedPreference(context)
        //        AppConstants.TOKEN = "Bearer " + sharedPreference.getStringValue(context.getString(R.string.token));
        sharedPreference.saveValueInSharedPreference(
            context.getString(R.string.token),
            token
        )
    }

    fun loadToken(context: Context) {
        val sharedPreference = SharedPreference(context)
        AppConstants.TOKEN =
            "Bearer " + sharedPreference.getStringValue(context.getString(R.string.token))
    }

    fun isUserLoggedIn(context: Context):Boolean {
        val sharedPreference = SharedPreference(context)
        return sharedPreference.getBooleanValue(context.getString(R.string.is_logged_in))
    }

    private fun removeUserDataToNull(context: Context) {
        val sharedPreference = SharedPreference(context)
        sharedPreference.saveValueInSharedPreference(context.getString(R.string.token), null)
        sharedPreference.saveValueInSharedPreference(context.getString(R.string.first_name), null)
        sharedPreference.saveValueInSharedPreference(context.getString(R.string.last_name), null)
        sharedPreference.saveValueInSharedPreference(
            context.getString(R.string.user_password),
            null
        )
        sharedPreference.saveValueInSharedPreference(
            context.getString(R.string.is_logged_in),
            false
        )
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun loadURL(context: Activity?) {
        /* SharedPreference sharedPreference = new SharedPreference(context);
        String refCode = sharedPreference.getStringValue(context.getResources().getString(R.string.ref_code));
        if (refCode != null && !refCode.equals("")) {
            ApiClient.BASE_URL = "https://" + refCode + ApiClient.POST_URL;
            ApiClient.REFERER = refCode + ApiClient.POST_URL;
            ApiClient.BASE_PATH = ApiClient.BASE_URL;
        }*/
        /* else {
            sharedPreference.saveValueInSharedPreference(context.getString(R.string.is_registered), false);
            Helper.redirectToActivity(context.getApplication(), SplashActivity.class);
        }*/
    }

    fun makeLog(context: Context, log: String) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context /* Activity context */)
        val isDebuggingOn = sharedPreferences.getBoolean(
            context.resources.getString(R.string.debugging),
            context.resources.getBoolean(R.bool.debuggingDefaultValue)
        )
        if (isDebuggingOn) {
            val currentTime = Calendar.getInstance().time
            val sharedPreference = SharedPreference(context)
            val username =
                sharedPreference.getStringValue(context.resources.getString(R.string.user_email))
            val refCode =
                sharedPreference.getStringValue(context.resources.getString(R.string.ref_code))
            val manager = context.packageManager
            var info: PackageInfo? = null
            try {
                info = manager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            var versionName: String? = null
            var versionCode: String? = null
            if (info != null) {
                versionName = info.versionName
                versionCode = info.versionCode.toString() + ""
            }
            val isReg =
                sharedPreference.getBooleanValue(context.resources.getString(R.string.is_registered))
            val s = """ Time: $currentTime
 URL: ${ApiClient.BASE_URL}
 REFERER: ${ApiClient.REFERER}
 USERNAME: $username
 SP_REFCODE: $refCode
 ISREGISTERED: $isReg
 VERNAME $versionName
 VERCODE $versionCode
 DEVICE: ${Build.MODEL}
 SCREEN NAME: ${context.javaClass.simpleName}
 Activity: $log"""
            Log.d("WMS", "makeLog: $s")
            appendLog(context, s)
        }
    }


    @Throws(Exception::class)
    fun convertStreamToString(`is`: InputStream?): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String? = null
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }

    fun truncate(str: String, length: Int, delim: String?): String? {
        val len = Math.max(0, length)
        return if (str.length <= len) str else {
            val idx = str.lastIndexOf(delim!!, len)
            str.substring(0, if (idx != -1) idx else len)
        }
    }

    @Throws(Exception::class)
    fun getStringFromFile(filePath: String?): String {
        val fl = File(filePath)
        val fin = FileInputStream(fl)
        val ret = convertStreamToString(fin)
        //Make sure you close all streams.
        fin.close()
        return ret
    }

    fun appendLog(context: Context?, text: String?) {
        val dir = File(Environment.getExternalStorageDirectory().toString() + "/wms/")
        dir.mkdirs()
        val fileName = "logs.txt"
        val logFile =
            File(Environment.getExternalStorageDirectory().toString() + "/wms/" + fileName)
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun getFormattedDate(dateString:String):String{
        val formatter = SimpleDateFormat(DATE_FORMAT_TZ)
        formatter.timeZone = TimeZone.getDefault()
        var value: Date? = null
        try {
            value = formatter.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateFormatter = SimpleDateFormat("dd MMM, yyyy")
        // dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatter.format(value)

    }

}