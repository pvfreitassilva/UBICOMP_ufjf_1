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

	private Dados dados;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		dados = (Dados) intent.getSerializableExtra("dados");		
		
		int size = dados.listPerfil.size() + 1;
		
	    String[] values = new String[size];
	    
	    values[0] = "< Criar novo >";
	    
	    for(int i = 1; i< size; i++){
	    	values[i] = dados.listPerfil.get(i-1).nome;
	    }	    
	    
	    // use your custom layout
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        R.layout.activity_menu, R.id.label, values);
	    setListAdapter(adapter);
	}
	
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    //String item = (String) getListAdapter().getItem(position);
		
		Intent i = new Intent(getApplicationContext(), EditPerfilActivity.class);
		i.putExtra("dados", dados);
		i.putExtra("ID", position);
		startActivity(i);
	
		
	    //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }
}
