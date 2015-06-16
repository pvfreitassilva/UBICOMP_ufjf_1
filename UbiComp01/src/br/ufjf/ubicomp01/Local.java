package br.ufjf.ubicomp01;

import java.io.Serializable;

import android.location.Location;

public class Local implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600496918702007198L;
	
	public String nome;
	public Perfil perfil;
	public Perfil perfilPadrao;
	Location local;
	int raio;
	
	public Local(String nome, Perfil perfil, Perfil perfilPadrao, Location local, int raio){
		this.nome = nome; 
		this.perfil = perfil;
		this.perfilPadrao = perfilPadrao;
		this.local = local;
		this.raio = raio;
	}
	
	
}
