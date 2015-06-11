package com.myapp.cktscore;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myapp.cktscore.view.ListviewAndroid;
import com.myapp.cktscore.view.ViewPagerr;


public class WelcomeScreen extends FragmentActivity {
	Button btnfacebook;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        getActionBar().setTitle("CKTSCORE");
        getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		  setContentView(R.layout.activity_welcome_screen);
		/*  btnfacebook =(Button) findViewById(R.id.btnText);
		
        TextView txtView =(TextView)findViewById(R.id.txtLink);
        txtView.setClickable(true);*/
        // FaceBook integrate - get key hash.
       /* try {
        	   PackageInfo info = getPackageManager().getPackageInfo(
        	     "com.myapp.cktscore",
        	     PackageManager.GET_SIGNATURES);
        	   for (Signature signature : info.signatures) {
        	    MessageDigest md = MessageDigest.getInstance("SHA");
        	    md.update(signature.toByteArray());
        	    Log.d("KeyHash:",
        	      Base64.encodeToString(md.digest(), Base64.DEFAULT));

        	   }
        	  } catch (NameNotFoundException e) {

        	  } catch (NoSuchAlgorithmException e) {

        	  }*/
    }
    public void btnHlink(View view) {
    	Intent intent = new Intent(getBaseContext(),teamname.class);
    	startActivity(intent);
	}
    public void btnHlink1(View view){
    	Intent intent = new Intent(getBaseContext(),FaceBook_Page.class);
    	startActivity(intent);
    }
    public void btnListView(View view){
    	Intent intent = new Intent(getBaseContext(),My_ListView.class);
    	startActivity(intent);
    }
    public void btnListView1(View view){
    	Intent intent = new Intent(getBaseContext(),ListviewAndroid.class);
    	startActivity(intent);
    }
    public void btnViewPager(View view){
    	Intent intent = new Intent(getBaseContext(),ViewPagerr.class);
    	startActivity(intent);
    }
    public void btnMenu(View view){
    	/*Intent intent = new Intent(getBaseContext(),Fragment_menu.class);
    	startActivity(intent);*/
    	/*Fragment fragment = new Fragment_menu();
    	 if (fragment != null) {
        	 FragmentManager fragmentManager = getSupportFragmentManager();
        	 FragmentTransaction ft = fragmentManager.beginTransaction();
        	 ft.add(R.id.Frame_layout, fragment);
//            ft.replace(R.id.Frame_layout, fragment);
            ft.addToBackStack(null);
            ft.commit();
    	 }  */
    }
}
