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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.matecho.wms.utils.InternetDetector
import com.matecho.wms.utils.SharedPreference
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.FragmentProductDetailBinding
import com.maventech.cryptoapp.databinding.FragmentProductDetailBindingImpl
import com.maventech.cryptoapp.databinding.FragmentProductListBinding
import com.maventech.cryptoapp.ui.adapters.ProductListAdapter
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback
import com.maventech.cryptoapp.viewmodel.ProductViewModel
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductDetailFragment : DaggerFragment() {
    private lateinit var adapter: ProductListAdapter
    private var binding: FragmentProductDetailBinding? = null

    private val _binding: FragmentProductDetailBinding get() = this.binding!!

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_detail,
            container,
            false
        )
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        val bundle = arguments ?: return
        val args: ProductDetailFragmentArgs =
            ProductDetailFragmentArgs.fromBundle(bundle)
        val product = args.product
        _binding.tvTitle.text=product.title
        _binding.tvDescription.text=product.description
        _binding.tvPrice.text=product.price
        Glide.with(binding?.ivImage!!.context).load(product.image).into(binding?.ivImage!!)
    }



}