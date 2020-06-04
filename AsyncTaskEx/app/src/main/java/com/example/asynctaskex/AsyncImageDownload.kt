package com.example.asynctaskex

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.widget.*
import java.io.InputStream
import java.net.URL

class AsyncImageDownload(
    val context: Context,
    val rootLayout: LinearLayout,
    val progressBar: ProgressBar,
    val tvMessage: TextView
) : AsyncTask<String, Int, ArrayList<Bitmap>>() {

    override fun onPreExecute() {
        progressBar.progress = 0
        tvMessage.text = "Download started..."
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String): ArrayList<Bitmap> {
        val list = ArrayList<Bitmap>()

        // Loop through the task and execute them
        for( i in params.indices){
            val urlOfImage = URL(params[i])
            try {
                val inputStream = urlOfImage.openStream()
                list.add(BitmapFactory.decodeStream(inputStream)!!)  // or
//                list.add(BitmapFactory.decodeStream(urlOfImage.getContent() as InputStream)!!)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
            }

            // Publish the async task progress in percentage
            publishProgress(((i + 1) / params.size.toFloat() * 100).toInt())

            // If user cancel the task at runtime
            if(isCancelled)break
        }
        return list
    }

    // Display the async tas progress
    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.progress = values[0]!!
        tvMessage.text = "Download completed... ${values[0]}%"
        Toast.makeText(context,"Downloaded ${values[0]} %",Toast.LENGTH_LONG).show()
        super.onProgressUpdate(values[0])
    }


    // Manage completed task after user cancel async task
    override fun onCancelled(result: ArrayList<Bitmap>?) {
        tvMessage.text = "Task cancelled...\n ${result!!.size} files download success"

        for (bitmap in result){
            rootLayout.addView(newImageView(bitmap))
        }

        super.onCancelled(result)
    }


    // Manage result after completed async task
    override fun onPostExecute(result: ArrayList<Bitmap>?) {
        tvMessage.text = "Task finish...\n ${result?.size} files download success"
        for (bitmap in result!!){
            rootLayout.addView(newImageView(bitmap))
        }
        super.onPostExecute(result)
    }

    // Method to create a new ImageView instance
    private fun newImageView(bitmap: Bitmap): ImageView {
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300)
        params.bottomMargin = 15
        val imageView = ImageView(context)
        imageView.layoutParams = params
        imageView.setImageBitmap(bitmap)
        imageView.setBackgroundColor(Color.LTGRAY)
        imageView.setPadding(10,10,10,10)
        return imageView
    }

}
