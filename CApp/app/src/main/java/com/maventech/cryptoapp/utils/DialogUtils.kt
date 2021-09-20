package com.matecho.wms.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.maventech.cryptoapp.R

class DialogUtils {
    private var activity: Activity? = null
    var context: Context? = null
    var dialog: Dialog? = null
    var imageDialog: Dialog? = null
    fun showDialogImage(context: Context?, imagePath: String) {
        imageDialog = Dialog(context!!, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        imageDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //...set cancelable false so that it's never get hidden
        imageDialog!!.setCancelable(true)
        //...that's the layout i told you will inflate later
        imageDialog!!.setContentView(R.layout.dialog_image)
        //...initialize the imageView form infalted layout
        val gifImageView = imageDialog!!.findViewById<ImageView>(R.id.custom_loading_imageView)
        val ivClose = imageDialog!!.findViewById<ImageView>(R.id.iv_close)
        ivClose.setOnClickListener { imageDialog!!.dismiss() }
        Glide.with(activity!!)
                .load(AppConstants.APP_URL + imagePath) /*.apply(new RequestOptions().circleCrop())*/
                .into(gifImageView)
        imageDialog!!.show()
    }

    fun showDialogSuccess(activity: Activity, title: String?, message: String?, toBackPress: Boolean): SweetAlertDialog {
        val sweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
        sweetAlertDialog.setOnDismissListener { if (toBackPress) activity.onBackPressed() }
        sweetAlertDialog
                .setTitleText(title)
                .setContentText(message)
                .show()
        return sweetAlertDialog
    }

    fun showMessageDialog(activity: Activity, title: String?, message: String?, toBackPress: Boolean) {
        val sweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
        sweetAlertDialog.setOnDismissListener { if (toBackPress) activity.onBackPressed() }
        sweetAlertDialog
                .setTitleText(title)
                .setContentText(message)
                .show()
    }

    fun showDialogError(activity: Activity, title: String?, message: String?, toBackPress: Boolean): SweetAlertDialog {
        val sweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
        sweetAlertDialog.setOnDismissListener { if (toBackPress) activity.onBackPressed() }
        sweetAlertDialog
                .setTitleText(title)
                .setContentText(message)
                .show()
        return sweetAlertDialog
    }

    fun showDialogLoading(activity: Activity, title: String?, message: String?, toBackPress: Boolean): SweetAlertDialog {
        val sweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.setTitle(title)
        sweetAlertDialog.contentText = message
        sweetAlertDialog.setCanceledOnTouchOutside(false)
        sweetAlertDialog.setOnDismissListener { if (toBackPress) activity.onBackPressed() }
        sweetAlertDialog
                .show()
        return sweetAlertDialog
    }

    constructor(activity: Activity?) {
        this.activity = activity
    }

    constructor(context: Context?) {
        this.context = context
    }

    fun showDialog() {
        dialog = Dialog(activity!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog!!.setCancelable(false)
        //...that's the layout i told you will inflate later
        dialog!!.setContentView(R.layout.dialog_loader)

        //...initialize the imageView form infalted layout
        val gifImageView = dialog!!.findViewById<ImageView>(R.id.custom_loading_imageView)
        Glide.with(activity!!)
                .load(R.drawable.loader_gif)
                .into(gifImageView)
        dialog!!.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        if (dialog != null) dialog!!.dismiss()
    }
}