package br.ufjf.ubicomp01;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

	private int id;
	private Perfil perfil;
    //private Dados dados;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.editperfil);

		SeekBar volume = (SeekBar) findViewById(R.id.volume);
		volume.setMax(100);

		Intent intent = getIntent();

		//dados = (Dados) intent.getSerializableExtra("dados");

		id = intent.getIntExtra("ID", -1);

		if (id != 0) {
			BDController crud = new BDController(getBaseContext());
			cursor = crud.getPerfilById(id);

			if (cursor != null) {
				cursor.moveToFirst();

				perfil = new Perfil(
						id,
						cursor.getString(cursor
								.getColumnIndex(CriaPerfilBD.NOME)),
						cursor.getInt(cursor
								.getColumnIndex(CriaPerfilBD.VOLUME)),
						cursor.getInt(cursor
								.getColumnIndex(CriaPerfilBD.VIBRAR)) == 1 ? true
								: false,
						cursor.getInt(cursor
								.getColumnIndex(CriaPerfilBD.RECUSAR_CHAMADAS)) == 1 ? true
								: false,
						cursor.getInt(cursor
								.getColumnIndex(CriaPerfilBD.RESPONDER_CHAMADAS)) == 1 ? true
								: false, cursor.getString(cursor
								.getColumnIndex(CriaPerfilBD.MENSAGEM_PADRAO)));
			}

			EditText nome = (EditText) findViewById(R.id.nome);
			nome.setText(perfil.nome);

			volume.setProgress(perfil.volume);

			CheckBox vibrar = (CheckBox) findViewById(R.id.vibrar);
			vibrar.setChecked(perfil.vibrar);

			CheckBox recusarChamadas = (CheckBox) findViewById(R.id.recusarChamadas);
			recusarChamadas.setChecked(perfil.recursarChamadas);

			CheckBox responderChamadas = (CheckBox) findViewById(R.id.responderChamadas);
			responderChamadas.setChecked(perfil.responderChamadas);

			EditText mensagemPadrao = (EditText) findViewById(R.id.mensagemPadrao);
			if (perfil.mensagemPadrao != null
					&& !perfil.mensagemPadrao.equals(""))
				mensagemPadrao.setText(perfil.mensagemPadrao);
		} else {
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

			BDController crud = new BDController(getBaseContext());
			String resultado = crud.inserePerfil(perfil);
			Toast.makeText(getBaseContext(),resultado,
					Toast.LENGTH_SHORT).show();

		} else {
			perfil.nome = nome.getText().toString();
			perfil.volume = volume.getProgress();
			perfil.vibrar = vibrar.isChecked();
			perfil.recursarChamadas = recusarChamadas.isChecked();
			perfil.responderChamadas = responderChamadas.isChecked();
			perfil.mensagemPadrao = mensagemPadrao.getText().toString();

			BDController crud = new BDController(getBaseContext());
			crud.alteraPerfil(perfil);
			Toast.makeText(getBaseContext(),"Perfil alterado",
					Toast.LENGTH_SHORT).show();

		}

		Intent i = new Intent(getApplicationContext(), MenuActivity.class);
		startActivity(i);

	}

	public void excluir(View view) {

		if (id != 0) {
			BDController crud = new BDController(getBaseContext());
			crud.deletaPerfil(perfil);
		}
		
		Toast.makeText(getBaseContext(),"Perfil excluido",
				Toast.LENGTH_SHORT).show();


		Intent i = new Intent(getApplicationContext(), MenuActivity.class);
		startActivity(i);

	}
}
