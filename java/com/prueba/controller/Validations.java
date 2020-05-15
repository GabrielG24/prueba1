package com.prueba.controller;

public class Validations {
	
	 boolean isValidRut(String rut)
	{
	    boolean ret = false;
	    if(rut != null && rut.trim().length() > 0)
	    {
	        try {
	            rut = rut.replaceAll("[.]", "").replaceAll("-", "").trim().toUpperCase();
	            char dv = rut.charAt(rut.length() - 1);
	            String mantisa = rut.substring(0, rut.length() - 1);
	            if( isInteger( mantisa ) )
	            {
	                int mantisaInt = Integer.parseInt( mantisa );
	                ret = validarRut( mantisaInt, dv ) ;
	            }
	        }
	        catch (Throwable e) 
	        {
	            //error("[isValidRut] ["+rut+"]", e);
	        }
	    }
	    return ret;
	}

	private boolean validarRut(int rut, char dv)
	{
	    int m = 0, s = 1;
	    for (; rut != 0; rut /= 10)
	    {
	        s = (s + rut % 10 * (9 - m++ % 6)) % 11;

	    }
	    return Character.toUpperCase(dv) == (char) (s != 0 ? s + 47 : 75) ;
	}

	public boolean isInteger(String cad)
	{
	    for(int i = 0; i<cad.length(); i++){
	        if( !Character.isDigit(cad.charAt(i)) )
	        {
	            return false;
	        }
	    }
	    return true;

	}	
	
}
