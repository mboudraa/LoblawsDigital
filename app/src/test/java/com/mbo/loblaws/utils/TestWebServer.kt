package com.mbo.loblaws.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.ExternalResource
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


@ExperimentalTime
class TestWebServer : ExternalResource() {

    private var dispatcher: (RecordedRequest) -> MockResponse = DEFAULT_DISPATCHER

    private val mockWebServer = MockWebServer()

    init {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = dispatcher(request)
        }
    }

    fun setDispatcher(dispatcher: (RecordedRequest) -> MockResponse) {
        this.dispatcher = dispatcher
    }

    fun setDispatcher(vararg dispatcher: Pair<String, MockResponse>) {
        this.dispatcher = { request ->
            dispatcher.find { (path, _) -> request.path == path }
                ?.let { (_, response) -> response }
                ?: DEFAULT_DISPATCHER(request)
        }
    }

    fun request(timeout: Timeout = Timeout.Forever): RecordedRequest? {
        return when (timeout) {
            is Timeout.Forever -> mockWebServer.takeRequest()
            is Timeout.Limited -> mockWebServer.takeRequest(
                timeout.duration.toLongMilliseconds(),
                TimeUnit.MILLISECONDS
            )
        }
    }

    fun baseUrl(): String {
        return mockWebServer.url("/").toString()
    }

    override fun before() {
        try {
            mockWebServer.start()
        } catch (e: Exception) {
            // Do Nothing
        }
    }

    override fun after() {
        try {
            mockWebServer.shutdown()
        } catch (e: Exception) {
            // Do Nothing
        } finally {
            dispatcher = DEFAULT_DISPATCHER
        }
    }

    sealed class Timeout {
        object Forever : Timeout()
        data class Limited(val duration: Duration) : Timeout()
    }

    companion object {
        val DEFAULT_DISPATCHER: (RecordedRequest) -> MockResponse =
            { MockResponse().setResponseCode(404) }
    }
}
