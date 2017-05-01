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
package csc420;

import csc420.models.TwitterUser;
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
public class UserStoreTest {
    
    public UserStoreTest() {
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
     * 
     */
    @Test
    public void testEquals() {
        
    }
    
    /**
     * Test of add method, of class UserStore.
     * Should increase the size of the UserStore.
     */
    @Test
    public void testAdd() {
        System.out.println("Add TwitterUser to UserStore");
        long id = 0;
        TwitterUser twitterUser = new TwitterUser(id, "example_user", 1024, "https://github.com/");
        UserStore instance = new UserStore();
        instance.add(twitterUser);
        assertEquals(twitterUser, instance.find(id));
    }
    
    /**
     * Test add method, of class UserStore.
     * Ensure no duplicate entries are allowed.
     * Determined by the TwitterUser's id instance member.
     */
    public void testAddUniqueIDs() {
        TwitterUser twitterUser = new TwitterUser(0, "example_user", 1024, "https://github.com/");
        UserStore instance = new UserStore();
        instance.add(twitterUser);
        instance.add(twitterUser);
        
        assertEquals(1, instance.getSize());
    }

    /**
     * Test of getSize method, of class UserStore.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        UserStore instance = new UserStore();
        int expResult = 2;
        for(int i = 0; i < expResult; i++) {
            instance.add(new TwitterUser(
                    (long)i,
                    "handle" + i,
                    i * 4,
                    "url/" + i
            ));
        }
        assertEquals(expResult, instance.getSize());
    }

    /**
     * Test of find method, of class UserStore.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        int userId = 0;       
        UserStore instance = new UserStore();
        assertNull(instance.find(userId));

        TwitterUser expResult = new TwitterUser(userId, "", 2, "");
        instance.add(expResult);
        assertEquals(expResult, instance.find(userId));        
    }

    /**
     * Test of remove method, of class UserStore.
     * Assumes store is empty on initialization.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int userId = 0;
        UserStore instance = new UserStore();
        
        assertEquals(0, instance.getSize());
        assertFalse(instance.remove(userId));
        
        instance.add(new TwitterUser(userId, "", 1024, ""));
        assertTrue(instance.remove(userId));        
    }

    /**
     * Test of clear method, of class UserStore.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        long userId = 0;
        
        UserStore instance = new UserStore();
        instance.clear();        
        assertEquals(0, instance.getSize());
        
        instance.add(new TwitterUser(userId, "", 1024, ""));
        instance.clear();
        assertEquals(0, instance.getSize());
    }
    
}
