// Problem Link : https://leetcode.com/problems/word-ladder/

import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> searchBox = new HashSet<>();
        int transformationCount = 1;

        for (String word : wordList)
            searchBox.add(word);

        if (!searchBox.contains(endWord))
            return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String curr = q.poll();

                if (curr.equals(endWord))
                    return transformationCount;

                if (searchBox.contains(curr))
                    searchBox.remove(curr);

                char[] chars = curr.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];  // store original char
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        
                        chars[i] = c;                          // mutate
                        String next = new String(chars);       // create candidate
                        
                        if (searchBox.contains(next)) {
                            q.offer(next);
                            searchBox.remove(next);
                        }
                    }
                    
                    chars[i] = original; // restore back
                }
            }
            transformationCount++;
        }
        return 0;
    }
}