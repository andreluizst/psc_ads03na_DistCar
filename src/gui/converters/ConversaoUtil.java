package gui.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConversaoUtil {

	public static Date criarData(int dia, int mes, int ano){
		return new GregorianCalendar(ano, mes-1, dia).getTime();
	}
	
	/**
	 * Converter data do formato dd-mm-yyyy para date
	 * @param data
	 * @return
	 */
	public static Date criarData(String data){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return df.parse(data);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Date d1 = ConversaoUtil.criarData("32-03-2014");
		System.out.println(d1);
		Date d2 = ConversaoUtil.criarData(32, 3, 2014);
		System.out.println(d2);

	}
}
