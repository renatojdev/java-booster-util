package io.rjdev.booster.util.string;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.date.Converter;

public class ConverterTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_convertDate(){
        try{
            assert(
                Converter.convertDate("10/02/2005", "dd/MM/yyyy", "yyyy-MM-dd")
                .equals("2005-02-10")
            );
            assert(
                Converter.convertDate("2005-02-10", "yyyy-MM-dd", "dd/MM/yyyy")
                .equals("10/02/2005")
            );
        }catch(Exception e){}
    }

    @Test
    public void test_DateToString(){

        String dateString = "2005-02-10";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date = dateFormat.parse(dateString);
            assert(
                Converter.dateToString(date)
                .equals("10/02/2005")
            );
            assert(
                Converter.dateToString(date, "yyyy-MM-dd")
                .equals("2005-02-10")
            );
        }catch(Exception e){}
    }


    @Test
    public void test_StringToDate(){

        String dateString = "2005-02-10";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date = dateFormat.parse(dateString);
            assert(
                Converter.stringToDate(dateString)
                .equals(date)
            );
            assert(
                Converter.stringToDate(dateString, "yyyy-MM-dd")
                .equals(date)
            );
        }catch(Exception e){}
    }

}
