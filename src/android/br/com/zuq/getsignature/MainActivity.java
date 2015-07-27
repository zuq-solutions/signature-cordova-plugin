package br.com.zuq.getsignature;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import org.apache.cordova.CordovaActivity;


public class MainActivity extends CordovaActivity {

    private SignatureView signatureView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();
        setContentView(resources.getIdentifier("activity_main", "layout", package_name));

        signatureView = new SignatureView(this);

        RelativeLayout place = (RelativeLayout)findViewById(resources.getIdentifier("signature_place", "id", package_name));
        place.addView(signatureView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        getMenuInflater().inflate(resources.getIdentifier("menu_main", "menu", package_name), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        int clean = resources.getIdentifier("action_clean", "id", package_name);
        int send  = resources.getIdentifier("action_send", "id", package_name);

        if (id == clean) {
            signatureView.clear();
            return true;
        }

        else if (id == send) {
            signatureView.saveImage();
            signatureView.clear();
        }

        return super.onOptionsItemSelected(item);
    }

}
