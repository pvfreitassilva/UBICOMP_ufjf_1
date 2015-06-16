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
	
	private ArrayList<Perfil> listPerfil;

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
					buildListPerfil();
					Intent i = new Intent(getApplicationContext(), MenuActivity.class);
					i.putExtra("LISTPERFIL", listPerfil );
					startActivity(i);
				}
			}
		};
		timer.start();
    }
    
    private void buildListPerfil(){
		
		listPerfil = new ArrayList<Perfil>();
		
		listPerfil.add(new Perfil(
				1,
				"Silencioso com vibração",
				50,
				true,
				false,
				false,
				null));
		
		listPerfil.add(new Perfil(
				2,
				"Silencioso sem vibração",
				0,
				false,
				false,
				false,
				null));	
		
		listPerfil.add(new Perfil(
				3,
				"Ocupado",
				0,
				false,
				false,
				true,
				"Estou ocupado"));
		
		listPerfil.add(new Perfil(
				4,
				"Geral",
				100,
				true,
				false,
				false,
				null));	
		
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
