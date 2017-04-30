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
import java.io.Serializable;
import java.util.TreeSet;

/**
 *
 * @author Cameron Everett, Chase Toy, Laura Buckman
 * Stores user entries for application.
 */
public class UserStore implements Serializable {
    TreeSet<TwitterUser> store;
    public UserStore() {
       store = new TreeSet<>();
    }
    
    /**
     * Add a user to the store.
     * @param twitterUser 
     */
    public void add(TwitterUser twitterUser){
        store.add(twitterUser);
    }
    
    /**
     * Get the number of users in the store.
     * @return 
     */
    public int getSize(){
        return store.size();
    }
    
    /**
     * Find a user in the store by the profile id. Returns null if it is not
     * found within the store.
     * @param userId
     * @return TwitterUser on success or null otherwise.
     */
    public TwitterUser find(int userId){
        for(TwitterUser user : store) {
            if(user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Remove a user from the store by the profile id. Returns true if a user
     * was deleted from the store or false otherwise.
     * @param userId
     * @return 
     */
    public boolean remove(int userId){
        for (TwitterUser user : store){
            if(user.getId() == userId){
                store.remove(user);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Empties out the twitter user profiles currently in the store.
     */
    public void clear() {
        store.clear();
    }
}
