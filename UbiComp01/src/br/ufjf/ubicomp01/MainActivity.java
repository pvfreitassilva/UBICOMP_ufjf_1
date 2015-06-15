package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    	
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(1000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally {
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					startActivity(i);
				}
			}
		};
		timer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void marcarLocal(View view){
    	
    	//Intent i = new Intent("br.ufjf.UbiComp01.PERFIS");
    	Intent i = new Intent(this, Perfis.class);
    	startActivity(i);
    	
    }
}
