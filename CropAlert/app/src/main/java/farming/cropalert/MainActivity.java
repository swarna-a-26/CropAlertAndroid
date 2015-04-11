package farming.cropalert;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;


import android.util.Log;

import java.util.List;

import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.User;
import farming.cropalert.rest.request.GsonRequest;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart () {
        super.onStart();
        //GsonRequest(String url, Type type, Map<String, String> headers,
        //com.android.volley.Response.Listener<T> listener, Response.ErrorListener errorListener)
      Response.Listener<List<User>> responseListener = new Response.Listener<List<User>>() {

          @Override
          public void onResponse(List<User> users) {
              if (users!=null) {
                 Log.d("Swarna", "users size=" + users.size());
              }

          }
      };
     Response.ErrorListener errorListener = new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError volleyError) {

         }
     };
      GsonRequest<List<User>> request = new GsonRequest<List<User>>("http://alertcrop.mybluemix.net/user?name=junying&password=password123",
              new TypeToken<List<User>>(){}.getType(),
              null,responseListener,errorListener);
       CropAlertApplication.getInstance(this).addToRequestQueue(request);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
