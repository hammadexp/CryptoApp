package com.maventech.cryptoapp


/*
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: FakeCurrencyRepository

    @Mock
    private lateinit var apiUsersObserver: Observer<>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<ApiClient>())
                .`when`(apiHelper)
                .getCurrencies()
            val viewModel = CurrencyViewModel(apiHelper)
            viewModel.getCurrencies()
                viewModel.currencyListResponse.observeForever(apiUsersObserver)
            verify(apiUsersObserver).onChanged()
            viewModel.currencyListResponse.removeObserver(apiUsersObserver)
        }
    }



    @After
    fun tearDown() {
        // do something if required
    }

}*/
