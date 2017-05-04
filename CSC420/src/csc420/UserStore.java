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
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Cameron Everett, Chase Toy, Laura Buckman
 * Stores user entries for application.
 */
public class UserStore implements Iterable<TwitterUser>, Serializable {
    private final Set<TwitterUser> store;
    public UserStore() {
       store = new TreeSet<>();
    }
    
    /**
     * Clear the store and accept a new collection of TwitterUsers
     * @param followers
     */
    public synchronized void update(Collection<TwitterUser> followers) {
        store.clear();
        followers.forEach((user) -> {
            store.add(user);
        });
    }
    
    /**
     * Expose data entries to be used to populate the graphing library.
     * @return current set of user entries/user followers.
     */
    public Collection<TwitterUser> getData() {
        return store;
    }
    
    /**
     * Get the number of users in the store.
     * @return 
     */
    public int getSize(){
        return store.size();
    }

    /**
     * Empties out the twitter user profiles currently in the store.
     */
    public synchronized void clear() {
        store.clear();
    }

    @Override
    public Iterator<TwitterUser> iterator() {
        return store.iterator();
    }
}
