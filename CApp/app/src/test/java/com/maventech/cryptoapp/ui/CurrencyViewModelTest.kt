package com.maventech.reminder.ui


/*
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
    fun `insert expense item with empty field returns error`(){
        viewModel.getCurrencies()

        val value=viewModel.insertExpenseItemsStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert expense item with too long name returns error`(){
        val string= buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertExpenseItem(string,"5")

        val value=viewModel.insertExpenseItemsStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert expense item with too long price returns error`(){
        val string= buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)
            }
        }
        viewModel.insertExpenseItem("name",string)

        val value=viewModel.insertExpenseItemsStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert expense item with valid input returns success`(){

        viewModel.insertExpenseItem("name","20")

        val value=viewModel.insertExpenseItemsStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }


}*/
