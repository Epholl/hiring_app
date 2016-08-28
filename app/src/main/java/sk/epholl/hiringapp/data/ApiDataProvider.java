package sk.epholl.hiringapp.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class ApiDataProvider {

    public interface ResponseListener<T> {
        void onSuccess(T response);
        void onError(String message);
    }

    private static ApiDataProvider sInstance;

    public static void init(@NonNull final Context context) {
        sInstance = new ApiDataProvider(context);
    }

    public static ApiDataProvider getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("init() was not called before getInstance()");
        }
        return sInstance;
    }

    private RequestQueue mRequestQueue;

    private ApiDataProvider(@NonNull final Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void requestString(
            @NonNull final String url,
            @NonNull final ResponseListener<String> responseListener) {
        mRequestQueue.add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responseListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onError(error.toString());
            }
        }));
    }

    public void requestJsonObject(
            @NonNull final String url,
            @NonNull final ResponseListener<JSONObject> responseListener) {
        mRequestQueue.add(new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onError(error.toString());
            }
        }));
    }

    public <T> void requestObject(
            @NonNull final String url,
            @NonNull final Class<T> objectClass,
            @NonNull final ResponseListener<T> responseListener) {
        mRequestQueue.add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    final T result = new Gson().fromJson(response, objectClass);
                    responseListener.onSuccess(result);
                } catch (JsonSyntaxException e) {
                    responseListener.onError("Gson parsing failed: " + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onError(error.toString());
            }
        }));
    }
}
