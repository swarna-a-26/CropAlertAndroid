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

import java.util.List;

import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.CropDisease;
import farming.cropalert.data.dto.Login;
import farming.cropalert.fragment.UpdateFragment;
import farming.cropalert.rest.request.GsonRequest;
import farming.cropalert.util.Utils;
import com.google.android.gms.location.LocationServices;


/**
 * Created by swarna on 4/11/15.
 */
public class HomeScreenActivity extends ActionBarActivity {
    private String userName;
    private TextView welcomeText;
    private EditText  crop;
    private EditText disease;
    private EditText symptoms;
    private EditText location;
    private View search;
    private GoogleApiClient googleApiClient;
    //    //
    //crop={cropname}&disease={diseasename}&symptoms={symptoms}&location={locationname}

    public static final String PARAM_CROP_KEY = "crop";
    public static final String PARAM_DISEASE_KEY="disease";
    public static final String PARAM_SYMPTOMS_KEY="symptoms";
    public static final String PARAM_LOCATION_KEY="location";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = null;
        if (getIntent() != null) {
            extras = getIntent().getExtras();
        }
        Object obj = extras == null ? null : extras.get(MainActivity.PARAM_STRING);
        if (obj == null && savedInstanceState != null) {
            obj = savedInstanceState.get(MainActivity.PARAM_STRING);
        }
        if (obj!=null) {
            userName = (String) obj;
        }
        setContentView(R.layout.home);
        initViews();
        if (userName!=null) {
            welcomeText.setText(getString(R.string.hi) + " " + userName);
        }
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                search();
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

        if (id==R.id.update) {
            Intent intent = new Intent(this, UpdateUserActivity.class);


            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop () {

        super.onStop();

    }

    private void initViews() {
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        crop = (EditText) findViewById(R.id.crop);
        disease = (EditText) findViewById(R.id.disease);
        symptoms = (EditText) findViewById(R.id.symptoms);
        location = (EditText) findViewById(R.id.location);
        search = findViewById(R.id.search);


    }
   private void search() {
        String cropName = crop.getText().toString();
        Utils.trim(cropName);
        String diseaseName = disease.getText().toString();
        Utils.trim(diseaseName);
        String symptomsName = symptoms.getText().toString();
        Utils.trim(symptomsName);
        String locationName = location.getText().toString();
        Utils.trim(locationName);
       Intent intent = new Intent(HomeScreenActivity.this, SearchResultsActivity.class);
       intent.putExtra(PARAM_LOCATION_KEY, locationName);
       intent.putExtra(PARAM_CROP_KEY, cropName);
       intent.putExtra(PARAM_DISEASE_KEY, diseaseName);
       intent.putExtra(PARAM_SYMPTOMS_KEY, symptomsName);
       startActivity(intent);
       overridePendingTransition(0, 0);



    }

    /*protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }*/







}
