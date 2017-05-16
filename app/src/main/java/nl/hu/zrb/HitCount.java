package nl.hu.zrb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HitCount extends AppCompatActivity implements Runnable {
	TextView tv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.textview1);

    }
    
    private Handler myHandler = new Handler() {
    	public void handleMessage(Message msg){
    		String s = msg.getData().getString("bericht");
    		tv.setText(s);
    	}
    };
    
    public void run() {
		try {

			URL url = new URL(getResources().getString(R.string.url));
			HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
			InputStream in = httpcon.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String s = reader.readLine();
			Message msg = new Message();
			msg.getData().putString("bericht", s);
			myHandler.sendMessage(msg);
		}
		catch(IOException ioe){
			Log.e("HitCount", ioe.toString());
		} 

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.connect_item){
			tv.setText("wachten...");
			new Thread(this).start();
			return true;
		}
		return false;

	}
}