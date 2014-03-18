/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org2nd2go.bible;

import org.junit.Test;
import org2nd2go.bible.util.DirCbol;
import static org.junit.Assert.*;

/**
 *
 * @author mark
 */
public class DirCbolTest {
    
    public DirCbolTest() {
    }

 
    @Test
    public void testCheckCbolOriginalDir() {
           new DirCbol().checkCbolOriginalDir();
           new DirCbol().checkCbolOriginalDirV2();
           new DirCbol().checkCbolOriginalDirV3();
           new DirCbol().checkCbolOriginalDirV4();
    }
    
}
