package com.comettaildev.posshop.pagseguro

import android.app.Application
import android.content.Context
import android.os.Environment
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPag
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPagAppIdentification
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPagPrintResult
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPagPrinterData
import com.comettaildev.posshop.R

object PagseguroInstance : Application() {

    private var mPlugPag: PlugPag? = null

    fun start(context: Context) {
        if (mPlugPag == null) {
            mPlugPag = PlugPag(
                context, PlugPagAppIdentification(
                    context.resources.getString(
                        R.string.app_pagseguro_name
                    ), context.resources.getString(
                        R.string.app_pagseguro_version
                    )
                )
            )
        }

    }

    fun printFile(filePath: String, callback: (Boolean) -> Unit) {

        val printerData = PlugPagPrinterData(
            Environment.getExternalStorageDirectory()
                .absolutePath + filePath,
            4,
            10 * 12
        )

        try {
            val result: PlugPagPrintResult = mPlugPag?.printFromFile(printerData)!!
            return if (result.result == PlugPag.RET_OK) {
                callback(true)
            } else {
                callback(false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback(false)
        }


    }


}