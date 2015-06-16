package br.ufjf.ubicomp01;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends ListActivity {
	
	private ArrayList<Perfil> listPerfil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    String[] values = new String[] { "Perfis", "Meus locais", "Marcar local atual" };
	    // use your custom layout
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        R.layout.activity_menu, R.id.label, values);
	    setListAdapter(adapter);
	    
	    Intent i = getIntent();
	    listPerfil = (ArrayList<Perfil>) i.getSerializableExtra("LISTPERFIL");
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    //String item = (String) getListAdapter().getItem(position);
		
		switch(position){
		
		case 0 : {
			Intent i = new Intent(getApplicationContext(), ListPerfilActivity.class);
			i.putExtra("LISTPERFIL", listPerfil);
			startActivity(i);
			break;
		}
		case 1 : {
//			Intent i = new Intent(getApplicationContext(), MenuActivity.class);
//			startActivity(i);
			break;
		}
		case 2 : {
			Intent i = new Intent(getApplicationContext(), MarcarLocalActivity.class);
			i.putExtra("LISTPERFIL", listPerfil);
			startActivity(i);
			break;
		}
		
		
		}
		
	    //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }
}
