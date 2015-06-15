package br.ufjf.ubicomp01;

public class Perfil {
	
	public String nome;
	public int id;
	public int volume;
	public boolean vibrar;
	public boolean recursarChamadas;
	public boolean responderChamadas;
	public String mensagemPadrao;
	
	public Perfil(int id, String nome, int volume, boolean vibrar, boolean recusarChamadas, boolean responderChamadas, String mensagemPadrao){
		
		this.nome = nome;
		this.id = id;
		this.volume = volume;
		this.vibrar = vibrar;
		this.recursarChamadas = recusarChamadas;
		this.responderChamadas = responderChamadas;
		this.mensagemPadrao = mensagemPadrao;
		
		
	}
	
	@Override 
	public String toString(){
		return id + "," + nome + "," + volume + "," + vibrar + "," + recursarChamadas + "," + responderChamadas + "," + mensagemPadrao; 
	}
	
}
