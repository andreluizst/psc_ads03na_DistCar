package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import classesBasicas.VersaoCarro;
import fachada.Fachada;

@FacesConverter(value="versaoConverter",forClass=VersaoCarro.class)
public class VersaoConverter implements Converter {
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
		 VersaoCarro versao = null;
			if (value == null)
				return null;
			Integer codigo = new Integer(value);
			if (codigo == -1)
				return null;
				versao = Fachada.obterInstancia().pesquisarVersaoCodigo(codigo);
			return versao; 
		}
  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
    	if (value == null) 
	           return null;  
	    if (value instanceof VersaoCarro) {  
	        VersaoCarro versao = (VersaoCarro)value; 
	        if (versao.getCodigo() != null)
	        	return versao.getCodigo().toString();  
	    }  
	    else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
    } 	
}