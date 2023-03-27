package io.rjdev.booster.util.num;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DoubleUtil {

	/**
	 * Method that moves the comma/period (decimal pointer) of a number
     * returning the same as a double. The method does not add
     * decimal places, just move the decimal identifier.
	 * <p>
	 * 		EX:
	 * 			<br>
	 * 			<code>moveIdentificadorDecimal(12345,2,"###.##");</code>
	 * 			<br>
	 * 			Result: 123.45
	 * 			<br>
	 * 			<code>moveIdentificadorDecimal(12345,2,"00000.00");</code>
	 * 			<br>
	 * 			Result: 00123.45
	 * </p>
	 *
	 * @see DecimalFormat;
	 * @author Ronaldo Campos de Oliveira
	 * @since 11/12/2007
	 * @param double number to convert
 	 * @param positions short with the number of positions the identifier must be moved
 	 * @param pattern Pattern (shape) of the return value
	 * @return double in the format defined in the <code>pattern</code>
	 */
	public static double moveIdentificadorDecimal(double numero, short posicoes, String pattern){
		double valor = 0;
		DecimalFormat nf = new DecimalFormat();
		pattern = (pattern == null) ? "000000000000.00" : pattern;
        nf.applyPattern(pattern);

        valor = (numero/Math.pow(10, posicoes));

		return Double.parseDouble(nf.format(valor).replace(",", "."));
	}

	/**
	 * Method that moves the comma/period (decimal pointer) of a number
	 * returning the same as a double. The method does not add
	 * decimal places, just move the decimal identifier.
	 * <p>
	 * 		EX:
	 * 			<br>
	 * 			<code>moveIdentificadorDecimal(12345,2);</code>
	 * 			<br>
	 * 			Result: 0000000123.45
	 * 			<br>
	 * </p>
	 *
	 * @see DecimalFormat
	 * @author Ronaldo Campos de Oliveira
	 * @since 11/12/2007
	 * @param double number to convert
 	 * @param positions short with the number of positions to be
	 * moved the identifier
	 * @return double in standard format 000000000000.00
	 */
	public static double moveIdentificadorDecimal(double numero, short posicoes){
		return moveIdentificadorDecimal(numero, posicoes, null);
	}

	/**
	 * Method that converts a Double into a string following the
	* decimal pattern that is entered. If an exception occurs then it returns ""
	 * <br>
	 * Eg: formatToString(123.4, "#,00") => 123,40 pt_BR
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 26/03/2008
	 * @param Double number that will be converted to the formatted string
	 * @param pattern Pattern that the formatted string should follow
	 *
	 * @see DecimalFormat
	 * @see DecimalFormatSymbols
	 * @return
	 */
	public static String formatToString(Double numero, String pattern){
		String retorno = "";
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(
			new Locale("pt", "BR")
		);
		DecimalFormat nf = new DecimalFormat();
		try{
			nf.applyPattern(pattern);
			nf.setDecimalFormatSymbols(symbols);
			retorno = nf.format(numero);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}

		return retorno;
	}

	/**
	 * Method that converts a Double into a string following the
	 * decimal pattern "#,00" pt_BR. If an exception occurs then it returns ""
	 * <br>
	 * Eg: formatToString(123.4) => 123.40
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 26/03/2008
	 * @param Double number that will be converted to the formatted string
	 * @return String with the number formatted in the pattern "#,00"
	 *
	 * @see DecimalFormat
	 */
	public static String doubleToString(Double numero){
		return formatToString(numero, "#.00");
	}
}

