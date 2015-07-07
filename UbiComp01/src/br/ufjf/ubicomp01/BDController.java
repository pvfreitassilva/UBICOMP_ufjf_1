package br.ufjf.ubicomp01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

public class BDController {
	private SQLiteDatabase db;
	private CriaBD banco;

	public BDController(Context context) {
		banco = new CriaBD(context);
		
		db = banco.getWritableDatabase();
		ContentValues valores  = new ContentValues();
		valores.put(banco.ID_LOCAL_ATUAL, -1);
		
		if(db.update(banco.TABELA_LOCAL_ATUAL, valores, null, null)==0 )
			db.insertOrThrow(banco.TABELA_LOCAL_ATUAL, null, valores);
		db.close();
		
	}

	public String inserePerfil(Perfil p) {
		ContentValues valores;
		long resultado;

		db = banco.getWritableDatabase();

		valores = new ContentValues();
		valores.put(banco.NOME, p.nome);
		valores.put(banco.VOLUME, p.volume);
		valores.put(banco.VIBRAR, p.vibrar ? 1 : 0);
		valores.put(banco.RECUSAR_CHAMADAS, p.recursarChamadas ? 1 : 0);
		valores.put(banco.RESPONDER_CHAMADAS, p.responderChamadas ? 1 : 0);
		valores.put(banco.MENSAGEM_PADRAO, p.mensagemPadrao);

		resultado = db.insertOrThrow(banco.TABELA_PERFIL, null, valores);
		db.close();

		if (resultado == -1)
			return "Erro ao inserir registro";
		else
			return "Perfil Inserido com sucesso";
	}
	
	public void alteraPerfil(Perfil p){
        ContentValues valores = new ContentValues();
        String where;
 
        db = banco.getWritableDatabase();
 
        where = banco.ID + " = " + p.id;
 
        valores.put(banco.NOME, p.nome);
		valores.put(banco.VOLUME, p.volume);
		valores.put(banco.VIBRAR, p.vibrar ? 1 : 0);
		valores.put(banco.RECUSAR_CHAMADAS, p.recursarChamadas ? 1 : 0);
		valores.put(banco.RESPONDER_CHAMADAS, p.responderChamadas ? 1 : 0);
		valores.put(banco.MENSAGEM_PADRAO, p.mensagemPadrao);
 
        db.update(banco.TABELA_PERFIL,valores,where,null);
        db.close();
    }
	
	public void deletaPerfil(Perfil p){
        
        String where;
 
        db = banco.getReadableDatabase();
 
        where = banco.ID + " = " + p.id;
 
        db.delete(banco.TABELA_PERFIL,where,null);
        db.close();
    }
	
	public Cursor listaTodosPerfis(){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_PERFIL, null);

		if (cursor != null) {
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	public Cursor getPerfilById(int id){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_PERFIL
				+ " WHERE " + CriaBD.ID + " = " + id, null);

		if (cursor != null) {
			
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	public Cursor getPerfilByName(String name){
		db = banco.getReadableDatabase();

		if(name==null)
			return null;
		
		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_PERFIL
				+ " WHERE " + CriaBD.NOME + " LIKE '" + name + "'", null);

		if (cursor != null) {
			
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	
	
	
	
	
	public String insereLocal(Local l) {
		ContentValues valores;
		long resultado;

		db = banco.getWritableDatabase();

		valores = new ContentValues();
		valores.put(banco.NOME, l.nome);
		valores.put(banco.LATITUDE, new Double(l.local.getLatitude()).toString());
		valores.put(banco.LONGITUDE, new Double(l.local.getLongitude()).toString());
		valores.put(banco.ID_PERFIL, l.id_perfil);
		valores.put(banco.RAIO, l.raio);
		valores.put(banco.ATIVO, l.ativo ? 1 : 0);
	
		resultado = db.insertOrThrow(banco.TABELA_LOCAL, null, valores);
		db.close();

		if (resultado == -1)
			return "Erro ao inserir registro";
		else
			return "Local Inserido com sucesso";
	}
	
	public void alteraLocal(Local l){
        ContentValues valores = new ContentValues();
        String where;
 
        db = banco.getWritableDatabase();
 
        where = banco.ID + " = " + l.id;
 
        valores.put(banco.NOME, l.nome);
		valores.put(banco.LATITUDE, new Double(l.local.getLatitude()).toString());
		valores.put(banco.LONGITUDE, new Double(l.local.getLongitude()).toString());
		valores.put(banco.ID_PERFIL, l.id_perfil);
		valores.put(banco.RAIO, l.raio);
		valores.put(banco.ATIVO, l.ativo ? 1 : 0);
 
        db.update(banco.TABELA_LOCAL,valores,where,null);
        db.close();
    }
	
	public void ativarLocal(int id){
        ContentValues valores = new ContentValues();
        String where;
 
        db = banco.getWritableDatabase();
 
		valores.put(banco.ATIVO, 0);
		where = null;
		db.update(banco.TABELA_LOCAL,valores,where,null);
		
		valores = new ContentValues();
		valores.put(banco.ATIVO, 1);
		where = banco.ID + " = " + id;
        db.update(banco.TABELA_LOCAL,valores,where,null);
        
        valores = new ContentValues();
        valores.put(banco.ID_LOCAL_ATUAL, id);
        db.update(banco.TABELA_LOCAL_ATUAL, valores, where, null);
        
        db.close();
    }
	
	public void deletaLocal(Local l){
        
        String where;
 
        db = banco.getReadableDatabase();
 
        where = banco.ID + " = " + l.id;
 
        db.delete(banco.TABELA_LOCAL,where,null);
        db.close();
    }
	
	public Cursor listaTodosLocais(){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL, null);

		if (cursor != null) {
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	public Cursor getLocalById(int id){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL
				+ " WHERE " + CriaBD.ID + " = " + id, null);

		if (cursor != null) {
			
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	public Local getLocalAtual(){
		
		Local localAtual = null;
		
		db = banco.getReadableDatabase();
		
		try{

		//Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL_ATUAL, null);
		
		//if(cursor!=null){

			//if (cursor.moveToFirst()) {
				
				//int id_atual = cursor.getInt(cursor.getColumnIndex(CriaBD.ID_LOCAL_ATUAL));
				
				Cursor c_local = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL + " WHERE " + CriaBD.ATIVO + " = 1 " , null);
				
				if(c_local !=null) {
					
					if (c_local.moveToFirst()) {
						
						int id_atual = c_local.getInt(c_local.getColumnIndex(CriaBD.ID));
						
						Location location = new Location("");
						location.setLatitude(Double.parseDouble(c_local.getString(c_local.getColumnIndex(CriaBD.LATITUDE))));
						location.setLongitude(Double.parseDouble(c_local.getString(c_local.getColumnIndex(CriaBD.LONGITUDE))));
						
						localAtual= new Local(
								id_atual,
								c_local.getString(c_local
										.getColumnIndex(CriaBD.NOME)),
								c_local.getInt(c_local
										.getColumnIndex(CriaBD.ID_PERFIL)),
								location,
										
								c_local.getInt(c_local
										.getColumnIndex(CriaBD.RAIO)));						
					}
				}	
			//}
		//}
		}
		catch(Exception e){
			
		}
		
		db.close();
			
		return localAtual;
	}
	
	public Perfil getPerfilAtual(){
		
		Perfil perfilAtual = null;
		
		db = banco.getReadableDatabase();
		
		try{

		//Cursor cursor = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL_ATUAL, null);
		
		//if(cursor!=null){

			//if (cursor.moveToFirst()) {
				
				//int id_atual = cursor.getInt(cursor.getColumnIndex(CriaBD.ID_LOCAL_ATUAL));
				
				Cursor c_local = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_LOCAL + " WHERE " + CriaBD.ATIVO + " = 1 ", null);
				
				if(c_local !=null) {
					
					if (c_local.moveToFirst()) {
						
						int id_perfil = c_local.getInt(c_local.getColumnIndex(CriaBD.ID_PERFIL));
						
						Cursor c_perfil = db.rawQuery("SELECT * FROM " + CriaBD.TABELA_PERFIL + " WHERE " + CriaBD.ID + " = " + id_perfil , null);
						
						perfilAtual= new Perfil(
								id_perfil,
								c_perfil.getString(c_perfil
										.getColumnIndex(CriaBD.NOME)),
										c_perfil.getInt(c_perfil
										.getColumnIndex(CriaBD.VOLUME)),
										c_perfil.getInt(c_perfil
										.getColumnIndex(CriaBD.VIBRAR)) == 1 ? true
										: false,
										c_perfil.getInt(c_perfil
										.getColumnIndex(CriaBD.RECUSAR_CHAMADAS)) == 1 ? true
										: false,
										c_perfil.getInt(c_perfil
										.getColumnIndex(CriaBD.RESPONDER_CHAMADAS)) == 1 ? true
										: false, c_perfil.getString(c_perfil
										.getColumnIndex(CriaBD.MENSAGEM_PADRAO)));
						
					}
				}	
			//}
		//}
				
		}
		catch(Exception e){
			
			
		}
		
		db.close();
		
		return perfilAtual;
		
	}
	
}
