package farming.cropalert.rest.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by swarna on 4/11/15.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    //private final Class<T> clazz;
    private final Type type;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
   public GsonRequest(String url, Type type, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.type = type;
        this.headers = headers;
        this.listener = listener;

    }

    public GsonRequest(String url, Type type, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener,boolean isPost) {
        super(Method.POST, url, errorListener);
        this.type = type;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            android.util.Log.d("Swarna: Json=" , json);
            return (Response<T>) Response.success(
                    gson.fromJson(json, type),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            android.util.Log.d("Swarna:UnsupportedEncodingException" ,e.toString());
            return Response.error(new ParseError(e));

        } catch (JsonSyntaxException e) {
            android.util.Log.d("Swarna:JsonSysntaxException" ,e.toString());
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}
