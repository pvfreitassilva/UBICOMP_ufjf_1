package br.ufjf.ubicomp01;

import java.io.Serializable;

import android.location.Location;

public class Local implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600496918702007198L;
	
	public int id;
	public String nome;
	public int id_perfil;
	//public int id_perfilPadrao;
	Location local;
	int raio;
	boolean ativo;
	
	public Local(int id, String nome, int id_perfil, Location local, int raio){
		this.id = id;
		this.nome = nome; 
		this.id_perfil = id_perfil;
		//this.perfilPadrao = perfilPadrao;
		this.local = local;
		this.raio = raio;
		this.ativo = false;
	}
	
	
}
