package farming.cropalert.util;

import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by swarna on 4/11/15.
 */
public class Utils {

    public static String trim(String str) {

        if (str!=null && !str.equals("")){
            str.trim();

        }

        return str;

    }

    public static void checkAndUpdateText(TextView view , String data) {
        if (data!=null) {
            view.setText(data);
        } else {
            view.setText("");
        }

    }

    public static String urlEncode(String data) {
        if (data!=null &&!data.isEmpty()) {
            try {
                data = URLEncoder.encode(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        return data;
    }
}
