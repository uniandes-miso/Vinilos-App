package com.miso.vinilosapp.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.miso.vinilosapp.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getAlbums() = suspendCoroutine<List<Album>> {cont->
        Log.d("GET DATA ALBUMS" , "FROM NETWORK")
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                val listTracks = mutableListOf<Track>()
                var item:JSONObject? = null
                var tracks:JSONArray? = null
                var itemtrack:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    tracks = resp.getJSONObject(i).getJSONArray("tracks")
                    for (j in 0 until tracks.length()) {
                        itemtrack = tracks.getJSONObject(j)
                        listTracks.add(j, Track(id = itemtrack.getInt("id"),name = itemtrack.getString("name"), duration = itemtrack.getString("duration")))
                    }
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description"), tracks = listTracks))
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
                    val listAlbums = mutableListOf<Album>()
                    var item:JSONObject? = null
                    var albums:JSONArray? = null
                    var itemalbum:JSONObject? = null
                    for (i in 0 until resp.length()) {
                        item = resp.getJSONObject(i)
                        albums = resp.getJSONObject(i).getJSONArray("albums")
                        for (j in 0 until albums.length()) {
                            itemalbum = albums.getJSONObject(j)
                            listAlbums.add(
                                j,
                                Album(
                                    albumId = itemalbum.getInt("id"),
                                    name = itemalbum.getString("name"),
                                    cover = itemalbum.getString("cover"),
                                    recordLabel = itemalbum.getString("recordLabel"),
                                    releaseDate = itemalbum.getString("releaseDate"),
                                    genre = itemalbum.getString("genre"),
                                    description = itemalbum.getString("description"),
                                    tracks = emptyList()
                                )
                            )
                            list.add(
                                i,
                                Musician(
                                    Id = item.getInt("id"),
                                    name = item.getString("name"),
                                    image = item.getString("image"),
                                    description = item.getString("description"),
                                    birthDate = item.getString("birthDate"),
                                    albums = listAlbums
                                )
                            )
                        }
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
                var albumsC:JSONArray? = null
                var itemalbum:JSONObject? = null
                var itemComment:JSONObject? = null
                var collectorComments:JSONArray? = null
                val collecAlbums = mutableListOf<Album>()
                val collecComments = mutableListOf<Comment>()
               // val collectMusician = mutableListOf<Musician>()
               // val listcollectMusicianAlbums = mutableListOf<Album>()
               // var itemmusician:JSONObject? = null
               // var itemCollecMusicianalbum:JSONObject? = null
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    collectorComments = resp.getJSONObject(i).getJSONArray("comments")
                    albumsC = resp.getJSONObject(i).getJSONArray("collectorAlbums")
                    for (c in 0 until collectorComments.length()) {
                        itemComment = collectorComments.getJSONObject(c)
                        collecComments.add(
                            c,
                            Comment(
                                id = itemComment.getInt("id"),
                                description = itemComment.getString("description"),
                                rating = itemComment.getString("rating")
                            )
                        )
                    }
                 //   musiciansC = resp.getJSONObject(i).getJSONArray("favoritePerformers")
                 /*   for (j in 0 until albumsC.length()) {
                        itemalbum = albumsC.getJSONObject(j)
                        collecAlbums.add(
                            j,
                            Album(
                                albumId = itemalbum.getInt("id"),
                                name = itemalbum.getString("name"),
                                cover = itemalbum.getString("cover"),
                                recordLabel = itemalbum.getString("recordLabel"),
                                releaseDate = itemalbum.getString("releaseDate"),
                                genre = itemalbum.getString("genre"),
                                description = itemalbum.getString("description"),
                                tracks = emptyList()
                            )
                        )
                    }*/
                  /*  for (m in 0 until musiciansC.length()) {
                        itemmusician = musiciansC.getJSONObject(m)
                        listcollectMusicianAlbums = resp.getJSONObject(m).getJSONArray("albums")
                        for (j in 0 until listcollectMusicianAlbums.length()) {
                            itemCollecMusicianalbum = listcollectMusicianAlbums.getJSONObject(j)
                            listcollectMusicianAlbums.add(
                                j,
                                Album(
                                    albumId = itemalbum.getInt("id"),
                                    name = itemalbum.getString("name"),
                                    cover = itemalbum.getString("cover"),
                                    recordLabel = itemalbum.getString("recordLabel"),
                                    releaseDate = itemalbum.getString("releaseDate"),
                                    genre = itemalbum.getString("genre"),
                                    description = itemalbum.getString("description"),
                                    tracks = emptyList()
                                )
                            )
                        }
                        collecAlbums.add(
                            m,
                            Musician(
                                Id = itemmusician.getInt("id"),
                                name = itemmusician.getString("name"),
                                image = itemmusician.getString("image"),
                                description = itemmusician.getString("description"),
                                birthDate = itemmusician.getString("birthDate")
                            )
                        )
                    }*/

                    list.add(i,
                        Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email"),
                            comments = collecComments
                    //        collectorAlbums = collecAlbums
                        )
                    )
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