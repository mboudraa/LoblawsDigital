package com.mbo.loblaws.utils

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.rules.ExternalResource

class MockkRule(private val recipient: Any) : ExternalResource() {

    override fun before() {
        MockKAnnotations.init(recipient, relaxUnitFun = true)
    }

    override fun after() {
        unmockkAll()
    }
}
