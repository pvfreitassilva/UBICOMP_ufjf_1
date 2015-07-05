package br.ufjf.ubicomp01;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class CaloService extends IntentService {
	public CaloService() {
		super("CaloService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//O que deveria ser feito aqui:
		//1-Verifica o local em que ele esta
		//2-Verifica se o local possui perfil no banco de dados
		//3-Caso possua perfil, altere as conf do aparelho.
		
		//just sleep for 5 seconds.
		long endTime = System.currentTimeMillis() + 5 * 1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show();
	    return super.onStartCommand(intent,flags,startId);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Serviço Finalizado", Toast.LENGTH_SHORT).show();
	}
}
