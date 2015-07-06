package br.ufjf.ubicomp01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;

public class BDController {
	private SQLiteDatabase db;
	private CriaPerfilBD banco;

	public BDController(Context context) {
		banco = new CriaPerfilBD(context);
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

		resultado = db.insertOrThrow(banco.TABELA, null, valores);
		db.close();

		if (resultado == -1)
			return "Erro ao inserir registro";
		else
			return "Perfil inserido com sucesso";
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
 
        db.update(banco.TABELA,valores,where,null);
        db.close();
    }
	
	public void deletaPerfil(Perfil p){
        
        String where;
 
        db = banco.getReadableDatabase();
 
        where = banco.ID + " = " + p.id;
 
        db.delete(banco.TABELA,where,null);
        db.close();
    }
	
	public Cursor listaTodosPerfis(){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaPerfilBD.TABELA, null);

		if (cursor != null) {
			return cursor;
		}
		
		db.close();
		return null;
	}
	
	public Cursor getPerfilById(int id){
		db = banco.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + CriaPerfilBD.TABELA
				+ " WHERE " + CriaPerfilBD.ID + " = " + id, null);

		if (cursor != null) {
			
			return cursor;
		}
		
		db.close();
		return null;
	}
}
