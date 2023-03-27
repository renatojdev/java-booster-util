package io.rjdev.booster.util.string;

import java.util.StringTokenizer;

/**
 * Classe com metodos uteis trabalhando com tokens
 * de String
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
	 * Metodo que separa uma string por um separador e retorna
	 * cada string separada em um array.
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 24/10/2007
	 * @param expressao String a ser separada
	 * @param separador String identificador onde sera quebrada a string
	 * @return String[] com as strings geradas ap�s a quebra
	 * @see StringTokenizer
	 */
	public static String[] separate(String expressao, String separador){
		// Separa a string passada pelo separador informado
		StringTokenizer st = new StringTokenizer(expressao, separador);
		// Cria o array de retorno com o tamanho igual ao numero de tokens gerados
		String[] tokens = new String[st.countTokens()];
		int i = 0;
		while(st.hasMoreTokens()){
			tokens[i] = st.nextToken();
			i++;
		}
		// Retorna o array com as strings separadas
		return tokens;
	}
}
