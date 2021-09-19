package com.maventech.cryptoapp.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.matecho.wms.utils.AppConstants
import com.matecho.wms.utils.SharedPreference
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPreference:SharedPreference;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        );

        val token = sharedPreference.getStringValue(getString(R.string.token))
        AppConstants.TOKEN=token
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}