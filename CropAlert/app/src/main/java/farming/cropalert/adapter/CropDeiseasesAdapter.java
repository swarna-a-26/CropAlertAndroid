package farming.cropalert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import farming.cropalert.R;
import farming.cropalert.application.CropAlertApplication;
import farming.cropalert.data.dto.CropDisease;

/**
 * Created by swarna on 4/12/15.
 */
public class CropDeiseasesAdapter extends ArrayAdapter<CropDisease> {
    private Context ctx;
    private List<CropDisease> cropDiseases;

    public CropDeiseasesAdapter(Context context, int resource, List<CropDisease> cropDiseases) {
        super(context, resource);
        this.cropDiseases = cropDiseases;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        ViewHolder viewHolder;
        if (convertView != null) {
            itemView = convertView;
        } else {
            itemView = LayoutInflater.from(ctx).inflate(R.layout.search_result_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (NetworkImageView) itemView.findViewById(R.id.cropImage);
            viewHolder.disease = (TextView) itemView.findViewById(R.id.itemDisease);
            viewHolder.location = (TextView) itemView.findViewById(R.id.itemLocation);
            viewHolder.symptoms = (TextView) itemView.findViewById(R.id.itemSymptoms);
            itemView.setTag(viewHolder);

        }

        viewHolder = (ViewHolder) itemView.getTag();
        CropDisease data = cropDiseases.get(position);
        if (viewHolder != null) {
// Get the ImageLoader through your singleton class.
            ImageLoader mImageLoader = CropAlertApplication.getInstance(ctx).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
            viewHolder.imageView.setImageUrl("htpp://urls", mImageLoader);
            viewHolder.disease.setText(data.getDisease());
            viewHolder.location.setText(data.getLocation());
            viewHolder.symptoms.setText(data.getSymptom());


        }








        return itemView;
    }



    public static class ViewHolder {
        NetworkImageView imageView;
        TextView disease;
        TextView location;
        TextView symptoms;





    }
}
