package io.rjdev.booster.util.string;

public class StringUtil {

    public static String removeCharFromString(char c, String str) {
        if(Character.valueOf(c).equals(null) || str == null)
            throw new IllegalArgumentException("No arguments provided");

        return str.replace(String.valueOf(c), "");
    }

	/**
	 * Method returns a string replacing its
	 * line breaks by blank spaces.
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 24/10/2007
	 * @param string to be formatted
	 * @return String after line break removal
	 */
	public static String removeLineBreak(String string){
		try{
			// String de retorno
			String stringFmt = (string == null) ? "" : string;
			stringFmt = stringFmt.replace("\r\n", " ");
			stringFmt = stringFmt.replace("\n|", " ");
			stringFmt = stringFmt.replace("\r|", " ");
			stringFmt = stringFmt.replace("\n", " ");
			stringFmt = stringFmt.replace("\r", " ");
			return stringFmt;
		}catch(Exception e){
			// If any formatting error occurs returns the initial string
			e.printStackTrace();
			return string;
		}
	}

	/**
	 * Method to check if a String has valid characters. This
	 * method should accept only characters from A to Z, space
	 * and numbers of 0 to 9 (alphanumeric).
	 *
	 * @param string ser verificada.
	 * @return <code> TRUE </code> se a String possuir caracteres validos ou
	 * <code> FALSE </code> em caso contrario.
	 *
	 * @author Renato de Paula Eduardo Jr
	 * @since 05/09/2008
	 */
	public static boolean isAlphanumeric(String string){
		if(string == null)
			return false;
		return string.matches("^[a-zA-Z0-9\\s]+$");
	}

	/**
	 * Method to count the number of Numeric Characters/Digits
	 * in a String.
	 *
	 * @param <code> string String </code> to be checked the quantity
	 * of numeric / digit characters.
	 * @return <code> number </code> of numeric characters/digits in
	 * a string
	 * @author Renato de Paula Eduardo Jr
	 * @since 09/09/2008
	 */
	public static int numericCharsQty(String string){
		int qt=0;

		if(string == null || string.isEmpty())
			return qt;

		for (int i = 0; i < string.length(); i++) {
			boolean check = Character.isDigit(string.charAt(i));
			if (check)
				qt++;
		}

		return qt;
	}

	/**
	 * Method for counting the number of Alphabetic Characters in a String.
	 *
	 * @param string <code> string String </code> String to be checked the quantity
	 * of alphabetic characters.
	 * @return <code> int </code> amount of alphabetic characters in
	 * a String
	 * @author Renato de Paula Eduardo Jr
	 * @since 09/09/2008
	 */
	public static int charactersAlphabeticQty(String string){
		int qt=0;

		if(string == null || string.isEmpty())
			return qt;

		for (int i = 0; i < string.length(); i++) {
			if (new Character(string.charAt(i)).toString().matches("[a-zA-Z]"))
				qt++;
		}

		return qt;
	}

	/**
	 * Method to verify the content of a String according to the quantity
  	 * of numeric and alphabetic characters.
	 *
	 * @param str <code> String </code> String to check
	 * @param qtNumeric <code> int </code> minimum number of characters
	 * required integers in String. -1 equals none.
	 * @param qtAlphabetics <code> int </code> minimum number of characters
	 * Alphabetics required in String. -1 equals none.
	 * @return <code> TRUE </code> if the String matches the
	 * parameters provided to check the amount of characters
	 * numeric and alphabetic or <code> FALSE </code> otherwise.
	 * @author Renato de Paula Eduardo Jr
	 * @since 05/09/2008
	 */
	public static boolean isNumMinimCharsRequired(String str, int qtCaracteres,
			int qtNumericos, int qtAlfabeticos) {

		boolean retNum = false;
		boolean retAlfa = false;

		// Checks if the String contains the minimum number of characters required
		if(str.length() < qtCaracteres)
			return false;
		// Checks the minimum number of required numeric characters
		if(qtNumericos != -1){
			if(numericCharsQty(str)>=qtNumericos)
				retNum = true;
		}else{
			retNum=true;
		}
		// Checks the minimum amount of alphabetic characters required
		if(qtAlfabeticos != -1){
				retAlfa = true;
		}else{
			retAlfa=true;
		}

		return (retNum&&retAlfa);
	}

	/**
	 * Method to replace two blanks with one.
	 *
	 * @since 09/04/2009
	 * @author Gabriel Moreira Marussi
	 */
	public static String removeDuplicateBlankSpace(String string){
		if(string == null)
			return null;
		String aux = string.replaceAll("  "," ");
		return aux;
	}

	/**
	 * Method that returns a String by breaking line (<br/>)
     * Receives as a parameter a String and an integer that represents the number
     * for line break.
	 *
	 * @since 09/04/2009
	 * @author Gabriel Moreira Marussi
	 */
	public static String getStringBreakingLine(String string, int brkLine) {
		int endIndex = brkLine;
		String ret = "", aux = string;
		boolean hasBrkLine = false;
		while (aux.length() > 0) {
			if (aux.length() <= brkLine) {
				endIndex = aux.length();
				hasBrkLine = false;
			} else {
				hasBrkLine = true;
			}
			if (hasBrkLine) {
				ret += aux.substring(0, endIndex);
				aux = aux.substring(endIndex, aux.length());
				if ((aux.indexOf(" ") != -1)) {
					ret += aux.substring(0, aux.indexOf(" "));
					aux = aux.substring(aux.indexOf(" ") + 1, aux.length());
				}
				ret += "<br/>";
			} else {
				ret += aux.substring(0, endIndex);
				aux = aux.substring(endIndex, aux.length());
			}
		}

		return ret;
	}

	/**
	 * Method that returns a String replacing the values with the
	 * parameters received.
	 *
	 * @return String updated with input parameters.
	 * @since 03/09/2009
	 * @author Renato de Paula Eduardo Jr
	 */
	public static String replace(String entrada, String ...strings){
		String saida = "";

		for(int i=0; i<strings.length; i++){
			if(i>0 && i!=strings.length) entrada = saida;
			saida = entrada.replace("{"+i+"}", strings[i]);
		}

		return saida;
	}

	/**
	 * Method to receive an HTML String and return it formatted by changing
	 * \n or \r for <BR/>.
	 * @param s HTML String.
	 * @return Formatted HTML string.
	 */
	public static String getBreakLineHtml(String s){
		return s == null ? null : s.replaceAll("(\r\n|\n)", "<br/>");
	}

	/**
	 * Method to remove a character from a String.
	 * @param s string
	 * @param c char to be removed
	 * @return string updated
	 */
	public static String removeChar(String s, Character c) {
		if(s == null)
			return null;
		if (c == null)
			return s;
		String r = "";
		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) != c) r += s.charAt(i);
		}
		return r;
	}


}
