/**
 * Copyright (c) 2013 Project Goth
 *
 * FileUtils.kt
 * Converted from FileUtils.java - Phase 3 Migration
 */

package com.projectgoth.util

import android.content.Context
import com.projectgoth.common.Logger
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * @author warrenbalcos
 *
 * Converted to Kotlin - Phase 3 Migration
 */
object FileUtils {

    const val TAG = "FileUtils"

    const val DEFAULT_FILE_BUFFER = 1024

    @JvmStatic
    fun doesFileExist(context: Context, filename: String): Boolean {
        return try {
            context.openFileInput(filename).use { true }
        } catch (e: FileNotFoundException) {
            false
        }
    }

    /**
     * Loads specific asset file data
     */
    @JvmStatic
    fun loadAssetFile(context: Context, filename: String): InputStream? {
        return try {
            context.assets.open(filename)
        } catch (e: Exception) {
            Logger.error.log(TAG, "Failed loading asset file: $filename", e)
            null
        }
    }

    @JvmStatic
    fun loadFile(context: Context, filename: String): InputStream? {
        return try {
            context.openFileInput(filename)
        } catch (e: Exception) {
            Logger.error.log(TAG, "Failed loading file: $filename", e)
            null
        }
    }

    /**
     * Copy source file to destination
     *
     * @param context The application context
     * @param src The source InputStream to copy from
     * @param dst The destination filename to write to
     * @return true if the file was saved successfully, false otherwise
     */
    @JvmStatic
    fun saveToFile(context: Context, src: InputStream, dst: String): Boolean {
        return try {
            context.openFileOutput(dst, Context.MODE_PRIVATE).use { fos ->
                src.use { input ->
                    val bytes = ByteArray(DEFAULT_FILE_BUFFER)
                    var read: Int
                    while (input.read(bytes).also { read = it } != -1) {
                        fos.write(bytes, 0, read)
                    }
                }
            }
            true
        } catch (e: Exception) {
            Logger.error.log(TAG, "Failed saving to file: $dst", e)
            false
        }
    }
}
