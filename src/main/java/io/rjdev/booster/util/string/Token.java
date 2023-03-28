package io.rjdev.booster.util.string;

import java.util.StringTokenizer;

/**
 * Class with useful methods working with tokens
 * String
 *
 * @author Ronaldo Campos de Oliveira
 * @author $Author: ec2-user $
 * @version $Revision: 1.1.1.1 $
 * @since 24/10/2007
 * @since $Date: 2018/10/05 17:10:38 $
 * @since 1.1
 */
public class Token {

	/**
	 * Method that separates a string by a separator and returns
	 * each separate string in an array.
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 24/10/2007
	 * @param String expression to be separated
	 * @param String tab where the string will be broken
	 * @return String[] with the strings generated after the break
	 * @see StringTokenizer
	 */
	public static String[] separate(String expressao, String separador){
		// Separates the string passed by the entered separator
		StringTokenizer st = new StringTokenizer(expressao, separador);
		// Creates the return array with the size equal to the number of generated tokens
		String[] tokens = new String[st.countTokens()];
		int i = 0;
		while(st.hasMoreTokens()){
			tokens[i] = st.nextToken();
			i++;
		}
		// Returns the array with the separate strings
		return tokens;
	}
}
