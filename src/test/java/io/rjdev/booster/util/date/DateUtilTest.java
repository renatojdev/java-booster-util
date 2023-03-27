package io.rjdev.booster.util.date;

import org.joda.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_dateNowAddHours(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 4);
        assert(cal.getTime().compareTo(DateUtil.dateNowAddHours(4))==0);
    }

    @Test
    public void test_dateAddHours(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 2);
        assert(cal.getTime().compareTo(DateUtil.dateAddHours(new Date(), 2))==0);
    }

    @Test
    public void test_getDateFormat(){
        assert("27/03".equals(DateUtil.getStringDateFormat(new Date(), "dd/MM")));
        assert("27/03/23".equals(DateUtil.getStringDateFormat(new Date(), "dd/MM/yy")));
        assert("2023-03-27".equals(DateUtil.getStringDateFormat(new Date(), "yyyy-MM-dd")));
        assert("27/03/2023".equals(DateUtil.getStringDateFormat(null, null)));
    }

    @Test
    public void test_getDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date dt = DateUtil.getDate(cal.getTime(), 5, 17, 11);
        cal.setTime(dt);
        assert(cal.get(Calendar.HOUR_OF_DAY) == 5);
        assert(cal.get(Calendar.MINUTE) == 17);
        assert(cal.get(Calendar.SECOND) == 11);
    }

    @Test
    public void test_getDateDay(){
        Calendar cal = Calendar.getInstance();
        int day = DateUtil.getDateDay(cal.getTime());
        assert(day == cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void test_getDateMonth(){
        Calendar cal = Calendar.getInstance();
        int month = DateUtil.getDateMonth(cal.getTime());
        assert(month == (cal.get(Calendar.MONTH)+1));
    }

    @Test
    public void test_getDateYear(){
        Calendar cal = Calendar.getInstance();
        int year = DateUtil.getDateYear(cal.getTime());
        assert(year == (cal.get(Calendar.YEAR)));
    }

    @Test
    public void test_isDateFutureFromNow(){
        Calendar cal = Calendar.getInstance();
        assert(!DateUtil.isDateFutureFromNow(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        assert(DateUtil.isDateFutureFromNow(cal.getTime()));
    }

    @Test
    public void test_hoursPastDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -72);
        assert(DateUtil.hoursPastDate(cal.getTime())==72);
    }

    @Test
    public void test_convertUtilDateToLocalDate(){
        Object jodaLocalDate = DateUtil.convertUtilDateToLocalDate(new Date());
        assert(jodaLocalDate.getClass().equals(LocalDate.class));
    }

    @Test
    public void test_daysBetweenDates(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 72);
        assert(3 == DateUtil.daysBetweenDates(new Date(), cal.getTime()));
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, -72);
        assert(-3 == DateUtil.daysBetweenDates(new Date(), cal.getTime()));
    }

}
