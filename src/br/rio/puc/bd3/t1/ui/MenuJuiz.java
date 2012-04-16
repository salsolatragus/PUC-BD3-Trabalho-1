package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;

import br.rio.puc.bd3.t1.dao.Conexao_DB;
import br.rio.puc.bd3.t1.model.Participacao;

public class MenuJuiz extends AMenu {

	public static final int RESULTADOS = 1 ;
	
	public static final int FECHA = 2 ;
	
	
    private final Conexao_DB con ;
	
    public MenuJuiz() 
    {
         super("Modo de exibição dos resultados",  new Opcao[] 
				{
		     	  new Opcao( RESULTADOS , "Inserir resultados" ) ,
		     	  
		     	 new Opcao( FECHA , "Fechar Bateria" )
			
		        } );
		
		con = new Conexao_DB() ;				
    }

    @Override
    protected void opcaoSelectionado( int id ) 
    {
    	switch( id )
    	{
    	 case RESULTADOS : criaResultados() ;
    	 break;
    	 
    	 case FECHA : fechaBateria() ;
    	 break;
    	
    	}
	
    }
    
   private void criaResultados()
    {
	   DialogoNovoJuiz cat = new DialogoNovoJuiz();
	   cat.show() ;
	
	   try 
	   {
		 con.insereParticipacao2( cat.getParticipacao() ) ; 
	   }
	   
	   catch (SQLException sqle){
		   sqle.printStackTrace(System.err) ;
	   }
	   
    }
   
   
   
   private void fechaBateria()
   { 
	  try {
		  new MenuSeleccaoCompetidor( con.getCompetidores() ) {
			
			@Override
			protected void opcaoSelectionado(final int competidorId) {
				
				 try {
					 
					 System.out.println();
					 System.out.println( competidorId  );
					 con.fechaBateria( new Participacao( 0 , competidorId ));
					
					System.out.println( competidorId );
					System.out.println("fim");
					return;  
					
				} 
				 catch (SQLException sqle) {
					 sqle.printStackTrace(System.err);

				}
				
			}
		}.show() ;
		
	} catch (Exception sqle) {
		 sqle.printStackTrace(System.err);
      }
	  

  }  
     
	   
	   
}
   
   


/*
 * 
 * 		try {
		    (new MenuSeleccaoCompetidor(con.getCompetidores()) {
			@Override
			
			protected void opcaoSelectionado(final int competidorId) {	
				
				try {
					    con.fechaBateria( new Participacao( 0 , competidorId));
					} 
				
				catch (SQLException sqle) 
				{
					    sqle.printStackTrace(System.err);
				}
	
			  }).show();
		    }
			    
		        catch (SQLException sqle)
		        {
				 sqle.printStackTrace(System.err);
			    }
			
		    }				
		catch (SQLException sqle) 
		{
		    sqle.printStackTrace(System.err);
		}
    }

    @Override
    protected void finalize() throws SQLException {
	if (con != null) {
	    con.close();
	}
 * 
 * */
   
   
