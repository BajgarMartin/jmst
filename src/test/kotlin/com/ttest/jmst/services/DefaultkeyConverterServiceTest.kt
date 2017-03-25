package com.ttest.jmst.services

import org.junit.Test
import java.util.*

/**
 * Created by bajga on 20.03.2017.
 */
class DefaultkeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun givenIdMustBeConvertableBothWays() {
        val rand = Random()
        for (i in 0..1000L) {
            val initialId = Math.abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            org.junit.Assert.assertEquals(initialId, id)
        }
    }
}