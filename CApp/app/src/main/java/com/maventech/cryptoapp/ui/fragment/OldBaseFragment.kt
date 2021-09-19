package com.maventech.cryptoapp.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.matecho.wms.utils.DialogUtils
import dagger.android.support.DaggerFragment
import java.util.*

open class OldBaseFragment internal constructor() : DaggerFragment() {
    protected var dialogUtils: DialogUtils? = null

    fun initDialogLoader(context: Activity?) {
        dialogUtils = DialogUtils(context)
    }

    fun showLoader() {
        if (dialogUtils != null) dialogUtils!!.showDialog()
    }

    private fun hideLoader() {
        if (dialogUtils != null) dialogUtils!!.hideDialog()
    }

    fun observeLoading(isLoading: MutableLiveData<Int?>, activity: Activity?) {
        initDialogLoader(activity)
        isLoading.removeObservers(viewLifecycleOwner)
        isLoading.observe(viewLifecycleOwner, Observer<Int?> { integer -> if (integer == 1) showLoader() else hideLoader() })
    }

    protected fun checkAndRequestPermissions(): Boolean {
        val permissionCamera = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.CAMERA)
        val permissionStorage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(), listPermissionsNeeded.toTypedArray(), REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }

    companion object {
        private const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    }
}