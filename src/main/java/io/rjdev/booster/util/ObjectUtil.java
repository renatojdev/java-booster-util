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
	 * Method for testing the equality of two objects. First and check if
	 * one of the two objects is null, returns FALSE, but if both are
	 * null, returns TRUE. Then it is checked if the objects are equal.
	 *
	 * @param obj1
	 * first object for equality check
	 * @param obj2
	 * second object for equality check
	 * @return TRUE if the objects are equal or FALSE otherwise.
	 * @since 08/02/2008
	 * @since 0.0.5
	 */
	public static boolean isEquals(Object obj1, Object obj2){

		if (obj1 == null ^ obj2 == null) {
			return false;
		}

		if (obj1 != null && !obj1.equals(obj2)) {
			return false;
		}

		return true;
	}

	/**
	 * Method for testing the difference of two objects. First and check if
 	 * one of the two objects is null, returns TRUE, but if both are
	 * null, returns FALSE. Then it is checked if the objects are different.
	 *
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 8/21/2008
	 * @param obj1 first object for diff checking
	 * @param obj2 second object for difference checking
	 * @return true if the objects are different
	 */
	public static boolean notEqual(Object obj1, Object obj2){
		return !ObjectUtil.isEquals(obj1, obj2);
	}

}