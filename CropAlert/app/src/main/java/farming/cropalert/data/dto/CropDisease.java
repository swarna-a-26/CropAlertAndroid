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

    private  int rating;
    private String image;
    private List<String> imageUrls;
    private String temp;
    private String humidity;
    private String moist;
    private String id;
    private String startdate;
    private String reportdate;
    private String aae;
    private  String aa;
    private String soiltype;
    private String enddate;
    private String lat;
    private String lon;









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
    public String getTemperature() {
        return temp;
    }
    public String getHumidity() {
        return humidity;
    }

    public String getMoist() {
        return moist;
    }

    public String getId() {
        return id;
    }



}
