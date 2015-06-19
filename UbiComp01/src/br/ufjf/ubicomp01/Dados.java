package br.ufjf.ubicomp01;

import java.io.Serializable;
import java.util.ArrayList;

public class Dados implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7276664931286538299L;
	public ArrayList<Perfil> listPerfil;
	public ArrayList<Local> listLocal;
	public Local localAtual;
	public Perfil perfilAtual;
	
	public Dados(){
		buildListPerfil();
		buildListLocal();
		localAtual = null;
		perfilAtual = listPerfil.get(0);
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
				null));
		
		listPerfil.add(new Perfil(
				2,
				"Silencioso sem vibração",
				0,
				false,
				false,
				false,
				null));	
		
		listPerfil.add(new Perfil(
				3,
				"Ocupado",
				0,
				false,
				false,
				true,
				"Estou ocupado"));
		
		listPerfil.add(new Perfil(
				4,
				"Geral",
				100,
				true,
				false,
				false,
				null));	
		
	}
    
    private void buildListLocal(){
    	listLocal = new ArrayList<Local>();
    }
	
	
}
