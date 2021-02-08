package com.comettaildev.posshop

import android.Manifest
import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import android.util.Log
import com.comettaildev.posshop.pagseguro.PagseguroInstance
import com.comettaildev.posshop.utils.Utils
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream

open class Startup : Application() {

    override fun onCreate() {
        super.onCreate()
        PagseguroInstance.start(applicationContext)
        requestPermissions()
    }

    private fun requestPermissions() {
        Dexter.withContext(applicationContext)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    // testPrint()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }

            })
            .check()
    }

    private fun testPrint() {

        val utils = Utils()

        val mainBitmap = Bitmap.createBitmap(400, 250, Bitmap.Config.RGB_565)
        val canvas = Canvas(mainBitmap)
        canvas.drawColor(Color.WHITE)
        val bitmap = utils.textAsBitmap("Teste", 24F)
        val bitmapMerge =
            utils.mergeToPin(
                mainBitmap,
                bitmap!!,
                20,
                Utils.Align.LEFT
            )

        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/images")
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        val fileName = "test.jpg"
        val file = File(myDir, fileName)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            bitmapMerge!!.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val runnable = Runnable {
            PagseguroInstance.printFile("/images/test.jpg") { printResult ->
                run {
                    Log.d("PRINTER", printResult.toString())
                }
            }
        }
        val thread = Thread(runnable)
        thread.start()
    }

}