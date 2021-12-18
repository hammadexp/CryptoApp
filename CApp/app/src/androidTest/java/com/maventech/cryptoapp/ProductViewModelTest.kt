package com.maventech.cryptoapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.maventech.cryptoapp.viewmodel.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ProductViewModelTest{
    private lateinit var viewModel:ProductViewModel

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule= MainCoroutineRule()

    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Application>()

        viewModel= ProductViewModel(FakeProductRepository(),context)

    }

    @Test
    fun testGetProductList(){
        runBlocking {
        viewModel.getProductList()
        }
        val value=viewModel.list.getOrAwaitValueTest()
        assertThat(value).isNotNull()
    }




}