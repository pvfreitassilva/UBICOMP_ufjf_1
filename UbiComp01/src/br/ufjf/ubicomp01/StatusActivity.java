package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity {
	
	private Dados dados;
	GPSTracker gps;

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		Intent intent = getIntent();
		
		dados = (Dados) intent.getSerializableExtra("dados");
		
		if(dados!=null){
		
			TextView textView = (TextView) findViewById(R.id.textViewLocal);
			if(dados.localAtual!=null)
				textView.setText("Local atual: " + dados.localAtual.nome);
			else
				textView.setText("Local atual: < desconhecido >");
			
			if(dados.perfilAtual!=null){
				textView = (TextView) findViewById(R.id.textViewPerfil);
				textView.setText("Perfil atual: " + dados.perfilAtual.nome);
				
				textView = (TextView) findViewById(R.id.textViewVolume);
				textView.setText("Volume: " + dados.perfilAtual.volume);
				
				textView = (TextView) findViewById(R.id.textViewVibrar);
				if(dados.perfilAtual.vibrar)
					textView.setText("Vibrar: ligado");
				else
					textView.setText("Vibrar: desligado");
				
				textView = (TextView) findViewById(R.id.textViewRecusar);
				if(dados.perfilAtual.recursarChamadas)
					textView.setText("Recusar chamdas: ligado");
				else
					textView.setText("Recusar chamadas: desligado");
				
				textView = (TextView) findViewById(R.id.textViewResponder);
				
				if(dados.perfilAtual.responderChamadas)
					textView.setText("Responder chamadas: ligado");
				else
					textView.setText("Responder chamadas: desligado");
								
				textView = (TextView) findViewById(R.id.textViewMensagem);
				if(dados.perfilAtual.mensagemPadrao!=null)
					textView.setText("Mensagem padrão: " + dados.perfilAtual.mensagemPadrao);
				else
					textView.setText("Mensagem padrão: < não definida >");
				
			}
			else
				textView.setText("Perfil atual: < nenhum >");
		}
	}
	
	public void startCalo(View view){		
		
		gps = new GPSTracker(StatusActivity.this);
		
		if(gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			TextView textViewLat = (TextView) findViewById(R.id.textViewLatitude);
			textViewLat.setText("Latitude: " + String.valueOf(latitude));
			
			TextView textViewLong = (TextView) findViewById(R.id.textViewLongitudee);
			textViewLong.setText("Longitude: " + String.valueOf(longitude));
			
			Toast.makeText(
					getApplicationContext(),
					"Localização: \nLatitude: " + latitude + "\nLongitude: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			gps.showSettingsAlert();
		}
		
		

		
		//Intent intent = new Intent(this, CaloService.class);
		//startService(intent);
	}
	
	public void finishCalo(View view){
		//Intent intent = new Intent(this, CaloService.class);
		//stopService(intent);
	}
}
