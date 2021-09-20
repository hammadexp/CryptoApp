package com.maventech.cryptoapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/*
@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {
 
    private lateinit var viewModel: CurrencyViewModel
 
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
 
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        spendsDatabase = Room.inMemoryDatabaseBuilder(
            context, SpendsDatabase::class.java
        ).allowMainThreadQueries().build()
        val dataSource = SpendsTrackerDataSource(spendsDatabase.getSpendDao())
        viewModel = CurrencyViewModel(context)
    }
 
    @After
    @Throws(IOException::class)
    fun closeDb() {
        spendsDatabase.close()
    }
 
    @Test
    fun testAddingSpend() {
        viewModel.addSpend(100, "Eggs")
        viewModel.getLast20Spends()
        val result = viewModel.getCurrencies().getOrAwaitValue().find {
            it.amount == 100 && it.description == "Eggs"
        }
        assertThat(result != null).isTrue()
    }
}*/
