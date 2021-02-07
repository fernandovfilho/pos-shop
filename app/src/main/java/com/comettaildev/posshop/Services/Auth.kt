package com.comettaildev.posshop.Services

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Auth {

    fun signIn(
        context: Context,
        email: String,
        password: String,
        callback: (Boolean, JSONObject?) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://www.google.com"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,
            null,
            Response.Listener<JSONObject> { response ->
                callback(true, response)
            },
            Response.ErrorListener { callback(false, null) })

        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            3000,
            0,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        );

        queue.add(jsonObjectRequest)
    }

}