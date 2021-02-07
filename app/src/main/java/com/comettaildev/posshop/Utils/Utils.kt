package com.comettaildev.posshop.Utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Utils {

    fun textAsBitmap(text: String, textSize: Float): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = Color.BLACK
        paint.textAlign = Paint.Align.LEFT
        val baseline = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.5f).toInt() // round
        val height = (baseline + paint.descent() + 0.5f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text, 0f, baseline, paint)
        return image
    }

    enum class Align {
        LEFT, RIGHT, CENTER
    }

    fun mergeToPin(back: Bitmap, front: Bitmap, topMargin: Int, align: Align): Bitmap? {
        val result = Bitmap.createBitmap(back.width, back.height, back.config)
        val canvas = Canvas(result)
        val widthBack = back.width
        val widthFront = front.width
        var move = 0F
        if (align === Align.CENTER) {
            move = (widthBack - widthFront) / 2.toFloat()
        } else if (align === Align.RIGHT) {
            move = (widthBack - widthFront).toFloat()
        } else if (align === Align.LEFT) {
            move = 0F
        }
        canvas.drawBitmap(back, 0f, 0f, null)
        canvas.drawBitmap(front, move, topMargin.toFloat(), null)
        return result
    }


}