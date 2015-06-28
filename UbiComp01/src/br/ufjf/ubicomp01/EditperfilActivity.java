package br.ufjf.ubicomp01;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class EditPerfilActivity extends Activity {

	private Dados dados;
	private int id;
	private Perfil perfil;

	private static SQLiteDatabase sqliteDB = null;

	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.editperfil);
		
		SeekBar volume = (SeekBar) findViewById(R.id.volume);
		volume.setMax(100);
		
		Intent intent = getIntent();
		
		dados = (Dados) intent.getSerializableExtra("dados");
		
		id = intent.getIntExtra("ID", -1);
		
		sqliteDB = this.openOrCreateDatabase(CriaPerfilBD.NOME_BANCO,
				MODE_PRIVATE, null);

		cursor = sqliteDB.rawQuery("SELECT * FROM "
				+ CriaPerfilBD.TABELA + " WHERE " + CriaPerfilBD.ID +  " = " + id, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		if(id!=0){
			perfil = new Perfil(id, 
					cursor.getString(cursor.getColumnIndex("NOME")), 
					cursor.getInt(cursor.getColumnIndex("VOLUME")), 
					cursor.getInt(cursor.getColumnIndex("VIBRAR")) == 1 ? true : false, 
					cursor.getInt(cursor.getColumnIndex("RECUSAR_CHAMADAS")) == 1 ? true : false, 
					cursor.getInt(cursor.getColumnIndex("RESPONDER_CHAMADAS")) == 1 ? true : false, 
					cursor.getString(cursor.getColumnIndex("MENSAGEM_PADRAO")));
			
			EditText nome = (EditText) findViewById(R.id.nome);
			nome.setText(cursor.getString(cursor.getColumnIndex("NOME")));
			
			volume.setProgress(cursor.getInt(cursor.getColumnIndex("VOLUME")));
			
			CheckBox vibrar = (CheckBox) findViewById(R.id.vibrar);
			vibrar.setChecked(cursor.getInt(cursor.getColumnIndex("VIBRAR")) == 1 ? true : false);
			
			CheckBox recusarChamadas = (CheckBox) findViewById(R.id.recusarChamadas);
			recusarChamadas.setChecked(cursor.getInt(cursor.getColumnIndex("RECUSAR_CHAMADAS")) == 1 ? true : false);
			
			CheckBox responderChamadas = (CheckBox) findViewById(R.id.responderChamadas);
			responderChamadas.setChecked(cursor.getInt(cursor.getColumnIndex("RESPONDER_CHAMADAS")) == 1 ? true : false);
			
			EditText mensagemPadrao = (EditText) findViewById(R.id.mensagemPadrao);
			if(cursor.getString(cursor.getColumnIndex("MENSAGEM_PADRAO")) !=null &&  !cursor.getString(cursor.getColumnIndex("MENSAGEM_PADRAO")).equals(""))
				mensagemPadrao.setText(cursor.getString(cursor.getColumnIndex("MENSAGEM_PADRAO")));
		}
		else{
			perfil = null;
			Button excluir = (Button) findViewById(R.id.excluir);
			excluir.setText("Cancelar");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editperfil, menu);
		return false;
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
		// return super.onOptionsItemSelected(item);
		return false;
	}

	public void salvar(View view) {

		EditText nome = (EditText) findViewById(R.id.nome);
		SeekBar volume = (SeekBar) findViewById(R.id.volume);
		CheckBox vibrar = (CheckBox) findViewById(R.id.vibrar);
		CheckBox recusarChamadas = (CheckBox) findViewById(R.id.recusarChamadas);
		CheckBox responderChamadas = (CheckBox) findViewById(R.id.responderChamadas);
		EditText mensagemPadrao = (EditText) findViewById(R.id.mensagemPadrao);

		if (id == 0) {

			if (mensagemPadrao.getText().toString() == "Mensagem padrão"
					|| mensagemPadrao.getText().toString() == "")
				perfil = new Perfil(0, nome.getText().toString(),
						volume.getProgress(), vibrar.isChecked(),
						recusarChamadas.isChecked(),
						responderChamadas.isChecked(), null);
			else
				perfil = new Perfil(0, nome.getText().toString(),
						volume.getProgress(), vibrar.isChecked(),
						recusarChamadas.isChecked(),
						responderChamadas.isChecked(), mensagemPadrao.getText()
								.toString());

			// dados.listPerfil.add(perfil);
			BDController crud = new BDController(getBaseContext());
			String resultado = crud.inserePerfil(perfil);
			Toast.makeText(getApplicationContext(), resultado,
					Toast.LENGTH_LONG).show();
		} else {
			perfil.nome = nome.getText().toString();
			perfil.volume = volume.getProgress();
			perfil.vibrar = vibrar.isChecked();
			perfil.recursarChamadas = recusarChamadas.isChecked();
			perfil.responderChamadas = responderChamadas.isChecked();
			perfil.mensagemPadrao = mensagemPadrao.getText().toString();

			BDController crud = new BDController(getBaseContext());
			crud.alteraRegistro(perfil);
		}

		Intent i = new Intent(getApplicationContext(), MenuActivity.class);
		i.putExtra("dados", dados);
		startActivity(i);

	}

	public void excluir(View view) {

		if (id != 0) {
			dados.listPerfil.remove(id - 1);

			for (int id = 1; id <= dados.listPerfil.size(); id++) {
				dados.listPerfil.get(id - 1).id = id;
			}
		}

		Intent i = new Intent(getApplicationContext(), MenuActivity.class);
		i.putExtra("dados", dados);
		startActivity(i);

	}
}
