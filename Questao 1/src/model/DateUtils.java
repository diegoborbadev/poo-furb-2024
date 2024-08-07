// DIEGO DANIEL BORBA

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatarData(Date data) {
        return SIMPLE_DATE_FORMAT.format(data);
    }

    public static Date parseData(String data) {
        try {
            return SIMPLE_DATE_FORMAT.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }
}
