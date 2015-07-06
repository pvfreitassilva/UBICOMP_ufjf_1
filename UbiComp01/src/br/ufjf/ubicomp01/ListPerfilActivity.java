package br.ufjf.ubicomp01;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListPerfilActivity extends ListActivity {

	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int size = 1;

		int i = 1;

		String[] values = new String[size];

		BDController crud = new BDController(getBaseContext());

		cursor = crud.listaTodosPerfis();

		if (cursor != null) {

			size = size + cursor.getCount();
			values = new String[size];

			if (cursor.moveToFirst()) {

				do {
					String nome = cursor.getString(cursor
							.getColumnIndex(CriaBD.NOME));
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
		Intent i = new Intent(getApplicationContext(), EditPerfilActivity.class);
		if (position == 0) {
			i.putExtra("ID", 0);
			startActivity(i);
		} else {
			int codigo;
			cursor.moveToPosition(position - 1);
			codigo = cursor.getInt(cursor
					.getColumnIndexOrThrow(CriaBD.ID));
			
			i.putExtra("ID", codigo);
			startActivity(i);
		}

		// Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
}
