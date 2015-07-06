package br.ufjf.ubicomp01;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

public class CaloService extends IntentService {
	
	Double longitude = 0d;
	Double latitude = 0d;
	GPSTracker gps;
	
	public CaloService() {
		super("CaloService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// O que deveria ser feito aqui:
		// 1-Verifica o local em que ele esta
		// 2-Verifica se o local possui perfil no banco de dados
		// 3-Caso possua perfil, altere as conf do aparelho.

		// just sleep for 5 seconds.
		 long endTime = System.currentTimeMillis() + 5 * 1000;
		 //while (System.currentTimeMillis() < endTime) {
		 while(true){
			 synchronized (this) {
				 try {
					 //wait(endTime - System.currentTimeMillis());
					 wait(5000);
					 
					 gps = new GPSTracker(this);
					 
					 if(gps.canGetLocation){
						 
						 longitude = gps.longitude;
						 latitude = gps.latitude;
						 
						 BDController crud = new BDController(getBaseContext());
						 
						 Cursor cursor = crud.listaTodosLocais();
						 
						 if(cursor!=null){
							 
							 Double longAux, latiAux;
							 int raio;
	
							 if (cursor.moveToFirst()) {
								do {
										
									longAux = Double.parseDouble(cursor.getString(cursor.getColumnIndex(CriaBD.LONGITUDE)));
									latiAux = Double.parseDouble(cursor.getString(cursor.getColumnIndex(CriaBD.LATITUDE)));
									raio = cursor.getInt(cursor.getColumnIndex(CriaBD.RAIO));
									
									Location locAux = new Location("Aux");
									locAux.setLatitude(latiAux);
									locAux.setLongitude(longAux);
									
									//Toast.makeText(this,
									//		"Lat: "+gps.getLocation().getLatitude()+" Long: "+gps.getLocation().getLongitude(),
									//		Toast.LENGTH_SHORT).show();
									Log.d("teste", "Lat: "+gps.getLocation().getLatitude()+" Long: "+gps.getLocation().getLongitude());
									
									
									if(locAux.distanceTo(gps.getLocation()) <= raio  ){
										
										Cursor perfis = crud.getPerfilById( cursor.getColumnIndex(CriaBD.ID_PERFIL));
										
										Log.d("teste", "Entrando em local salvo");
										
										if(perfis!=null){
											
											//TODO ALTERAR CONFIGS DO APARELHO AQUI
											
										}
										
									}
										
									} while (cursor.moveToNext());
							 	}
						 	}
					 }
				 } catch (Exception e) {
					 Toast.makeText(this, "Erro no serviço CALo", Toast.LENGTH_SHORT).show();
					 stopSelf();
				 }
			 }
			 endTime = System.currentTimeMillis() + 5 * 1000;
		 }
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Serviço Finalizado", Toast.LENGTH_SHORT).show();
	}
}
