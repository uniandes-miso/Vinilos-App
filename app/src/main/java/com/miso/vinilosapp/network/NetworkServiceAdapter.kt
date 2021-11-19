package com.miso.vinilosapp.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Collector
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.ui.musicians
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter  constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://thedcompany.herokuapp.com/"
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
    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error: VolleyError)->Unit){
        Log.d("REFRESH DATA ALBUMS" , "NETWORK SERVICE ADAPTER")
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                Log.d("REFRESH DATA ALBUMS" , list.size.toString())
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun getMusicians(onComplete: (resp: List<Musician>) -> Unit, onError: (error: VolleyError) -> Unit) {
        Log.d("REFRESH DATA MUSICIANS" , "NETWORK SERVICE ADAPTER")
        requestQueue.add(
            getRequest("musicians",
                { response ->
                   /*val mapper = jacksonObjectMapper()
                   var musicians: List<Musician> = mapper.readValue(response)
                    Log.d("REFRESH DATA ALBUMS" , musicians.size.toString())
                   onComplete(musicians)*/
                    val resp = JSONArray(response)
                    val list = mutableListOf<Musician>()
                    for (i in 0 until resp.length()) {
                       val item = resp.getJSONObject(i)
                        list.add(i, Musician(Id = item.getInt("id") , name = item.getString("name") , image = item.getString("image")  , description = item.getString("description") , birthDate = item.getString("birthDate")))
                    }
                    Log.d("REFRESH DATA Musicias" , list.size.toString())
                    onComplete(list)
                },
                {
                    onError(it)
                })
        )
    }

    fun getCollectors(onComplete: (resp: List<Collector>) -> Unit, onError: (error: VolleyError) -> Unit) {
        Log.d("REFRESH DATA Collectors" , "NETWORK SERVICE ADAPTER")
        requestQueue.add(
            getRequest("collectors",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(i, Collector(Id = item.getInt("id") , name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                    }
                    Log.d("REFRESH DATA Collectors" , list.size.toString())
                    onComplete(list)
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
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}