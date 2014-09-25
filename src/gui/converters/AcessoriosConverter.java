package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import classesBasicas.AcessorioCarro;
import fachada.Fachada;

@FacesConverter(value="AcessoriosConverter",forClass=AcessorioCarro.class)
public class AcessoriosConverter implements Converter {
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
		 AcessorioCarro acessorio = null;
			if (value == null)
				return null;
			Integer codigo = new Integer(value);
			if (codigo == -1)
				return null;
				acessorio = Fachada.obterInstancia().pesquisarAcessorioCodigo(codigo);
			return acessorio; 
		}

  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
    	if (value == null) 
	           return null;  
	    if (value instanceof AcessorioCarro) {  
	        AcessorioCarro acessorio = (AcessorioCarro)value; 
	        if (acessorio.getCodigo() != null)
	        	return acessorio.getCodigo().toString();  
	    }  
	    else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
    }
}