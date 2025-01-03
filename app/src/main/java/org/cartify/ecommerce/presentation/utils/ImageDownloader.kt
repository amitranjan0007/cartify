package org.cartify.ecommerce.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ImageDownloader(context:Context) {
    private val memoryCache:LruCache<String,Bitmap> = LruCache(10*1024*1024)//10mb
    private val diskCacheDir = File(context.cacheDir,"image_cache")

    init {
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdirs()
        }
    }

    fun downloadImage(url:String):Bitmap?{

        //if exist is memory
        memoryCache[url]?.let {
            return  it
        }

        //check exist in file
        val file=File(diskCacheDir,url.hashCode().toString())
        if(file.exists()){
            val bitmap=BitmapFactory.decodeFile(file.absolutePath)
            bitmap?.let {
                return  it
            }
        }

        //download the image from server
        val bitmap = downloadImageFromServer(url)

        bitmap?.let {
            memoryCache.put(url,bitmap)
            saveToFile(url,bitmap)
            return it
        }
        return null
    }

    private fun saveToFile(urlString: String,bitmap: Bitmap) {
         val file=File(diskCacheDir,urlString.hashCode().toString())
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG,1,outputStream)
            outputStream.close()
        }catch (e:Exception){
             e.printStackTrace()
        }
    }

    private fun downloadImageFromServer(urlString:String):Bitmap? {
        var connection:HttpURLConnection?=null
        var bitmap:Bitmap?=null
        val url=URL(urlString)
        try {
            connection= url.openConnection() as HttpURLConnection
            connection.apply {
                requestMethod="GET"
                readTimeout=5000
                connectTimeout=5000
            }
            val inputStream=connection.inputStream

            /************To Read the image download in percentage . For that we have to read chunk by chunk ***/

                val totalSize=connection.contentLength
                val buffer= ByteArray(1024)

                val byteArrayOutputStream = ByteArrayOutputStream()
                var bytesRead=0
                var totalRead=0
                //from inputstream putting the data into buffer
                while (inputStream.read(buffer).also { bytesRead=it }!=-1){

                    //from buffer(holding data) and bytesRead(how much need to be write) into the outputstream
                    byteArrayOutputStream.write(buffer,0,bytesRead)
                    totalRead+=bytesRead
                    val progress=(totalRead.toDouble()/totalSize.toDouble())*100
                    println("total $progress %")
                }
                val outputByteArray=byteArrayOutputStream.toByteArray()
                bitmap=BitmapFactory.decodeByteArray(outputByteArray,0,outputByteArray.size)

            /****************************************************************************************/

            //Read directly from the input stream not by chunk by chunk
           // bitmap=BitmapFactory.decodeStream(inputStream)
            return bitmap

        }catch (e:IOException){
            e.printStackTrace()
        }finally {
            connection?.disconnect()

        }
        return  null
    }

}