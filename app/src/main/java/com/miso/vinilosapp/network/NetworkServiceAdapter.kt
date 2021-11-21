package com.miso.vinilosapp.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Collector
import com.miso.vinilosapp.models.Musician
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter  constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://back-vinyls-populated.herokuapp.com/"
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

    suspend fun getAlbums() = suspendCoroutine<List<Album>> {cont->
        Log.d("GET DATA ALBUMS" , "FROM NETWORK")
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                Log.d("REFRESH DATA ALBUMS" , list.size.toString())
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getMusicians() = suspendCoroutine<List<Musician>> { cont ->
        Log.d("GET DATA MUSICIANS" , "FROM NETWORK")
        requestQueue.add(
            getRequest("musicians",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Musician>()
                    var item:JSONObject? = null
                    for (i in 0 until resp.length()) {
                        item = resp.getJSONObject(i)
                        list.add(i, Musician(Id = item.getInt("id") , name = item.getString("name") , image = item.getString("image")  , description = item.getString("description") , birthDate = item.getString("birthDate")))
                    }
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                }))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(getRequest("collectors",
            { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}