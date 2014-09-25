package gui.converters;

import fachada.Fachada;
import gui.MsgPrimeFaces;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import classesBasicas.Cidade;


@FacesConverter(forClass=Cidade.class, value="CidadeConverter")
public class CidadeConverter implements Converter {
	private ResourceBundle rb = ResourceBundle.getBundle("util.idioma_pt_BR");
	
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Cidade obj = null;
		if (valor == null)
			return null;
		Integer codigo = new Integer(valor);
		if (codigo == -1)
			return null;
		try{
			obj = Fachada.obterInstancia().pegarCidadePorId(codigo);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(rb.getString("msgErroConverterTitulo"), 
					MessageFormat.format(rb.getString("msgErroConverter"), "Cidade")
			);
		}
		return obj; 
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object objArg) {
		if (objArg == null) 
	           return null;  
	    if (objArg instanceof Cidade) {  
	        Cidade obj = (Cidade)objArg; 
	        if (obj.getCodigo() != null)
	        	return obj.getCodigo().toString();  
	    }  
	    else if(objArg instanceof String && ((String)objArg).equalsIgnoreCase("-1")){  
	        return "-1";  
	    }
		return null;
	}

}
