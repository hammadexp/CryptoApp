package com.matecho.wms.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.maventech.cryptoapp.databinding.FragmentCurrencyConvertBinding
import com.maventech.cryptoapp.model.currencyList.CurrencyListResponse
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyConvertFragment : DaggerFragment() {

    private var binding: FragmentCurrencyConvertBinding? = null

    private val _binding: FragmentCurrencyConvertBinding get() = this.binding!!

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_currency_convert, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this, viewModelFactory)[CurrencyViewModel::class.java]

        initDashboard()
        viewmodel.isLoading.observe(getLifeCycleOwner(), {
            if (it == 1) {
                _binding.loader.containerLoader.visibility = VISIBLE
            } else {
                _binding.loader.containerLoader.visibility = GONE
            }
        })

        _binding.btnConvert.setOnClickListener {
//            if(binding.spinnerFrom.text)
            viewLifecycleOwner.lifecycleScope.launch {
             /*   viewmodel.convert(
                    _binding.spinnerFrom.text.toString(),
                    _binding.spinnerTo.text.toString(),
                    _binding.etAmount.text.toString()
                )
                viewmodel.convertResponse.observe(getLifeCycleOwner(), Observer {
                    if(it.success)
                    _binding.tvResult.text=it.result.toString()
                })*/
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.getCurrencies()
            viewmodel.currencyListResponse.observe(viewLifecycleOwner, Observer {
                if (it.success) {

                    initCurrencySpinner(it)
                }
            })
        }

    }

    private fun initCurrencySpinner(it: CurrencyListResponse) {
        val names = it.crypto.map { it.value.symbol }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, names
        )
        binding?.spinnerFrom?.setAdapter(adapter)
        binding?.spinnerTo?.setAdapter(adapter)
        binding?.spinnerFrom?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, pos, _ ->
                binding?.spinnerFromLayout?.error = null

            }
    }


    private fun initDashboard() {
        if (!InternetDetector.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireActivity(), "Internet not available", Toast.LENGTH_SHORT).show()
            return
        }

    }


}