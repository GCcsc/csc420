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

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Cameron Everett, Chase Toy, Laura Buckman
 */
public class TwitterUser implements Comparable<TwitterUser>, Serializable {
    private String handle;
    private long id;
    private int followersCount;
    private String profileImageUrl;
    private int friendsCount;
    
    public TwitterUser(long id, String handle, int followersCount, int friendsCount, String profileImageUrl) {
        this.id = id;
        this.handle = handle;
        this.followersCount = followersCount;
        this.friendsCount = friendsCount;
        this.profileImageUrl = profileImageUrl;
    }
    
    /**
     * Returns twitter id.
     * @return Id of the twitter user's profile.
     */
    public long getId() {
        return id;
    }
    
    /**
     * Returns twitter handle of current user.
     * @return Display name of the twitter user's profile.
     */
    public String getHandle() {
        return handle;
    }
    
    /**
     * Returns number of followers this user has.
     * @return Number of users following this profile.
     */
    public int getFollowersCount() {
        return followersCount;
    }
    
    /**
     * @return Number of friends this user has.
     */
    public int getFriendsCount() {
        return friendsCount;
    }
    
    /**
     * Returns link to user's profile image.
     * @return Link to user's profile image.
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    
    @Override
    public String toString() {
        return String.format("@%s", handle);
    }

    @Override
    public int compareTo(TwitterUser other) {
        if(this.id < other.getId()) {
            return -1;
        }
        else if(this.id == other.getId()) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof TwitterUser)) {
            return false;
        }
        if(this == other) {
            return true;
        }
        TwitterUser o = (TwitterUser) other;
        if(this.id == o.getId()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.handle);
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + this.followersCount;
        hash = 79 * hash + Objects.hashCode(this.profileImageUrl);
        return hash;
    }
    
}
