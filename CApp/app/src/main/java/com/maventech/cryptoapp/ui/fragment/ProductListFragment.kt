package com.maventech.cryptoapp.ui.fragment

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
import com.maventech.cryptoapp.databinding.FragmentProductListBinding
import com.maventech.cryptoapp.ui.adapters.ProductListAdapter
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback
import com.maventech.cryptoapp.viewmodel.ProductViewModel
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductListFragment : DaggerFragment() {
    private lateinit var adapter: ProductListAdapter
    private  var binding: FragmentProductListBinding?=null

    private  val _binding:FragmentProductListBinding  get()= this.binding!!

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        initRecyclerView()
        initProductList()
        viewmodel.isLoading.observe(getLifeCycleOwner(),{
            if(it==1){
                binding?.loader?.containerLoader?.visibility=VISIBLE
            }
            else {
                binding?.loader?.containerLoader?.visibility= GONE
            }
        })
    }

    private fun initProductList() {
        if(!InternetDetector.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireActivity(), "Internet not available", Toast.LENGTH_SHORT).show()
            return
        }
        if(Helper.isUserLoggedIn(requireContext())) {
            viewmodel.isLoading.value=1
            lifecycleScope.launch {
                viewmodel.products.collectLatest { pagedData ->
                    adapter?.submitData(pagedData)
                }
            }
        }
        else{
            binding?.listProducts?.visibility=GONE
        }
    }

    private fun initRecyclerView() {
        adapter = ProductListAdapter(requireContext(), ArrayList(), clickCallback)
        _binding.listProducts.adapter = adapter
        _binding.listProducts.setHasFixedSize(true)

    }

    private val clickCallback: ProductClickCallback = ProductClickCallback { }



}