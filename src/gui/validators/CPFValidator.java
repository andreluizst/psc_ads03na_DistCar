package gui.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import util.CpfCnpj;

public class CPFValidator implements Validator {

	@Override
	public void validate(FacesContext contexto, UIComponent uiComponent, Object valor)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String cpf = (String)valor;
		cpf = cpf.replace(".", "").replace("-", "");
		if (cpf.length() != 11 || !CpfCnpj.eValido(cpf))
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "CPF inválido!"));
	}

}
