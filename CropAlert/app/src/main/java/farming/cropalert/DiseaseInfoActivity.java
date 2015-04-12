package farming.cropalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.CropDisease;
import farming.cropalert.rest.request.GsonRequest;
import farming.cropalert.util.Utils;


/**
 * Created by swarna on 4/12/15.
 */
public class DiseaseInfoActivity extends ActionBarActivity {

    private TextView infoDiseaseTitle;
    private NetworkImageView infoCropImage;
    private TextView locationtext;
    private TextView symptomstext;
    private TextView remedytext;
    private TextView harvesttimetext;
    private RatingBar ratingBar;
    private String cropDiseaseId;
    private TextView temperaturetext;
    private TextView humiditytext;
    private TextView moisturetext;
    public static final String DISEASE_ID_KEY="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras=null;
        if (getIntent() != null) {
            extras = getIntent().getExtras();
        }
        if (extras!=null) {
            populateDataFromBundle(extras);
        }
        setContentView(R.layout.diseaseinfo);
        initViews();
        getDataFromCropDisease();

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

        infoDiseaseTitle = (TextView) findViewById(R.id.infoDiseaseTitle);

        infoCropImage =( NetworkImageView ) findViewById(R.id.infoCropImage);
        locationtext = (TextView) findViewById(R.id.locationtext);
        symptomstext= (TextView) findViewById(R.id.symptomstext);
        remedytext= (TextView) findViewById(R.id.remedytext);
        harvesttimetext= (TextView) findViewById(R.id.harvesttimetext);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                                   @Override
                                                   public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                                       if (fromUser) {
                                                           int ratingInfo = (int) Math.ceil((float)ratingBar.getRating());
            sendNewRatingToServer(ratingInfo);
                                                       }
                                                   }
                                               }
                    );

        temperaturetext=(TextView) findViewById(R.id.temperaturetext);
        humiditytext = (TextView) findViewById(R.id.humiditytext);
        moisturetext = (TextView) findViewById(R.id.moisturetext);



    }

    private void sendNewRatingToServer(int numStars) {

    }

    private void populateDataFromBundle(Bundle extras) {
        cropDiseaseId = extras == null ? null : extras.getString(DISEASE_ID_KEY,"");

    }
    private void getDataFromCropDisease() {
        Response.Listener<List<CropDisease>> responseListener = new Response.Listener<List<CropDisease>>() {

            @Override
            public void onResponse(List<CropDisease> cropDiseases) {
                if (cropDiseases!=null && cropDiseases.size()>0) {
                    updateCropDiseaseInformation(cropDiseases.get(0));
                }


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(DiseaseInfoActivity.this, R.string.try_again_later, Toast.LENGTH_LONG).show();


            }
        };
        //crop={cropname}&disease={diseasename}&symptoms={symptoms}&location={locationname}
        String url = "http://alertcrop.mybluemix.net/cropdiseaseinfo?cropdiseaseid=" + cropDiseaseId;
        android.util.Log.d("Swarna:", "infourl=" + url);
        GsonRequest<List<CropDisease>> request = new GsonRequest<List<CropDisease>>(url ,
                new TypeToken<List<CropDisease>>(){}.getType(),
                null,responseListener,errorListener);
        CropAlertApplication.getInstance(this).addToRequestQueue(request);
    }

    private void updateCropDiseaseInformation(CropDisease cropDisease) {



        Utils.checkAndUpdateText(infoDiseaseTitle,cropDisease.getDisease());
        Utils.checkAndUpdateText(locationtext,cropDisease.getLocation());
        Utils.checkAndUpdateText(remedytext,cropDisease.getRemedy());
        Utils.checkAndUpdateText(harvesttimetext,cropDisease.getHarvest());
        Utils.checkAndUpdateText(temperaturetext,cropDisease.getTemperature());
        Utils.checkAndUpdateText(humiditytext,cropDisease.getHumidity());
        Utils.checkAndUpdateText(moisturetext,cropDisease.getMoist());
        Utils.checkAndUpdateText(symptomstext,cropDisease.getSymptom());

        ratingBar.setNumStars(5);
        ratingBar.setRating((float) cropDisease.getRating());
        ImageLoader imageLoader = CropAlertApplication.getInstance(this).getImageLoader();
        infoCropImage.setImageUrl(cropDisease.getImageUrl(), imageLoader);




    }





}
