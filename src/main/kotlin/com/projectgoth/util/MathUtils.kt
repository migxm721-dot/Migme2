/**
 * Copyright (c) 2013 Project Goth
 *
 * MathUtils.kt
 * Converted from MathUtils.java - Phase 2 Migration
 */

package com.projectgoth.util

import kotlin.math.*

/**
 * Object that contains Math related utility routines.
 * Converted to Kotlin - Phase 2 Migration
 *
 * @author angelorohit
 */
object MathUtils {

    private const val EPSILON = 0.0000001f

    /**
     * Calculates a power-of-two scale for the source dimensions to expand to
     * the destination dimensions.
     *
     * @param originalWidth
     *            The width of the source dimension to be scaled
     * @param originalHeight
     *            The height of the source dimension to be scaled
     * @param expectedWidth
     *            The width of the destination dimension to be scaled to.
     * @param expectedHeight
     *            The height of the destination dimension to be scaled to.
     * @return An integer that is the power-of-two scale.
     */
    @JvmStatic
    fun getPowerOfTwoScale(originalWidth: Int, originalHeight: Int, expectedWidth: Int, expectedHeight: Int): Int {
        val expectedSize = max(expectedWidth, expectedHeight).toDouble()
        val originalSize = min(originalWidth, originalHeight).toDouble()

        // this is to get power of 2
        var scale = 1
        if (expectedSize > 0 && originalSize > 0 && originalSize > expectedSize) {
            // do log2(originalSize/ expectedSize)
            val logSize = ln(originalSize / expectedSize)
            val exponent = logSize / ln(2.0)
            // rounded down to the nearest power of 2.
            val exponentInt = exponent.toInt()
            val pow = 2.0.pow(exponentInt)
            scale = pow.toInt()
        }

        return scale
    }

    /**
     * Compares two floating point values
     *
     * @param lhs
     *            One of the floating point values to be compared
     * @param rhs
     *            The other floating point value to be compared.
     * @return true if equal and false otherwise.
     */
    @JvmStatic
    fun compareFloats(lhs: Float, rhs: Float): Boolean {
        return (abs(lhs - rhs) < EPSILON)
    }

    /**
     * Ensures that a given value is within a max and min range.
     * If the value is less than the minimum, then it is made the min value.
     * If the value is greater than the maximum, then it is made the max value.
     * Otherwise, the original value is returned.
     * @param value The value to be limited within range.
     * @param min   The minimum value below which the given value cannot go.
     * @param max   The maximum value above which the given value cannot go.
     * @return      The range limited value.
     */
    @JvmStatic
    fun clamp(value: Int, min: Int, max: Int): Int {
        return max(min(value, max), min)
    }

    @JvmStatic
    fun clamp(value: Float, min: Float, max: Float): Float {
        return max(min(value, max), min)
    }

    /**
     * Linear interpolation for integers
     * @param a start value
     * @param b end value
     * @param t alpha value (or elapsed time)
     * @return linearly interpolated value between a and b (for t [0..1])
     */
    @JvmStatic
    fun lerp(a: Int, b: Int, t: Float): Int {
        return a + ((b - a) * t).toInt()
    }

    /**
     * Linear interpolation for floats
     * @param a start value
     * @param b end value
     * @param t alpha value (or elapsed time)
     * @return linearly interpolated value between a and b (for t [0..1])
     */
    @JvmStatic
    fun lerp(a: Float, b: Float, t: Float): Float {
        return a + (b - a) * t
    }
}
