package farming.cropalert.util;

import android.widget.TextView;

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
}
