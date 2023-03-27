package io.rjdev.booster.util.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents a record (line) of a CSV file format.
 *
 * @author Antonio Carlos Venancio Jurnior
 * @author $Author: ec2-user $
 * @version $Revision: 1.1.1.1 $
 * @since 01/02/2006
 * @since $Date: 2018/10/05 17:10:38 $
 * @since 1.1
 */
public class Record {
	/**
	 * List of record fields.
	 *
	 * @since 1.1
	 */
	private ArrayList<Object> records = new ArrayList<Object>();

	/**
	 * Returns a particular field from the record.
	 *
	 * @param i
	 *            record index
	 * @return Record field found by the index.
	 * @since 1.1
	 */
	public final Object getCampo(int i) {
		return records.get(i);
	}

	/**
	 * Get All Records Fields.
	 *
	 * @retun Records fields list.
	 * @since 1.1
	 */
	public final ArrayList<Object> getRecords() {
		return records;
	}

	/**
	 * Constructor. Initializes the list of fields for the record.
	 *
	 * @param fields
	 *            Record fields list.
	 * @see #records
	 * @since 1.1
	 */
	public Record(List<Object> fields) {
		Iterator<Object> iterador = fields.iterator();
		while (iterador.hasNext()) {
			Object field = iterador.next();
			this.records.add(field);
		}
	}

	/**
	 * Returns a text representation of the CSV record.
	 *
	 * @return text representation of CSV record.
	 * @see StringBuffer
	 * @see Iterator
	 * @since 1.1
	 */
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<Object> iterador = records.iterator();
		while (iterador.hasNext()) {
			Object field = iterador.next();
			buffer.append(field.toString());
			buffer.append(";");
		}
		return buffer.toString().replaceFirst(";$", "");
	}
}
