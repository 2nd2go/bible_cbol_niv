/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org2nd2go.bible.content;

/**
 * to generate SQL insert statements
 *
 * @author mark
 */
public class Main implements BooksTitle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  INSERT INTO `bible`.`books` 
        //    (`ID`, `TESTAMENT`, `TITLE`, `TITLE_CN`, `TITLE_CN_SHORT`) 
        //    VALUES 
        //      ('1', 'OT', 'Genesis', '創世紀', '創');     
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 39; k++) {

            sb.append("INSERT INTO `bible`.`books` ");
            sb.append("(`ID`, `TESTAMENT`, `TITLE`, `TITLE_CN`, `TITLE_CN_SHORT`)  ");
            sb.append(" VALUES (");
            sb.append("'").append((1 + k)).append("',");
            sb.append("'OT',");
            sb.append("'").append(OLD_TESTAMENT[k]).append("',");
            sb.append("'").append(OLD_TESTAMENT_CHINESE[k]).append("',");
            sb.append("'").append(OLD_TESTAMENT_CHINESE_SHORT[k]).append("');");
            sb.append("\n");
        }
        System.out.println(sb.toString());

        System.out.println("nt CNT="+NEW_TESTAMENT_CHINESE.length);

        //
        //
        //
        sb = new StringBuilder();
        for (int k = 0; k < 27; k++) {

            sb.append("INSERT INTO `bible`.`books` ");
            sb.append("(`ID`, `TESTAMENT`, `TITLE`, `TITLE_CN`, `TITLE_CN_SHORT`)  ");
            sb.append(" VALUES (");
            sb.append("'").append((39+1 + k)).append("',");
            sb.append("'OT',");
            sb.append("'").append(NEW_TESTAMENT[k]).append("',");
            sb.append("'").append(NEW_TESTAMENT_CHINESE[k]).append("',");
            sb.append("'").append(NEW_TESTAMENT_CHINESE_SHORT[k]).append("');");
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

}
