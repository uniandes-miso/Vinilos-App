package com.miso.vinilosapp.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.model.Musician
import org.json.JSONObject


class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://thedcompany.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(onComplete: (resp: List<Album>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("albums",
                { response ->
                    val mapper = jacksonObjectMapper()
                    var albums: List<Album> = mapper.readValue(response)
                    onComplete(albums)
                },
                {
                    onError(it)
                })
        )
    }

    fun getMusicians(onComplete: (resp: List<Musician>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("musicians",
                { response ->
                    val mapper = jacksonObjectMapper()
                    var musicians: List<Musician> = mapper.readValue(response)
                    onComplete(musicians)
                },
                {
                    onError(it)
                })
        )
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.PUT, BASE_URL + path, body, responseListener, errorListener)
    }


}