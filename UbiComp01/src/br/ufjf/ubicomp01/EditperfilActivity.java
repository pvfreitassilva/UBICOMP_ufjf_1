package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

public class EditperfilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		String stringExtra[];
		
		setContentView(R.layout.editperfil);
		
		SeekBar volume = (SeekBar) findViewById(R.id.volume);
		volume.setMax(100);
		
		if(i.getBooleanExtra("NOVOPERFIL", false)==false){
			
			stringExtra = i.getStringExtra("EDITPERFIL").split(",");
			
			Log.d("Teste", stringExtra[0] + " 0");
			Log.d("Teste", stringExtra[1] + " 1");
			Log.d("Teste", stringExtra[2] + " 2");
			Log.d("Teste", stringExtra[3] + " 3");
			Log.d("Teste", stringExtra[4] + " 4");
			Log.d("Teste", stringExtra[5] + " 5");
			Log.d("Teste", stringExtra[6] + " 6");
			//Log.d("Teste", stringExtra[7]);
			
			EditText nome = (EditText) findViewById(R.id.nome);
			nome.setText(stringExtra[1]);
			
			//SeekBar volume = (SeekBar) findViewById(R.id.volume);
			volume.setProgress(new Integer(stringExtra[2]));
			
			CheckBox vibrar = (CheckBox) findViewById(R.id.vibrar);
			vibrar.setSelected(new Boolean(stringExtra[3]));
			
			CheckBox recusarChamadas = (CheckBox) findViewById(R.id.recusarChamadas);
			vibrar.setSelected(new Boolean(stringExtra[4]));
			
			CheckBox responderChamadas = (CheckBox) findViewById(R.id.responderChamadas);
			vibrar.setSelected(new Boolean(stringExtra[5]));
			
			EditText mensagemPadrao = (EditText) findViewById(R.id.mensagemPadrao);
			mensagemPadrao.setText(stringExtra[6]);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editperfil, menu);
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
}
