package com.ravish.hilt.coroutines.retrofit.example.viewmodel

import android.os.Looper
import com.ravish.hilt.coroutines.retrofit.example.api.FakeApiService
import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiRepository
import com.ravish.hilt.coroutines.retrofit.example.presentation.model.UIState
import com.ravish.hilt.coroutines.retrofit.example.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiRepository: ApiRepository

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        homeViewModel = HomeViewModel(apiRepository)
    }

    @Test
    fun `test User data success`() = runBlockingTest {
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Success)
        assertNotNull(value.data)
        assertEquals(7834983, value.data?.id)
        assertEquals("Ravish Rajput", value.data?.name)
    }

    @Test
    fun `test User data api failure`() = runBlockingTest {
        fakeApiService.failUserApi = true
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Error)
        assertNull(value.data)
    }

    @Test
    fun `test User wrong data`() = runBlockingTest {
        fakeApiService.wrongResponse = true
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Success)
        assertNotNull(value.data)
        assertEquals("", value.data?.name)
    }

}