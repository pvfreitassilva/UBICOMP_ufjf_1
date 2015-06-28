package br.ufjf.ubicomp01;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListPerfilActivity extends ListActivity {

	private Dados dados;

	private ListView lista;

	private static SQLiteDatabase sqliteDB = null;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_list_bd);
	//
	// BDController crud = new BDController(getBaseContext());
	// final Cursor cursor = crud.carregaPerfis();
	//
	// if (cursor != null) {
	// do {
	// String bookName = cursor.getString(cursor.getColumnIndex("NOME"));
	//
	// } while (cursor.moveToNext());
	//
	// }
	//
	// String[] nomeCampos = new String[] { CriaPerfilBD.ID, CriaPerfilBD.NOME
	// };
	// int[] idViews = new int[] { R.id.idLivro, R.id.nomeLivro };
	//
	// SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
	// getBaseContext(), R.layout.perfil_layout, cursor, nomeCampos,
	// idViews, 0);
	// lista = (ListView) findViewById(R.id.list);
	// lista.setAdapter(adaptador);
	//
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int size = 1;

		int i = 1;

		String[] values = new String[size];

		sqliteDB = this.openOrCreateDatabase(CriaPerfilBD.NOME_BANCO,
				MODE_PRIVATE, null);

		Cursor cursor = sqliteDB.rawQuery("SELECT * FROM "
				+ CriaPerfilBD.TABELA, null);

		if (cursor != null) {

			size = size + cursor.getCount();
			values = new String[size];

			if (cursor.moveToFirst()) {

				do {
					String nome = cursor.getString(cursor
							.getColumnIndex("NOME"));
					values[i] = nome;
					i++;
				} while (cursor.moveToNext());
			}
		}

		values[0] = "< Criar novo >";

		// use your custom layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.activity_menu, R.id.label, values);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		// String item = (String) getListAdapter().getItem(position);

		Intent i = new Intent(getApplicationContext(), EditPerfilActivity.class);
		i.putExtra("dados", dados);
		i.putExtra("ID", position);
		startActivity(i);

		// Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
}
