package br.ufjf.ubicomp01;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListPerfilActivity extends ListActivity {

	private List<Perfil> listPerfil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		buildListPerfil();
		
		int size = listPerfil.size() +1;
		
	    String[] values = new String[size];
	    
	    values[0] = "< Criar novo >";
	    
	    for(int i = 1; i< size; i++){
	    	values[i] = listPerfil.get(i-1).nome;
	    }	    
	    
	    // use your custom layout
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        R.layout.activity_menu, R.id.label, values);
	    setListAdapter(adapter);
	}
	
	private void buildListPerfil(){
		
		listPerfil = new ArrayList<Perfil>();
		
		listPerfil.add(new Perfil(
						1,
						"Silencioso com vibração",
						50,
						true,
						false,
						false,
						" "));
		
		listPerfil.add(new Perfil(
				2,
				"Silencioso sem vibração",
				0,
				false,
				false,
				false,
				" "));	
		
		listPerfil.add(new Perfil(
				3,
				"Ocupado",
				0,
				false,
				false,
				true,
				"Estou ocupado"));	
		
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    //String item = (String) getListAdapter().getItem(position);
		
		Intent i = new Intent(getApplicationContext(), EditperfilActivity.class);
		if(position>0){
			i.putExtra("EDITPERFIL", listPerfil.get(position-1).toString());
			i.putExtra("NOVOPERFIL", false);
		}
		else
			i.putExtra("NOVOPERFIL", true);
		startActivity(i);
	
		
	    //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }
}
