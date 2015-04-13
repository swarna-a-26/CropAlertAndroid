package farming.cropalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;

import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.Login;
import farming.cropalert.data.dto.UpdateUserResponse;
import farming.cropalert.rest.request.GsonRequest;
import farming.cropalert.util.Utils;

/**
 * Created by swarna on 4/12/15.
 */
public class UpdateUserActivity extends ActionBarActivity {


    private EditText crop;
    private EditText disease;
    private EditText symptoms;
    private EditText location;
    private View update;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        initViews();

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                update();
            }
        });



    }

    @Override
    protected void onStart () {
        super.onStart();


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

    private void initViews() {

        crop = (EditText) findViewById(R.id.crop);
        disease = (EditText) findViewById(R.id.disease);
        symptoms = (EditText) findViewById(R.id.symptoms);
        location = (EditText) findViewById(R.id.location);
        update = findViewById(R.id.update);


    }
    private void update() {
        String cropName = crop.getText().toString();
        Utils.trim(cropName);
        String diseaseName = disease.getText().toString();
        Utils.trim(diseaseName);
        String symptomsName = symptoms.getText().toString();
        Utils.trim(symptomsName);
        String locationName = location.getText().toString();
        Utils.trim(locationName);

        Response.Listener<UpdateUserResponse> responseListener = new Response.Listener<UpdateUserResponse>() {

            @Override
            public void onResponse(UpdateUserResponse updateUserResponse) {
                if (updateUserResponse!=null && updateUserResponse.getSuccess()==200) {
                    Toast.makeText(UpdateUserActivity.this, R.string.updated_success, Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    if (updateUserResponse==null) {
                        android.util.Log.d("Swarna:", "updateUserResponse=null");
                    } else {
                        android.util.Log.d("Swarna:", "failed updateUserResponse=" +  updateUserResponse.getSuccess());

                    }

                    Toast.makeText(UpdateUserActivity.this, R.string.update_failed, Toast.LENGTH_LONG).show();

                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                android.util.Log.d("Swarna:", "failed VolleyError=" +  volleyError.toString());
                Toast.makeText(UpdateUserActivity.this, R.string.update_failed, Toast.LENGTH_LONG).show();


            }
        };
        /*String diseaseName = disease.getText().toString();
        Utils.trim(diseaseName);
        String symptomsName = symptoms.getText().toString();
        Utils.trim(symptomsName);
        String locationName = location.getText().toString();
        Utils.trim(locationName);*/
        //alertcrop.mybluemix.net/cropdisease?
        // //crop=choysum&disease=CabbageMagot&symptom=bacterial soft rots&location=delhi
        // &harvest=start&temp=120F&moist=high&soiltype=loam&lat=28.2&lon=77.2
        // &image=https://s3.amazonaws.com/cropimage/cabmagotlarva.jpg&humidity=40%
        // &startdate=1/1/2015&enddate=4/4/2015&rating=5&aa=3%&aae=13%&remedy=field sanitation
        cropName=Utils.urlEncode(cropName);
        diseaseName=Utils.urlEncode(diseaseName);
        symptomsName=Utils.urlEncode(symptomsName);
        locationName=Utils.urlEncode(locationName);

        String url ="http://alertcrop.mybluemix.net/" + "cropdisease?crop=" + cropName
                    + "&disease=" + diseaseName + "&symptom=" + symptomsName + "&location="+ locationName;
        android.util.Log.d("Swarna:", "updateUrl="+ url);

        GsonRequest<UpdateUserResponse> request = new GsonRequest<UpdateUserResponse>(url,
                new TypeToken<UpdateUserResponse>(){}.getType(),
                null,responseListener,errorListener,true);
        CropAlertApplication.getInstance(this).addToRequestQueue(request);





    }



}
