/**
 * A plugin for Apache Cordova (Phonegap) which will ask the user to
 * write his or her a signature, which gets captured into an image.
 *
 * Copyright (c) 2013-2014, Code Yellow B.V.
 *
 * Heavily based on Holly Schinsky's tutorial:
 * http://devgirl.org/2013/09/17/how-to-write-a-phonegap-3-0-plugin-for-android/
 */
package br.com.zuq.getsignature;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class SignaturePlugin extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
		throws JSONException
	{
		if (action.equals("new")) {
			// TODO: Make default title translatable
			String title = "Please sign below", htmlFile = null;
			if (args.length() >= 2) htmlFile = args.getString(1);
			if (args.length() >= 1) title    = args.getString(0);

			Activity act = this.cordova.getActivity();

			Intent intent = new Intent(act, MainActivity.class);
			act.startActivity(intent);

			return true;
		} else {
			callbackContext.error("Unknown action: "+action);
			return false;
		}
	}
}
