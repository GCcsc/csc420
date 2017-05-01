/*
 * The MIT License
 *
 * Copyright 2017 cam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package csc420.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cam
 */
public class TwitterUserTest {
    
    public TwitterUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class TwitterUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TwitterUser instance = new TwitterUser(0L, "", 1024, "");
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHandle method, of class TwitterUser.
     */
    @Test
    public void testGetHandle() {
        System.out.println("getHandle");
        TwitterUser instance = new TwitterUser(0, "handle", 1024, "");
        String expResult = "handle";
        String result = instance.getHandle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFollowersCount method, of class TwitterUser.
     */
    @Test
    public void testGetFollowersCount() {
        System.out.println("getFollowersCount");
        TwitterUser instance = new TwitterUser(0, "", 1024, "");
        int expResult = 1024;
        int result = instance.getFollowersCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProfileImageUrl method, of class TwitterUser.
     */
    @Test
    public void testGetProfileImageUrl() {
        System.out.println("getProfileImageUrl");
        TwitterUser instance = new TwitterUser(0, "", 1024, "url");
        String expResult = "url";
        String result = instance.getProfileImageUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class TwitterUser.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        TwitterUser equal = new TwitterUser(1, "", 1024, "");
        TwitterUser isGreaterThan = new TwitterUser(0, "", 1024, "");
        TwitterUser isLessThan = new TwitterUser(2, "", 1024, "");
        
        TwitterUser instance = new TwitterUser(1, "", 1024, "");
        assertEquals(0, instance.compareTo(equal));
        assertEquals(-1, instance.compareTo(isLessThan));
        assertEquals(1, instance.compareTo(isGreaterThan));
    }
    
}
