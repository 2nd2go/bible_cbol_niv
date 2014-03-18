/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 http://www.justbible.com/


 INSERT INTO `bible`.`dates` (
 `ID` ,
 `DATE` ,
 `WEEKNUM` ,
 `WEEKDAY`
 )
 VALUES (
 '1', '2014-03-09', '1', '1'
 );

UPDATE `bible`.`books` SET `CHAP_CNT` = '50' WHERE `books`.`ID` =1;

 */
package org2nd2go.bible.content;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * to generate SQL insert statements
 *
 * @author mark
 */
public class SqlDates implements BooksTitle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //  
        new SqlDates().insertDates();
        
        }
    
        public void  updateChapCnt(){
    
        }
        public void  insertDates() throws ParseException{
            
        
        // http://docs.oracle.com/javase/tutorial/essential/io/file.html
//        http://docs.oracle.com/javase/tutorial/java/data/manipstrings.html
        //
        Path file = Paths.get("/home/mark/0-prj/bible/bookchap-v3.csv");
        Charset charset = Charset.forName("US-ASCII");

        //
        // http://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
        //
        String dt = "2013-03-08";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        String WEEKDAY_CHINESE="日一二三四五六";
        int id=0;
        for (int ww = 1; ww <= 52; ww++) {
            for (int dd = 0; dd < 7; dd++) {
                id++;
                c.add(Calendar.DATE, 1);  // number of days to add
                dt = sdf.format(c.getTime());  // dt is now the new date
              
                System.out.printf("INSERT INTO `bible`.`dates` ( `ID` , `DATE` , `WEEKNUM` ,`WEEKDAY` ) VALUES (");
                System.out.printf("'%s', ", id);
                System.out.printf("'%s', ", dt);
                System.out.printf("'%s', ", ww);
                System.out.printf("'%s' ", WEEKDAY_CHINESE.charAt(dd));
                System.out.printf("); %n");

            }
        }

    }

}
