package io.rjdev.booster.util;

import java.io.Serializable;

/**
 * Classe para manipulacao de Objetos.
 *
 * <p>
 * Copyright 2003-2005 Alphus Technologies.
 *
 * @author Renato de Paula Eduardo Jr
 * @since 08/02/2008
 * @see Object
 * @since 0.0.5
 */
public class ObjectUtil implements Serializable {

	/**
	 * Metodo para testar a equalidade de dois objetos. Primeiro e verificado se
	 * um dos dois objetos forem nulos, retorna FALSE, mas se os dois forem
	 * nulos, retorna TRUE. Em seguida e verificacado se os objetos sao iguais.
	 *
	 * @param obj1
	 *            primeiro objeto para verificacao de equaldiade
	 * @param obj2
	 *            segundo objeto para verificacao de equalidade
	 * @return TRUE se os objetos forem iguais ou FALSE em caso contrario.
	 * @since 08/02/2008
	 * @since 0.0.5
	 */
	public static boolean isEqual(Object obj1, Object obj2){

		if (obj1 == null ^ obj2 == null) {
			return false;
		}

		if (obj1 != null && !obj1.equals(obj2)) {
			return false;
		}

		return true;
	}

	/**
	 * Metodo para testar a diferanca de dois objetos. Primeiro e verificado se
	 * um dos dois objetos forem nulos, retorna TRUE, mas se os dois forem
	 * nulos, retorna FALSE. Em seguida e verificacado se os objetos sao diferentes.
	 *
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 21/08/2008
	 * @param obj1 primeiro objeto para verificacao de diferenca
	 * @param obj2 segundo objeto para verificacao de diferanca
	 * @return true se os objetos forem diferentes
	 */
	public static boolean notEqual(Object obj1, Object obj2){
		return !ObjectUtil.isEqual(obj1, obj2);
	}

}