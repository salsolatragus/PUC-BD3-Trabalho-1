package br.rio.puc.bd3.t1.model;

public class Participacao {

 
	private int bateria         ;	   
	private int categoria       ;    
	private int competidor      ;   
	private int desclassificado ; 
	private int tempo           ; 

    

    public Participacao(int categoria, int competidor) {
	this.categoria  = categoria;
	this.competidor = competidor;
    }

    /*public Participacao( int bateria , int competidor , int desclassificado , int tempo )
    {
    	this.bateria         = bateria         ;
    	this.competidor      = competidor      ;
    	this.desclassificado = desclassificado ;
    	this.tempo           = tempo           ;
    	
    }
    */
    public Participacao( int competidor , int desclassificado , int tempo )
    {
       this.competidor      = competidor      ;	
	   this.desclassificado = desclassificado ;
       this.tempo           = tempo           ;

    }
    
    public int getCategoria() {
	return categoria ;
    }

    public int getCompetidor() {
	return competidor ;
    }
    
    public int getBateria() {
    	return bateria ;
        }
    public int getDesclassificado() {
    	return desclassificado ;
        }
    
    public int getTempo() {
    	return tempo ;
   	}
    
    
    
}
