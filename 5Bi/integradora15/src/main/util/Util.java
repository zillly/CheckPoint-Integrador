package main.util;

import java.security.Timestamp;
import java.sql.Date;

public class Util {


    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }

}
