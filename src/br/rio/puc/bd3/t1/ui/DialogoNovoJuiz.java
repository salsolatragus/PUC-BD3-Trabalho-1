package br.rio.puc.bd3.t1.ui;

import br.rio.puc.bd3.t1.model.Participacao;
import br.rio.puc.bd3.t1.ui.ADialogo.Caracteristica;

public class DialogoNovoJuiz extends ADialogo
{
		
 //  public static final int BATERIA = 1;

	 public static final int COMPETIDOR = 1 ; 

	 public static final int DESCLASSIFICADO = 2 ;
	 
	 public static final int TEMPO = 3 ;
	
	 public DialogoNovoJuiz()
	 {
		 super( "Entre com os resultados da competição" ,
				 
		 new Caracteristica[] 
		 {
				 
    	//  new Caracteristica( BATERIA         , "Bateria"         , TIPO.INT ) ,
		  new Caracteristica( COMPETIDOR      , "Competidor"      , TIPO.INT ) ,
		  new Caracteristica( DESCLASSIFICADO , "Desclassificado" , TIPO.INT ) ,
		  new Caracteristica( TEMPO           , "Tempo"           , TIPO.INT ) 
	
		 } ) ;
		
	 }
	 
	 
	 public Participacao getParticipacao()
	 {
		// Integer bateria   = getValorInt( BATERIA ) ;     
		 int competidor      = getValorInt( COMPETIDOR ) ;
		 int desclassificado = getValorInt( DESCLASSIFICADO ) ;
		 int tempo           = getValorInt( TEMPO ) ;
		 
		 
		 // return new Participacao( bateria , competidor , desclassificado , tempo )	;
		 
		 return new Participacao( competidor , desclassificado , tempo );  
		
		 
	 }

	 
}
