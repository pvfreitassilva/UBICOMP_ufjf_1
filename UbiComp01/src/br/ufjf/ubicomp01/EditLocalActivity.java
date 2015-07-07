package br.ufjf.ubicomp01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditLocalActivity extends Activity {
	
	Spinner spinnerPerfis;
	
	private Cursor cursor;
	
	Double longitude;
	Double latitude;
	int raio;
	Local local;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_local);
		
		Intent i = getIntent();
		if(i.getBooleanExtra("novo", false)){
			
			longitude = i.getDoubleExtra("longitude", 0.0);
			latitude = i.getDoubleExtra("latitude", 0.0);
			raio = i.getIntExtra("raio", 10);
			
			Button b = (Button) findViewById(R.id.buttonExcluirLocal);
			b.setText("Cancelar");
			
		}else{
			
			//IMPLEMENTAR AQUI PARA EDITAR LOCAL
			
			
			
		}
		
		spinnerPerfis = (Spinner) findViewById(R.id.spinnerPerfis);		
		
		//int size = 1;

		//int i = 1;

		//String[] values = new String[size];
		List<String> values;
		
		values = new ArrayList<String>();

		values.add("< Selecione >");
		
		BDController crud = new BDController(getBaseContext());
		
		Log.d("teste", "teste1");

		cursor = crud.listaTodosPerfis();

		Log.d("teste", "teste2");
		
		if (cursor != null) {

			//size = size + cursor.getCount();
			//values = new String[size];
			

			if (cursor.moveToFirst()) {

				do {
					String nome = cursor.getString(cursor
							.getColumnIndex(CriaBD.NOME));
					values.add(nome);
					//values[i] = nome;
					//i++;
				} while (cursor.moveToNext());
			}
		}

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item , values ); 
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerPerfis.setAdapter(adapter);

		
	}
	
	public void salvar(View view){
		
		String nome_perfil = spinnerPerfis.getSelectedItem().toString();
		
		Log.d("teste", nome_perfil);
		
		BDController crud = new BDController(getBaseContext());
		
		Cursor cursor = crud.getPerfilByName(nome_perfil);
		
		Local l;
		
		Random r = new Random();
		
		EditText nome = (EditText) findViewById(R.id.editTextNomeLocal);
		
		Location location = new Location(nome.getText().toString());
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		
		Log.d("teste", "teste0");
		
		if(cursor!=null){
			
			Log.d("teste", "teste0.1");
			
			cursor.moveToFirst();
			
			int id_perfil = cursor.getInt(cursor.getColumnIndex(CriaBD.ID));
			
			l = new Local(r.nextInt(100), nome.getText().toString(), id_perfil, location, raio);
			
			Log.d("teste", "teste1");
			
			crud.insereLocal(l);
			
			Log.d("teste", "teste2");
			
			Toast.makeText(getBaseContext(),"Local salvo",
					Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(getBaseContext(),"Erro ao salvar local: perfil não encontrado",
					Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void excluir(View view){
		
		//TODO IMPLEMENTAR EXCLUSAO OU CANCELAR
		
		
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
		
	}
}
