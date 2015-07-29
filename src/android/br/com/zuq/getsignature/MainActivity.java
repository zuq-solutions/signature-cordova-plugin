package br.com.zuq.getsignature;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;


public class MainActivity extends CordovaActivity {

    // FIXME: Static Scope
    private static CallbackContext callback;

    public static void configureCallback(CallbackContext context) {
        callback = context;
    }

    private SignatureView signatureView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();
        setContentView(resources.getIdentifier("activity_main", "layout", package_name));

        signatureView = new SignatureView(this);

        RelativeLayout place = (RelativeLayout)findViewById(
                resources.getIdentifier("signature_place", "id", package_name));
        place.addView(signatureView);

        addEventListeners();
    }

    private void addEventListeners() {

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        Button cleanButton   = (Button) findViewById(getResource("clean", "id"));
        Button confirmButton = (Button) findViewById(getResource("confirm", "id"));
        Button cancelButton  = (Button) findViewById(getResource("cancel", "id"));

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject result = new JSONObject();
                    result.put("error", 400);
                    callback.error(result);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    callback.error("Unknown error");
                }

                finish();
            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject result = signatureView.saveImage();
                callback.success(result);
                signatureView.clear();
                finish();
            }
        });
    }

    private int getResource(String name, String category) {
      String package_name = getApplication().getPackageName();
      Resources resources = getApplication().getResources();

      return resources.getIdentifier(name, category, package_name);
    }

}
