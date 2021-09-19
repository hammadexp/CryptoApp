package com.matecho.wms.utils

import android.content.Context
import android.content.Intent

class ScannerUtils {
    fun toggleScanner(context: Context) {
        val i = Intent()
        // set the intent action using soft scan trigger action string declared earlier
        i.action = ACTION_SOFTSCANTRIGGER
        // add a string parameter to tell DW that we want to toggle the soft scan trigger
        i.putExtra(EXTRA_PARAM, DWAPI_TOGGLE_SCANNING)
        // now broadcast the intent
        i.putExtra("com.symbol.datawedge.api.CREATE_PROFILE", "Profile1")
        //        i.putExtra("com.symbol.datawedge.api.SWITCH_TO_PROFILE", "DWDemo");
        // request and identify the result code
//        i.putExtra("SEND_RESULT","true");
//        i.putExtra("COMMAND_IDENTIFIER","111");
        context.sendBroadcast(i)
    }

    fun openScanner(context: Context) {
        val i = Intent()
        // set the intent action using soft scan trigger action string declared earlier
        i.action = ACTION_SOFTSCANTRIGGER
        // add a string parameter to tell DW that we want to toggle the soft scan trigger
        i.putExtra(EXTRA_PARAM, DWAPI_START_SCANNING)
        // now broadcast the intent
        i.putExtra("com.symbol.datawedge.api.CREATE_PROFILE", "Profile1")
        //        i.putExtra("com.symbol.datawedge.api.SWITCH_TO_PROFILE", "DWDemo");
        // request and identify the result code
//        i.putExtra("SEND_RESULT","true");
//        i.putExtra("COMMAND_IDENTIFIER","111");
//        Toast.makeText(context, "Scanner opened", Toast.LENGTH_SHORT).show();
        context.sendBroadcast(i)
    }

    fun closeScanner(context: Context) {
        val i = Intent()
        // set the intent action using soft scan trigger action string declared earlier
        i.action = ACTION_SOFTSCANTRIGGER
        // add a string parameter to tell DW that we want to toggle the soft scan trigger
        i.putExtra(EXTRA_PARAM, DWAPI_STOP_SCANNING)
        // now broadcast the intent
        i.putExtra("com.symbol.datawedge.api.CREATE_PROFILE", "Profile1")
        //        i.putExtra("com.symbol.datawedge.api.SWITCH_TO_PROFILE", "DWDemo");
        // request and identify the result code
//        i.putExtra("SEND_RESULT","true");
//        i.putExtra("COMMAND_IDENTIFIER","111");
//        Toast.makeText(context, "Scanner closed", Toast.LENGTH_SHORT).show();
        context.sendBroadcast(i)
    }

    companion object {
        const val ACTION_SOFTSCANTRIGGER = "com.motorolasolutions.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER"
        private const val EXTRA_PARAM = "com.motorolasolutions.emdk.datawedge.api.EXTRA_PARAMETER"
        private const val DWAPI_TOGGLE_SCANNING = "TOGGLE_SCANNING"
        private const val DWAPI_START_SCANNING = "START_SCANNING"
        private const val DWAPI_STOP_SCANNING = "STOP_SCANNING"
    }
}