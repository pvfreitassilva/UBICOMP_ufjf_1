package br.ufjf.ubicomp01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;

public class BDController {
	private SQLiteDatabase db;
	private CriaBD banco;

	public BDController(Context context) {
		banco = new CriaBD(context);
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
 
        db.update(banco.TABELA_LOCAL,valores,where,null);
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
	
	
}
