package com.example.sqltest;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;

public class MainActivity extends Activity {
	   private ProgressDialog pDialog;
	   
	    // URL to get contacts JSON
	    private static String url = "http://api.androidhive.info/contacts/";
	 
	    // JSON Node names
	    private static final String TAG_CONTACTS = "contacts";
	    private static final String TAG_ID = "id";
	    private static final String TAG_NAME = "name";
	    private static final String TAG_EMAIL = "email";
	    private static final String TAG_ADDRESS = "address";
	    private static final String TAG_GENDER = "gender";
	    private static final String TAG_PHONE = "phone";
	    private static final String TAG_PHONE_MOBILE = "mobile";
	    private static final String TAG_PHONE_HOME = "home";
	    private static final String TAG_PHONE_OFFICE = "office";
	 
	    // contacts JSONArray
	    JSONArray contacts = null;
	    AlertDialog alertDialogStores;

	 
	    // Hashmap for ListView
	    ArrayList<HashMap<String, String>> contactList;
	    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		String [] arrayStr = new String[5];
		JSONObject jsonobject[] = new JSONObject[5];
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
		jsonobject = JSONfunctions.getJSONfromURL("http://ec2-54-213-181-25.us-west-2.compute.amazonaws.com/htdocs/lib/android/android.php");
	      // contactList = new ArrayList<HashMap<String, String>>();
	    
	       ListView listViewItems = new ListView(this);
	       
	       for(int i = 0; i<jsonobject.length;i++) {
	    	   	arrayStr[i] = jsonobject[i].getString("title");
	    	   	arrayStr[i] += "\n"+jsonobject[i].getString("content");
	    	   		   		}
	       
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                   R.layout.list_item, R.id.content, arrayStr);
           
           
           listViewItems.setAdapter(adapter);
           /*alertDialogStores = new AlertDialog.Builder(MainActivity.this)
           .setView(listViewItems)
           .setTitle("Stores")
           .show();*/
           this.addContentView(listViewItems, new ListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
           
	       	} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("eee",e.getMessage());
	       			}
	       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
