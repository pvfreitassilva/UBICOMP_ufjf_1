package br.ufjf.ubicomp01;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class MainActivity extends Activity {
	
	public Dados dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(3000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally {
					dados = new Dados();
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					i.putExtra("dados", dados );
					startActivity(i);
				}
			}
		};
		timer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
