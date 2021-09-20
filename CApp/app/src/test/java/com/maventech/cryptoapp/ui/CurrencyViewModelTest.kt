package com.maventech.reminder.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.maventech.cryptoapp.MainCoroutineRule
import com.maventech.cryptoapp.getOrAwaitValueTest
import com.maventech.cryptoapp.repository.FakeCurrencyRepository
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
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule= MainCoroutineRule()

    @Before
    fun setup(){
        viewModel= CurrencyViewModel(FakeCurrencyRepository())

    }

    @Test
    fun `get currency rates list`(){
//        viewModelScope.
        runBlocking {
        viewModel.getCurrencyRate()
        }
        val value=viewModel.list.getOrAwaitValueTest()
        assertThat(value.rates).isNotEmpty()
    }




}