package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity {
	
	//private Dados dados;
	GPSTracker gps;

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		//Intent intent = getIntent();
		
		//dados = (Dados) intent.getSerializableExtra("dados");
		
		/*if(dados!=null){
		
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
				
		
		}*/
		
		AtualizaStatus statusLoop = new AtualizaStatus(getBaseContext());
		statusLoop.execute("");
	}
	
	public void startCalo(View view){		
		
		gps = new GPSTracker(StatusActivity.this);
		
		if(gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			TextView textViewLat = (TextView) findViewById(R.id.textViewLatitude);
			textViewLat.setText("Latitude: " + String.valueOf(latitude));
			
			TextView textViewLong = (TextView) findViewById(R.id.textViewLongitude);
			textViewLong.setText("Longitude: " + String.valueOf(longitude));
			
			Toast.makeText(
					getApplicationContext(),
					"Localização: \nLatitude: " + latitude + "\nLongitude: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			gps.showSettingsAlert();
		}
		
		//Intent i = new Intent(this, GPSTracker.class);
		//startService(i);
		
		Intent intent = new Intent(this, CaloService.class);
		startService(intent);
	}
	
	public void finishCalo(View view){
		Intent intent = new Intent(this, CaloService.class);
		//Intent intent = new Intent(this, GPSTracker.class);
		stopService(intent);
	}
	
	private class AtualizaStatus extends AsyncTask<String, Integer, String> {

		Local localAtual;
		Perfil perfilAtual;
		BDController crud;

		public AtualizaStatus(Context context) {
			localAtual = null;
			perfilAtual = null;
			crud = new BDController(context);
		}

		@Override
		protected String doInBackground(String... params) {
			//TODO VERIFICAR ALTERACOES AQUI
			
			while(true){
				synchronized (this) {
					 try {
						 wait(5000);
						 Log.d("teste", "Consultando local");
						 localAtual = crud.getLocalAtual();
						 perfilAtual = crud.getPerfilAtual();
						 publishProgress();
						 
					 }
					 catch(Exception e){
						 Log.d("teste", "Erro ao atualizar status");
						 Log.d("teste", e.toString());
						 //return "";
					 }
				}
			}
		}

		@Override
		protected void onPostExecute(String result) {

		}

		@Override
		protected void onPreExecute() {

		}
		
		@Override
		protected void onProgressUpdate(Integer... progress) {
			if(localAtual!=null)
				 Log.d("teste", "Local encontrado");
			 				
			TextView textView = (TextView) findViewById(R.id.textViewLocal);
			if(localAtual!=null)
				textView.setText("Local atual: " + localAtual.nome);
			else
				textView.setText("Local atual: < desconhecido >");
			
			textView = (TextView) findViewById(R.id.textViewPerfil);
			if(perfilAtual!=null){
				textView = (TextView) findViewById(R.id.textViewPerfil);
				textView.setText("Perfil atual: " + perfilAtual.nome);
				
				textView = (TextView) findViewById(R.id.textViewVolume);
				textView.setText("Volume: " + perfilAtual.volume);
				
				textView = (TextView) findViewById(R.id.textViewVibrar);
				if(perfilAtual.vibrar)
					textView.setText("Vibrar: ligado");
				else
					textView.setText("Vibrar: desligado");
				
				textView = (TextView) findViewById(R.id.textViewRecusar);
				if(perfilAtual.recursarChamadas)
					textView.setText("Recusar chamdas: ligado");
				else
					textView.setText("Recusar chamadas: desligado");
				
				textView = (TextView) findViewById(R.id.textViewResponder);
				
				if(perfilAtual.responderChamadas)
					textView.setText("Responder chamadas: ligado");
				else
					textView.setText("Responder chamadas: desligado");
								
				textView = (TextView) findViewById(R.id.textViewMensagem);
				if(perfilAtual.mensagemPadrao!=null)
					textView.setText("Mensagem padrão: " + perfilAtual.mensagemPadrao);
				else
					textView.setText("Mensagem padrão: < não definida >");
				
			}
			else
				textView.setText("Perfil atual: < nenhum >");
	    }
	}
}
