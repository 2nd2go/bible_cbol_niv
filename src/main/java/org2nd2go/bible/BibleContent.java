package org2nd2go.bible;



import org2nd2go.bible.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import org2nd2go.bible.Basic;
//import org.jsoup.select.;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * http://www.divinerevelations.info/bible/index.htm World English Bible Chinese
 * BIG5
 *
 * @author mark
 */
public class BibleContent {

    String BIBLE_BASE = "/home/mark/bible/";

    public static void main(String[] args) throws IOException, ParseException {
//        new BibleContent_CBOL().getContent();
//        new BibleContent_CBOL().getContent("tw",1,1);
//        new BibleContent_CBOL().getContent("tw",40,6);
        BibleContent_CBOL bc = new BibleContent_CBOL();
//        bc.createBookSql("tw");
        bc.createBookSql("en");
        
        
//        bc.createBookSql("kjv");

    }

    /**
     *
     * @param ver tw for traditional Chinese, kjv for
     * @param book 1 to 66
     */
    public void createBookSql(String ver) throws ParseException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("-- \n");
        sb.append("-- generated by 2nd2go.org\n");
        sb.append("-- \n\n");
//        Charset charset = Charset.forName("US-ASCII");
        Charset charset = Charset.forName("UTF-8");
//      
//        if (ver.equals("tw")) {
//            charset = Charset.forName("UTF-8");
//        }
        Path file = Paths.get(BIBLE_BASE + "/sql/bible_" + ver + ".sql");

        for (int book = 1; book <= 66; book++) {
            int k = book - 1;
            int chapCnt = Basic.CHAPTER_COUNT[k];
            System.out.println("-- book#" + book + ", " + Basic.TITLE_EN[k] + ", " + Basic.TITLE_TW[k] + ", chapters=" + chapCnt);
            sb.append("\n-- book#" + book + ", " + Basic.TITLE_EN[k] + ", " + Basic.TITLE_TW[k] + ", chapters=" + chapCnt);
//
//        
//        

//        
//        
//        
            for (int chap = 1; chap <= chapCnt; chap++) {
//            System.out.println(getInsertStatement(ver, book, chap));
                String s = getInsertStatement(ver, book, chap);

//                System.out.println("  ... debug ..."+s);
                sb.append(s);

            }
        }

        //
        //
        //
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(sb.toString(), 0, sb.toString().length());
        } catch (IOException x) {
            x.printStackTrace();
            System.err.format("%n%n createBookSql   ver=%s %n%n", ver);
        }

    }

    public String getOneChapterContent(String ver, String bookChapter) throws ParseException, IOException {
        Path file = Paths.get("/home/mark/bible/content/" + ver + "/" + bookChapter + ".htm");
        StringBuilder sb = new StringBuilder();

        Charset charset = Charset.forName("US-ASCII");
        if (ver.equals("tw")) {
            charset = Charset.forName("BIG5");

        }

        String line = null;

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            while ((line = reader.readLine()) != null) {

                sb.append(line);
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("getOneChapterContent  ...IOException:", x);
            System.err.format("%nver=%s", ver);
            System.err.format("%nbook chap=%s%n", bookChapter);
            System.err.format("%n%s%n", sb.toString());
          
            
//            System.exit(-1);

        }
        return sb.toString();
    }

    public String getBookChapter(int book, int chapter) {
        String strBook = String.format("B%02d", book);
        String strChapter = String.format("C%03d", chapter);
        return strBook + strChapter;
    }

    public String getInsertStatement(String ver, int book, int chapter) throws ParseException, IOException {
//        String strBook = String.format("B%02d", book);
//        String strChapter = String.format("C%03d", chapter);
        String strHtml = getOneChapterContent(ver, getBookChapter(book, chapter));
        int verse = 0;
        Document doc = Jsoup.parse(strHtml);
        Elements testing = doc.select("P");

        StringBuilder sb = new StringBuilder();
        sb.append("\nINSERT INTO `bible`.`bible` (`VERSION`, `BOOK`, `CHAPTER`, `VERSE`, `CONTENT`) VALUES");
        for (Element src : testing) {
            //
            // filter out
            //
            if (src.toString().contains("<a href=")) {
                continue;
            }
            if (src.toString().length() <= 7) {
                continue;
            }
            if (src.toString().contains("<img src")) {
                continue;
            }
            if (src.toString().contains("<p>&nbsp;</p>")) {
                continue;
            }

            //
            // replace 
            //
            String plain;
            plain = src.toString().replace("<p>", "");
            plain = plain.replace("</p>", "");
            plain = plain.replace("<i>", "");
            plain = plain.replace("</i>", "");
            if (ver.equals("tw")) {
                plain = plain.replace(" ", "");
                plain = plain.replace("．", "。");
                plain = plain.replace("、", "，");

            } else {
                // for English
                plain = plain.replace("'", "&rsquo;");

            }

            //
            // compose SQL
            //
            verse++;

//            System.out.println("ver="+ver+" book"+book+" chapter="+chapter+" "+verse + "=> " + plain);
            String temp = String.format("%n('%s','%s','%s','%s','%s'),", ver, book, chapter, verse, plain);
            sb.append(temp);
//            System.out.println(temp);

        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

//        System.out.println(sb.toString());
        return sb.toString();

    }

    public void getContent(String ver, int book, int chapter) throws ParseException, IOException {
//        String ver = "tw";
//        String strHtml = getOneChapterHtmlSource("kjv", "B40C021");
        String strBook = String.format("B%02d", book);
        String strChapter = String.format("C%03d", chapter);

//        System.out.println(" ===========> "+strBook+strChapter);
        String html = getOneChapterContent(ver, strBook + strChapter);

//        System.out.println(" =========== ");
//        System.out.println(strHtml);
//        System.out.println(" =========== ");
        int chapNum = 0;
        Document doc = Jsoup.parse(html);
        Elements testing = doc.select("P");
        for (Element src : testing) {
            if (src.toString().contains("<a href=")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            if (src.toString().length() <= 7) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            //<img src
            if (src.toString().contains("<img src")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            if (src.toString().contains("<p>&nbsp;</p>")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }

            chapNum++;
            String plain;
            plain = src.toString().replace("<p>", "");
            plain = plain.replace("</p>", "");
            plain = plain.replace("<i>", "");
            plain = plain.replace("</i>", "");
            if (ver.equals("tw")) {
                plain = plain.replace(" ", "");
                plain = plain.replace("．", "。");
                plain = plain.replace("、", "，");
            }
            System.out.println(chapNum + "=> " + plain);

        }

    }

    public void getContent() throws ParseException, IOException {
        String ver = "tw";
//        String strHtml = getOneChapterHtmlSource("kjv", "B40C021");
        String html = getOneChapterContent(ver, "B40C006");

        System.out.println(" =========== ");
        System.out.println(html);
        System.out.println(" =========== ");
        int chapNum = 0;
        Document doc = Jsoup.parse(html);
        Elements testing = doc.select("P");
        for (Element src : testing) {
            if (src.toString().contains("<a href=")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            if (src.toString().length() <= 7) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            //<img src
            if (src.toString().contains("<img src")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }
            if (src.toString().contains("<p>&nbsp;</p>")) {
//                System.out.println("   ... to ignore");
//                System.out.println("   ... " + src);
                continue;
            }

            chapNum++;
            String plain;
            plain = src.toString().replace("<p>", "");
            plain = plain.replace("</p>", "");
            plain = plain.replace("<i>", "");
            plain = plain.replace("</i>", "");
            if (ver.equals("tw")) {
                plain = plain.replace(" ", "");
                plain = plain.replace("．", "。");
                plain = plain.replace("、", "，");
            }
            System.out.println(chapNum + "=> " + plain);

        }

    }

    public void insertDates() throws ParseException {

        // http://docs.oracle.com/javase/tutorial/essential/io/file.strHtml
//        http://docs.oracle.com/javase/tutorial/java/data/manipstrings.strHtml
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
        String WEEKDAY_CHINESE = "日一二三四五六";
        int id = 0;
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
