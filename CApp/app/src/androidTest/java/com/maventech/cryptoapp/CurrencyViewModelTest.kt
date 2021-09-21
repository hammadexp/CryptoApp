package com.maventech.cryptoapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CurrencyViewModelTest{
    private lateinit var viewModel:CurrencyViewModel

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule= MainCoroutineRule()

    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Application>()

        viewModel= CurrencyViewModel(FakeCurrencyRepository(),context)

    }

    @Test
    fun testGetCurrencyRatesList(){
        runBlocking {
        viewModel.getCurrencyRate()
        }
        val value=viewModel.list.getOrAwaitValueTest()
        assertThat(value.rates).isNotNull()
    }

    @Test
    fun testGetCurrencyList(){
        runBlocking {
            viewModel.getCurrencies()
        }
        val value=viewModel.currencyListResponse.getOrAwaitValueTest()
        assertThat(value.crypto).isNotNull()
    }




}