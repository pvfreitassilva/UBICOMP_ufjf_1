package br.ufjf.ubicomp01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {
	
	protected static final String NOME_BANCO = "banco.db";
	
    protected static final String TABELA_PERFIL = "PERFIL";
    protected static final String ID = "ID";
    protected static final String NOME = "NOME";
    protected static final String VOLUME = "VOLUME";
    protected static final String VIBRAR = "VIBRAR";
    protected static final String RECUSAR_CHAMADAS = "RECUSAR_CHAMADAS";
    protected static final String RESPONDER_CHAMADAS = "RESPONDER_CHAMADAS";
    protected static final String MENSAGEM_PADRAO = "MENSAGEM_PADRAO";
    
    protected static final String TABELA_LOCAL = "LOCAL";
    protected static final String LATITUDE = "LATITUDE";
    protected static final String LONGITUDE = "LONGITUDE";
    protected static final String ID_PERFIL = "ID_PERFIL";
    protected static final String RAIO = "RAIO";
    protected static final String ATIVO = "ATIVO";
    
    protected static final String TABELA_LOCAL_ATUAL = "LOCAL_ATUAL";
    protected static final String ID_LOCAL_ATUAL = "ID_LOCAL_ATUAL";
    
    protected static final int VERSAO = 7;

    public CriaBD(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA_PERFIL+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + VOLUME + " integer,"
                + VIBRAR + " integer,"
                + RECUSAR_CHAMADAS + " integer,"
                + RESPONDER_CHAMADAS + " integer,"
                + MENSAGEM_PADRAO + " text"
                +")";
        db.execSQL(sql);
        
        sql = "CREATE TABLE "+TABELA_LOCAL+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + LATITUDE + " text,"
                + LONGITUDE + " text,"
                + RAIO + " integer,"
                + ID_PERFIL + " integer,"
                + ATIVO + " integer"
                +")";
        db.execSQL(sql);
        
        sql = "CREATE TABLE "+TABELA_LOCAL_ATUAL+"("
                + ID + " integer primary key autoincrement,"
                + ID_LOCAL_ATUAL + " integer"
                +")";
        db.execSQL(sql);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PERFIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LOCAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LOCAL_ATUAL);
        onCreate(db);
    }

}

