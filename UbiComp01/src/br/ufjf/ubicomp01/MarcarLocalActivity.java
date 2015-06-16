package br.ufjf.ubicomp01;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MarcarLocalActivity extends Activity {
	
	private ArrayList<Perfil> listPerfil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marcar_local);
		
		Intent i = getIntent();
	    listPerfil = (ArrayList<Perfil>) i.getSerializableExtra("LISTPERFIL");
	}
	
	public void avancar(View view){
    	Intent i = new Intent(this, MenuActivity.class);
    	i.putExtra("LISTPERFIL", listPerfil);
    	startActivity(i);
    	
    }
}
