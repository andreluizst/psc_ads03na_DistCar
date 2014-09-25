package util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class TextoComMascara {
	public static String execute(String string, String mascara) throws ParseException {
	    MaskFormatter maskFormater = new MaskFormatter(mascara);
	    maskFormater.setValueContainsLiteralCharacters(false);
	    return maskFormater.valueToString(string);
	}
	
}
