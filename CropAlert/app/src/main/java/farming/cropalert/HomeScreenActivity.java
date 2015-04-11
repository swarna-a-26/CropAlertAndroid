package farming.cropalert;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Created by swarna on 4/11/15.
 */
public class HomeScreenActivity extends ActionBarActivity {
    private String userName;
    private TextView welcomeText;

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
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        if (userName!=null) {
            welcomeText.setText(getString(R.string.hi) + " " + userName);
        }



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


}
