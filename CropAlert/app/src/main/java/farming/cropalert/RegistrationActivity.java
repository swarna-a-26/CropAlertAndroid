package farming.cropalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.Login;
import farming.cropalert.data.dto.UpdateUserResponse;
import farming.cropalert.rest.request.GsonRequest;
import farming.cropalert.util.Utils;


/**
 * Created by swarna on 4/12/15.
 */
public class RegistrationActivity extends ActionBarActivity {

    private EditText userName;
    private EditText registerpassword;
    private EditText mobileNumber;
    private View registerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userName = (EditText) findViewById(R.id.userName);
        registerpassword = (EditText) findViewById(R.id.registerpassword);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        registerButton = findViewById(R.id.registerButton);
    }

    @Override
    protected void onStart () {
        super.onStart();
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                register();
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

        private void register() {

            String userNameData = Utils.urlEncode(userName.getText().toString());
            String passwordData= Utils.urlEncode(registerpassword.getText().toString());
            String mobileNumberData= Utils.urlEncode(mobileNumber.getText().toString());
            android.util.Log.d("Swarna:", "user="+ userNameData + " " + "password=" + passwordData + "mobileNumber=" + mobileNumberData);
            Response.Listener<UpdateUserResponse> responseListener = new Response.Listener<UpdateUserResponse>() {

                @Override
                public void onResponse(UpdateUserResponse responsee) {
                    if (responsee!=null) {
                        Toast.makeText(RegistrationActivity.this, R.string.registration_success, Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(RegistrationActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();


                }
            };
            //alertcrop.mybluemix.net/user?name=malarkodi3&password=pass1233456&mobileno=1234567890
            String url = "http://alertcrop.mybluemix.net/user?name=" + userNameData + "&password="+ passwordData + "&mobileno="+mobileNumberData;
            android.util.Log.d("Swarna:" ,"registerurl=" + url );
            GsonRequest<UpdateUserResponse> request = new GsonRequest<UpdateUserResponse>(url,
                    new TypeToken<UpdateUserResponse>(){}.getType(),
                    null,responseListener,errorListener,true);
            CropAlertApplication.getInstance(this).addToRequestQueue(request);


        }



}
