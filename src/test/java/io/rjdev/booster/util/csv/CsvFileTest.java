package io.rjdev.booster.util.csv;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CsvFileTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_csvFile(){
        CsvFile csv = new CsvFile();

        List<Object> fields = new ArrayList<>();
        fields.add("Field 1");
        fields.add("Field 2");
        fields.add("Field 3");

        Record record = new Record(fields);
        csv.addRecord(record);

        fields = new ArrayList<>();
        fields.add("Field 1");
        fields.add(12.44);
        fields.add(1200.44);

        record = new Record(fields);
        csv.addRecord(record);

        csv.setCsvFile("src/main/resources/f1.csv");

        assert(!csv.isEmpty());

        assert(csv.toArquivo());
        String content = "Field 1;Field 2;Field 3\n"
                    +"Field 1;12.44;1200.44\n";
        assert(csv.toString().equals(content));

        assert(csv.getRecord(1).getCampo(1).equals(12.44));
    }

}
