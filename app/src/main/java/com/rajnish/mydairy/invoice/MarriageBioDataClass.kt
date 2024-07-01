package com.rajnish.mydairy.invoice

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Environment
import android.widget.Toast
import com.rajnish.mydairy.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MarriageBioDataClass {

    fun templet1(context: Context) {
        val myPdfDocument = PdfDocument()
        val pageInfo = PageInfo.Builder(595, 842, 1).create()
        val page: PdfDocument.Page = myPdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val image = BitmapFactory.decodeResource(context.resources, R.drawable.marriagebiodatablankpage)
        val imageRect = Rect(0, 0, canvas.width, canvas.height)
        canvas.drawBitmap(image, null, imageRect, null)

        val textPaint = Paint()
        textPaint.color = Color.rgb(0, 0, 0)
        textPaint.textSize = 20.5f


        val marriageArrayList = arrayListOf(
            "Name",
            "Father Name",
            "Mother Name",
            "Date Of Birth",
            "Qualification",
            "Village",
            "Post",
            "District",
            "State",
            "Village",
            "Post",
            "District",
            "State"
        )
        val x = 70F
        var yPoint = 150F


        for (i in marriageArrayList){
            canvas.drawText(i,x,yPoint,textPaint)
            yPoint += 30
        }


        myPdfDocument.finishPage(page)

        val file: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "e_sharam_card.pdf")

        try {
            myPdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "Ready to Print", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
