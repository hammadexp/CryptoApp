package com.maventech.cryptoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.matecho.wms.service.ApiClient
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<List<ApiUser>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<ApiClient>())
                .`when`(apiHelper)
                .getUsers()
            val viewModel = CurrencyViewModel(apiHelper)
            viewModel.getMessageByOrderID().observeForever(apiUsersObserver)
            verify(apiHelper).getUsers()
            verify(apiUsersObserver).onChanged(Resource.success(emptyList()))
            viewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getUsers()
            val viewModel = SingleNetworkCallViewModel(apiHelper)
            viewModel.getUsers().observeForever(apiUsersObserver)
            verify(apiHelper).getUsers()
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}