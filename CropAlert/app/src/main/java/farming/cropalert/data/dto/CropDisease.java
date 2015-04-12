package farming.cropalert.data.dto;

import java.util.List;

/**
 * Created by swarna on 4/11/15.
 */
public class CropDisease {
    private  String crop;
    private  String disease;
    private  String symptom;
    private  String remedy;
    private  String harvest;
    private  String location;
    private  int aa;
    private  int rating;
    private String image;
    private List<String> imageUrls;

    public String getCrop() {
        return crop;
    }
    public String getDisease() {
        return disease;
    }
    public String getSymptom() {
        return symptom;
    }
    public String getLocation() { return location;}

    public String getRemedy() {
        return remedy;
    }

    public String getHarvest() {
        return harvest;
    }

    public int getRating() {
        return rating;
    }
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getImageUrl() {

        return image;

    }


}
