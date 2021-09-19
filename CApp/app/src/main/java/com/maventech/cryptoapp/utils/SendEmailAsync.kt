package com.matecho.wms.utils

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import java.lang.ref.WeakReference

class SendEmailAsync(context: Context) : AsyncTask<Void?, Void?, Boolean>() {
    private var progressDialog: ProgressDialog? = null
    private val contextRef: WeakReference<Context>
    var context: Context
    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = ProgressDialog(contextRef.get())
        progressDialog!!.setMessage("Please wait..")
        progressDialog!!.show()
    }

    protected override fun doInBackground(vararg params: Void?): Boolean {
//        return Helper.prepareLogEmail(context)
    return true
    }

    override fun onPostExecute(isSent: Boolean) {
        super.onPostExecute(isSent)
        progressDialog!!.dismiss()
        if (isSent) Toast.makeText(contextRef.get(), "Logs sent to the developer. Thank you!", Toast.LENGTH_SHORT).show() else Toast.makeText(context, "Could not sent logs. May be there are no logs available.", Toast.LENGTH_SHORT).show()
    }

    init {
        contextRef = WeakReference(context)
        this.context = context
    }
}