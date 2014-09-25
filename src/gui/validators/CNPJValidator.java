package gui.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import util.CpfCnpj;

public class CNPJValidator  implements Validator {

	@Override
	public void validate(FacesContext contexto, UIComponent uiComponent, Object valor)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String cnpj = (String)valor;
		cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
		if (cnpj.length() != 14 || !CpfCnpj.eValido(cnpj))
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "CNPJ inválido!"));
	}

}
