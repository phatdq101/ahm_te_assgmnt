package dev.phatduong.ahm_tech_asignment.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class IntExtensionKtTest {
    @Test
    fun format1_ReturnsCorrectValue() {
        assertEquals("1", 1.formatToThousandText())
    }

    @Test
    fun format1000_ReturnsInCorrectValue() {
        assertNotEquals("1000", 1.formatToThousandText())
    }

    @Test
    fun format12345_ReturnsCorrectValue() {
        assertEquals("12.3k", 12345.formatToThousandText())
    }

    @Test
    fun formatNull_ReturnsZero() {
        val num: Int? = null
        assertEquals("0", num.formatToThousandText())
    }
}