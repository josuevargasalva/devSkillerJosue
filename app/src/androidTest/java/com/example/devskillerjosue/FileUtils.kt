package com.example.devskillerjosue

import android.content.Context
import java.io.IOException
import java.io.InputStream

object FileUtils {

    fun readTestResourceFile(fileName: String,context: Context): String {
        var json:String = ""
        val text : InputStream = context.getAssets().open(fileName)
        val size: Int = text.available()
        val buffer = ByteArray(size)
        text.read(buffer)
        text.close()
        json = String(buffer)
        return json
    }

}