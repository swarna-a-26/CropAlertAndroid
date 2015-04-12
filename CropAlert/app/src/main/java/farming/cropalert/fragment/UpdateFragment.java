package farming.cropalert.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farming.cropalert.HomeScreenActivity;
import farming.cropalert.R;

/**
 * Created by swarna on 4/12/15.
 */
public class UpdateFragment extends Fragment {
    public static final String TAG = "UpdateFragmentTag";
    private  String  crop;
    private String disease;
    private String symptomsName;
    private String locationName;


    public static UpdateFragment newInstance(String crop, String disease,String symptomsName,String locationName) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HomeScreenActivity.PARAM_CROP_KEY, crop);
        bundle.putString(HomeScreenActivity.PARAM_DISEASE_KEY, disease);
        bundle.putString(HomeScreenActivity.PARAM_SYMPTOMS_KEY, symptomsName);
        bundle.putString(HomeScreenActivity.PARAM_LOCATION_KEY, locationName);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_layout, container, false);
        getArgumentsAndInitialise(savedInstanceState);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {

        super.onStop();
    }



    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);


    }

    private void getArgumentsAndInitialise(Bundle args) {
        this.crop = getArguments().getString(HomeScreenActivity.PARAM_CROP_KEY);
        this.disease = getArguments().getString(HomeScreenActivity.PARAM_DISEASE_KEY);
        this.symptomsName =  getArguments().getString(HomeScreenActivity.PARAM_SYMPTOMS_KEY);
        this.locationName = getArguments().getString(HomeScreenActivity.PARAM_LOCATION_KEY);

    }


}
