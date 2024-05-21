package com.example.devskillerjosue

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.annotation.UiThreadTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.devskillerjosue.api.ApiProvider
import com.example.devskillerjosue.models.MCharacter
import com.example.devskillerjosue.viewmodel.CharacterViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection

class SimpleUnitTest {

    // in my current job we test the viewModels using data from a json document

    lateinit var mockWebServer: MockWebServer

    //used for mutablelivedata set value
    @get:Rule
    val rule = InstantTaskExecutorRule()

    //provider and viewModel
    lateinit var viewModel: CharacterViewModel

    init {
        val synchronousScheduler = Schedulers.from { obj: Runnable -> obj.run() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { synchronousScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler{ synchronousScheduler }
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        ApiProvider.url = mockWebServer.url("/")
        viewModel = CharacterViewModel()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    @UiThreadTest
    fun getDataTest() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(FileUtils.readTestResourceFile("getData.json", InstrumentationRegistry.getInstrumentation().context)))

        viewModel.getData()

        Assert.assertEquals(viewModel.currentData.value!![0].name, "pepe")

        //sorry didn't work but this is the way we test the view-models, i think was for the InstantTaskExecutorRule wo fails

    }


}