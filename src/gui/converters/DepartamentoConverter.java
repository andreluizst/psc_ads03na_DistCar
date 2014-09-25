package gui.converters;

import fachada.Fachada;
import gui.MsgPrimeFaces;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import classesBasicas.Departamento;



public class DepartamentoConverter implements Converter {
	private ResourceBundle rb = ResourceBundle.getBundle("util.idioma_pt_BR");

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Departamento obj = null;
		if (valor == null)
			return null;
		Integer codigo = new Integer(valor);
		if (codigo == -1)
			return null;
		try{
			obj = Fachada.obterInstancia().pegarDepartamentoPorId(codigo);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(rb.getString("msgErroConverterTitulo"), 
					MessageFormat.format(rb.getString("msgErroConverter"), "Departamento")
			);
		}
		return obj; 
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object objArg) {
		if (objArg == null) 
	           return null;  
	    if (objArg instanceof Departamento) {  
	    	Departamento obj = (Departamento)objArg;
	    	if (obj.getCodigo() != null)
	    		return obj.getCodigo().toString();  
	    }  
	    else if(objArg instanceof String && ((String)objArg).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
	}
}
