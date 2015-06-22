package br.ufjf.ubicomp01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaPerfilBD extends SQLiteOpenHelper {
	
	protected static final String NOME_BANCO = "banco.db";
    protected static final String TABELA = "PERFIL";
    protected static final String ID = "ID";
    protected static final String NOME = "NOME";
    protected static final String VOLUME = "VOLUME";
    protected static final String VIBRAR = "VIBRAR";
    protected static final String RECUSAR_CHAMADAS = "RECUSAR_CHAMADAS";
    protected static final String RESPONDER_CHAMADAS = "RESPONDER_CHAMADAS";
    protected static final String MENSAGEM_PADRAO = "MENSAGEM_PADRAO";
    protected static final int VERSAO = 1;

    public CriaPerfilBD(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + VOLUME + " integer,"
                + VIBRAR + " integer,"
                + RECUSAR_CHAMADAS + " integer,"
                + RESPONDER_CHAMADAS + " integer,"
                + MENSAGEM_PADRAO + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

}
