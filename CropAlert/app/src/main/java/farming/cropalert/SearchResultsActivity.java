package farming.cropalert;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import farming.cropalert.adapter.CropDeiseasesAdapter;
import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.CropDisease;
import farming.cropalert.fragment.UpdateFragment;
import farming.cropalert.rest.request.GsonRequest;


/**
 * Created by swarna on 4/12/15.
 */
public class SearchResultsActivity extends ActionBarActivity {



    private  String crop;
    private  String disease;
    private  String symptomsName;
    private  String locationName;
    private  ListView searchResults;
    private ViewGroup noResultsFound;
    private ViewGroup updatefragmentHolder;
    private CropDeiseasesAdapter cropDeiseasesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);


    }

    @Override
    protected void onStart () {
        super.onStart();
        Bundle extras=null;
        if (getIntent() != null) {
            extras = getIntent().getExtras();
        }
        if (extras!=null) {
            populateDataFromBundle(extras);
        }
        initViews();
        search( crop, disease,symptomsName, locationName);



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
        searchResults = (ListView) findViewById(R.id.searchResults);
        noResultsFound = (ViewGroup) findViewById(R.id.noResultsFound);
        updatefragmentHolder = (ViewGroup) findViewById(R.id.updatefragmentHolder);
        cropDeiseasesAdapter = new CropDeiseasesAdapter(this,R.layout.search_result_item);
        searchResults.setAdapter(cropDeiseasesAdapter);
    }

    private void search(final String crop,final String disease,final String symptomsName, final String locationName) {

        Response.Listener<List<CropDisease>> responseListener = new Response.Listener<List<CropDisease>>() {

            @Override
            public void onResponse(List<CropDisease> cropDiseases) {
                if (cropDiseases!=null && cropDiseases.size()>0) {
                    Log.d("Swarna:", "size=" + cropDiseases.size());
                    showCropsDiseasesList(cropDiseases);
                } else {
                    showNoResults();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SearchResultsActivity.this, R.string.try_again_later, Toast.LENGTH_LONG).show();


            }
        };
        //crop={cropname}&disease={diseasename}&symptoms={symptoms}&location={locationname}
        GsonRequest<List<CropDisease>> request = new GsonRequest<List<CropDisease>>("http://alertcrop.mybluemix.net/cropdisease?crop=" + crop + "&disease="+ disease
                + "&symptoms=" + symptomsName + "&location=" + locationName ,
                new TypeToken<List<CropDisease>>(){}.getType(),
                null,responseListener,errorListener);
        CropAlertApplication.getInstance(this).addToRequestQueue(request);
    }

    private void populateDataFromBundle(Bundle extras) {
        crop = extras == null ? null : extras.getString(HomeScreenActivity.PARAM_CROP_KEY,"");
        disease = extras == null ? null : extras.getString(HomeScreenActivity.PARAM_DISEASE_KEY,"");
        symptomsName= extras == null ? null : extras.getString(HomeScreenActivity.PARAM_SYMPTOMS_KEY,"");
        locationName = extras == null ? null : extras.getString(HomeScreenActivity.PARAM_LOCATION_KEY,"");

    }

    private void showCropsDiseasesList(List<CropDisease> cropDiseases) {
        cropDeiseasesAdapter.setCropDiseases(cropDiseases);
        cropDeiseasesAdapter.notifyDataSetChanged();
        searchResults.setVisibility(View.VISIBLE);
        noResultsFound.setVisibility(View.GONE);

    }

    private void showNoResults() {
        noResultsFound.setVisibility(View.VISIBLE);
        searchResults.setVisibility(View.GONE);
        //newInstance(String crop, String disease,String symptomsName,String locationName)
        UpdateFragment updateFragment = UpdateFragment.newInstance(crop, disease,symptomsName,locationName);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.updatefragmentHolder, updateFragment, UpdateFragment.TAG);
        fragmentTransaction.commit();

    }




}
