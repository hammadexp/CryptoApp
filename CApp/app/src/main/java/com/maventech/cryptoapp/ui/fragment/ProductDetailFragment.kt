package com.matecho.wms.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.matecho.wms.utils.Helper
import com.matecho.wms.utils.InternetDetector
import com.matecho.wms.utils.SharedPreference
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.FragmentProductDetailBinding
import com.maventech.cryptoapp.viewmodel.ProductViewModel
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductDetailFragment : DaggerFragment() {

    private var binding: FragmentProductDetailBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var viewmodel: ProductViewModel

    @Inject
    lateinit var sharedPreference: SharedPreference


    fun getLifeCycleOwner(): LifecycleOwner = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

        initDashboard()
        viewmodel.isLoading.observe(getLifeCycleOwner(), {
            if (it == 1) {
                binding?.loader?.containerLoader?.visibility = VISIBLE
            } else {
                binding?.loader?.containerLoader?.visibility = GONE
            }
        })


    }


    private fun initDashboard() {
        if (!InternetDetector.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireActivity(), "Internet not available", Toast.LENGTH_SHORT).show()
            return
        }
        if (Helper.isUserLoggedIn(requireContext())) {
            viewmodel.isLoading.value = 1
            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.getOrderDetail(arguments?.getInt("orderId") ?: 0)
                viewmodel.orderDetail.observe(viewLifecycleOwner, {
                    if (it.result != null) {
                        viewmodel.isLoading.value = 0

                    }
                })
            }


        }
    }




}