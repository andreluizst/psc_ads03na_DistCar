package gui.converters;

import fachada.Fachada;
import gui.MsgPrimeFaces;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import classesBasicas.Centro;


public class CentroConverter implements Converter {
	private ResourceBundle rb = ResourceBundle.getBundle("util.idioma_pt_BR");

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Centro obj = null;
		if (valor == null)
			return null;
		Integer codigo = new Integer(valor);
		if (codigo == -1)
			return null;
		try{
			obj = Fachada.obterInstancia().pegarCentroPorId(codigo);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(rb.getString("msgErroConverterTitulo"), 
					MessageFormat.format(rb.getString("msgErroConverter"), "Centro")
			);
		}
		return obj; 
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object objArg) {
		if (objArg == null) 
	           return null;  
	    if (objArg instanceof Centro) {  
	    	Centro obj = (Centro)objArg;
	    	if (obj.getCodigo() != null)
	    		return obj.getCodigo().toString();  
	    }  
	    else if(objArg instanceof String && ((String)objArg).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
	}
}
