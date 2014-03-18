/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org2nd2go.bible.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mark
 */
public class DirCbol {

    String CBOL_HOME = "/home/mark/bible/content/cbol";

    public void checkCbolOriginalDirV4() {
        Path dir = Paths.get(CBOL_HOME);

//        List<String> books = new ArrayList<>();
        Set<String> books = new HashSet<>();

        StringBuilder sb;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                sb = new StringBuilder();
                sb.append(file.getFileName().toString());
                if (sb.toString().startsWith("unv_")) {
                    sb.delete(0, 4); // remove leading unv_
                    sb.delete(sb.length() - 5, sb.length()); // remove .html
//                    int pos = sb.indexOf("_");// position of _ underscore
//                    System.err.println(" _ is at ?" + pos);

                    sb.delete(sb.indexOf("_"), sb.length()); // remove .html
                    
//                    System.err.println("book is " + sb.toString());

                    books.add(sb.toString());
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

       // Collections.sort(books);
        int k=0;
        for (String book : books) {
            System.out.println("cnt="+(++k)+" "+book);

        }

    }

    public void checkCbolOriginalDirV3() {
        Path dir = Paths.get(CBOL_HOME);

        List<String> list = new ArrayList<>();

        //
        //http://docs.oracle.com/javase/tutorial/essential/io/dirs.html
        String str;
        String str2;
        String str3;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                str = file.getFileName().toString();
                if (str.startsWith("unv_")) {
                    str2 = str.substring(4);
                    str3 = str2.replace(".html", "");
//                    System.out.println(str3);
                    list.add(str3);
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

        Collections.sort(list);
        for (String sorted : list) {
            System.out.println(sorted);

        }

    }

    public void checkCbolOriginalDirV2() {
        Path dir = Paths.get(CBOL_HOME);
        //
        //http://docs.oracle.com/javase/tutorial/essential/io/dirs.html
        String str;
        String str2;
        String str3;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                str = file.getFileName().toString();
                if (str.startsWith("unv_")) {
                    str2 = str.substring(4);
                    str3 = str2.replace(".html", "");
                    System.out.println(str3);
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

    }

    public void checkCbolOriginalDir() {
        Path dir = Paths.get(CBOL_HOME);
        //
        //http://docs.oracle.com/javase/tutorial/essential/io/dirs.html

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

    }

}
