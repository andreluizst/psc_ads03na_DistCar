package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import classesBasicas.ModeloCarro;
import fachada.Fachada;

@FacesConverter(value="ModeloCarroConverter",forClass=ModeloCarro.class)
public class ModeloCarroConverter implements Converter {
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
		 ModeloCarro modelo = null;
			if (value == null)
				return null;
			Integer codigo = new Integer(value);
			if (codigo == -1)
				return null;
				modelo = Fachada.obterInstancia().pesquisarModelosCarroCodigo(codigo);
			return modelo; 
		}
  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
    	if (value == null) 
	           return null;  
	    if (value instanceof ModeloCarro) {  
	        ModeloCarro modelo = (ModeloCarro)value; 
	        if (modelo.getCodigo() != null)
	        	return modelo.getCodigo().toString();  
	    }  
	    else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
    }
}