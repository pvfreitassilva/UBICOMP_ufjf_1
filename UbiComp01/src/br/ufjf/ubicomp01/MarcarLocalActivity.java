package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MarcarLocalActivity extends Activity implements OnMapReadyCallback {
	
	GPSTracker gps;
	
	//private Dados dados;
	//Location local;
	double latitude = 0.0;
	double longitude = 0.0;
	int raio = 10;
	TextView textViewRaio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marcar_local);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        gps = new GPSTracker(MarcarLocalActivity.this);
      
        if(gps.canGetLocation()) {
        	        	
			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
			
			/*TextView textViewLat = (TextView) findViewById(R.id.textViewLatitude);
			textViewLat.setText("Latitude: " + String.valueOf(latitude));
			
			TextView textViewLong = (TextView) findViewById(R.id.textViewLongitude);
			textViewLong.setText("Longitude: " + String.valueOf(longitude));*/
			
			Toast.makeText(
					getApplicationContext(),
					"Localização: \nLatitude: " + latitude + "\nLongitude: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			gps.showSettingsAlert();
		}
        
        SeekBar seekBarRaio = (SeekBar) findViewById(R.id.seekBarRaio);
        seekBarRaio.setMax(1000);
        seekBarRaio.setProgress(raio);
        
        textViewRaio = (TextView) findViewById(R.id.textViewRaio);
        textViewRaio.setText("Raio: "+raio+" m");
        
        seekBarRaio.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        	}
        	
        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        	}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				raio = progress;
				textViewRaio.setText("Raio: "+raio+" m");
			}
        });

		//Intent i = getIntent();
		//dados = (Dados) i.getSerializableExtra("dados");
	}
	
	public void avancar(View view){
    	Intent i = new Intent(this, EditLocalActivity.class);
    	i.putExtra("novo", true);
    	i.putExtra("latitude", latitude);
    	i.putExtra("longitude", longitude);
    	i.putExtra("raio", raio);
    	//i.putExtra("dados", dados);
    	startActivity(i);
    	
    }

	@Override
	public void onMapReady(GoogleMap map) {
		// TODO Auto-generated method stub
		
		LatLng localAtual = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(localAtual, 13));

        map.addMarker(new MarkerOptions()
                .title("Posição atual")
                .position(localAtual));
	}
}
