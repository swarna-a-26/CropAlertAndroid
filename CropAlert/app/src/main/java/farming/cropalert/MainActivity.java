package farming.cropalert;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.Login;

import farming.cropalert.rest.request.GsonRequest;


public class MainActivity extends ActionBarActivity {

    private EditText userName;
    private EditText password;
    private Button login;
    public static final String PARAM_STRING="param";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    @Override
    protected void onStart () {
        super.onStart();
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("swarna", "nithilan");
                //login(userName.getText().toString(), password.getText().toString());
            }
        });


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

    @Override
    protected void onStop () {

        super.onStop();

    }

    private void login(final String userName, final String password) {
        android.util.Log.d("Swarna:", "user="+ userName + " " + "password=" + password );
        Response.Listener<Login> responseListener = new Response.Listener<Login>() {

            @Override
            public void onResponse(Login loginResponse) {
                if (loginResponse!=null) {
                    Log.d("Swarna","Succesfully logged in" + userName);
                    Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,R.string.login_failed,Toast.LENGTH_LONG).show();


            }
        };
        GsonRequest<Login> request = new GsonRequest<Login>("http://alertcrop.mybluemix.net/login?name=" + userName + "&password="+ password,
                new TypeToken<Login>(){}.getType(),
                null,responseListener,errorListener);
        CropAlertApplication.getInstance(this).addToRequestQueue(request);


    }
}
