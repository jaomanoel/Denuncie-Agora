package com.example.denuncieagora.utils

import android.content.Context
import android.os.AsyncTask
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException

class AdvertisingInfoTask(private val context: Context, private val callback: (String) -> Unit) :
    AsyncTask<Void, Void, String>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Void?): String {
        var advertisingId = ""

        try {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            advertisingId = adInfo.id.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        }

        return advertisingId
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String) {
        callback.invoke(result)
    }
}

fun getAdvertisingInfo(context: Context, callback: (String) -> Unit) {
    AdvertisingInfoTask(context, callback).execute()
}