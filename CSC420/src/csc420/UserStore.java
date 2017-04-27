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
import java.util.ArrayList;

/**
 *
 * @author Cameron Everett, Chase Toy, Laura Buckman
 */
public class UserStore implements Serializable {
    ArrayList<TwitterUser> store;
    public UserStore() {
       store = new ArrayList<>();
    }
    public void add(TwitterUser item){
        store.add(item);
    }
    public int getLength(){
        return store.size();
    }
    public TwitterUser find(int userId){
        for(TwitterUser user : store) {
            if(user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    public boolean remove(int userId){
        for (TwitterUser user : store){
            if(user.getId() == userId){
                store.remove(user);
                return true;
            }
        }
        return false;
    }
}
