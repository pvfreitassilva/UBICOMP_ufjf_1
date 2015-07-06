package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class MarcarLocalActivity extends Activity implements OnMapReadyCallback {
	
	//private Dados dados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marcar_local);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

		
		//Intent i = getIntent();
		//dados = (Dados) i.getSerializableExtra("dados");
	}
	
	public void avancar(View view){
    	Intent i = new Intent(this, MenuActivity.class);
    	//i.putExtra("dados", dados);
    	startActivity(i);
    	
    }

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub
		
	}
}
