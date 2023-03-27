package io.rjdev.booster.util.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents a file in CSV format.
 *
 * @author Renato de Paula Eduardo Junior
 * @author $Author: ec2-user $
 * @version $Revision: 1.1.1.1 $
 * @since 21/11/2007
 * @since $Date: 2018/10/05 17:10:38 $
 * @since 1.1
 */
public class CsvFile {
	/**
	 * List of records (lines) in the file.
	 *
	 * @since 1.1
	 */
	private List<Record> records = new ArrayList<Record>();

	private String csvFile = null;

	/**
	 * Returns the list of records from the file.
	 *
	 * registration number
	 *
	 * @return list of records in the file.
	 * @since 1.1
	 */
	public final List<Record> getRecords() {
		return records;
	}

	/**
	 * Adds a list of records to the file.
	 *
	 * @param records
	 *              list of records to add to the file.
	 * @since 1.2
	 */
	public final void addRecords(List<Record> registros) {
		this.records.addAll(registros);
	}

	/**
	 * Adds a record to the file.
	 *
	 * @param record
	 *             record to be added to the file.
	 * @since 1.2
	 */
	public final void addRecord(Record record) {
		records.add(record);
	}

	/**
	 * Returns a particular record of the file.
	 *
	 * @param i
	 *           record index
	 * @return a CSV Record
	 * @since 1.1
	 */
	public final Record getRecord(int i) {
		return (Record) records.get(i);
	}

	/**
	 * Sets the CSV file name with the full path
	 * of the system to be saved.
	 * @param csvFile file with file path
	 */
	public final void setCsvFile(String csvFile){
		this.csvFile = csvFile;
	}

	/**
	 * Constructor. Initializes the file's list of records.
	 *
	 * @see #records
	 * @since 1.1
	 */
	public CsvFile(List<Record> records) {
		this.records = records;
	}

	/**
	 * Constructor. Initializes the file's list of records and
	 * the CSV file name with the full path
	 * of the system to be saved.
	 *
	 * @param records
	 * 				 file records
	 * @param csvFile
	 * 				 CSV file path to be saved
	 */
	public CsvFile(List<Record> records, String csvFile) {
		this.records = records;
		this.csvFile = csvFile;
	}

	/**
	 * Default Constructor.
	 *
	 * @since 1.2
	 */
	public CsvFile() {}

	/**
	 * Informs you if the file is empty.
	 *
	 * @return true if file is empty.
	 * @see ArrayList#isEmpty()
	 * @since 1.2
	 */
	public final boolean isEmpty() {
		return records.isEmpty();
	}

	/**
	 * Returns a text representation of the CSV file.
	 *
	 * @return Text string of the CSV file.
	 * @see StringBuffer
	 * @see Iterator
	 * @since 1.1
	 */
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<Record> iterador = records.iterator();
		while (iterador.hasNext()) {
			Record rec = (Record) iterador.next();
			buffer.append(rec.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Get records text representation of the CSV file and
	 * saves the file to disk.
	 *
	 * @return boolean if you were able to save the file.
	 * @see StringBuffer
	 * @see Iterator
	 * @since 1.1
	 */
	public final boolean toArquivo() {
		File f = new File(csvFile);

		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(f, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			out.write(toString().getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
