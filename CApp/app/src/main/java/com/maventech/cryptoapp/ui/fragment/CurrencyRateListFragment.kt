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
import com.matecho.wms.utils.Helper
import com.matecho.wms.utils.InternetDetector
import com.matecho.wms.utils.SharedPreference
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.databinding.FragmentCurrencyRateListBinding
import com.maventech.cryptoapp.model.currencyList.CurrencyItem
import com.maventech.cryptoapp.ui.adapters.CurrencyListAdapter
import com.maventech.cryptoapp.ui.callbacks.ProductClickCallback
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyRateListFragment : DaggerFragment() {
    private lateinit var adapter: CurrencyListAdapter
    private var binding: FragmentCurrencyRateListBinding? = null

    private val _binding: FragmentCurrencyRateListBinding get() = this.binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var viewmodel: CurrencyViewModel

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
            R.layout.fragment_currency_rate_list,
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
        viewmodel = ViewModelProvider(this, viewModelFactory)[CurrencyViewModel::class.java]
        initRecyclerView()
        initCurrencyList()
        viewmodel.isLoading.observe(getLifeCycleOwner(), {
            if (it == 1) {
                binding?.loader?.containerLoader?.visibility = VISIBLE
            } else {
                binding?.loader?.containerLoader?.visibility = GONE
            }
        })
    }

    private fun initCurrencyList() {
        if (!InternetDetector.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireActivity(), "Internet not available", Toast.LENGTH_SHORT).show()
            return
        }

            viewmodel.isLoading.value = 1
            lifecycleScope.launch {
                viewmodel.getCurrencyRate()

                viewmodel.list.observe(getLifeCycleOwner(), Observer {
                    val rates = it.rates
//                        val data = it.rates?.hashMap
                        val keys = ArrayList(rates.keys)
                        val values = ArrayList(rates?.values)
                        val currencyList = ArrayList<CurrencyItem>()
                        for (i in 0 until  keys.size) {
                            currencyList.add(CurrencyItem(keys[i], values[i].toString()))
                        }
                        adapter?.submitList(currencyList)
//                    adapter.notifyDataSetChanged()
                    viewmodel.isLoading.postValue(0)
                })
            }

    }

    private fun initRecyclerView() {
        adapter = CurrencyListAdapter(requireContext(), ArrayList(), clickCallback)
        _binding.listProducts.adapter = adapter
        _binding.listProducts.setHasFixedSize(true)

    }

    private val clickCallback: ProductClickCallback = ProductClickCallback { }


}