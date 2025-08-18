// Problem Link : https://www.geeksforgeeks.org/problems/alien-dictionary/1

import java.util.*;

// Approach : Topological sort
class Solution {
    
    boolean DFS(Map<Character , ArrayList<Character>> adj, int []state,Stack<Character> charOrder, char u){
        if (state[u - 'a'] == 1) return false; 
        if (state[u - 'a'] == 2) return true;
        
        state[u -'a'] = 1;
        
        if(adj.containsKey(u)) {
            for(char v : adj.get(u)){
                  if (!DFS(adj, state, charOrder, v)) return false;
            }
        }
        state[u -'a'] = 2;
        charOrder.add(u);
        return true;
    }
    
    public String findOrder(String[] words) {
        int n = words.length;
        Set<Character> st = new HashSet<>();
        StringBuilder result = new StringBuilder(""); 
        Map<Character , ArrayList<Character>> adj = new HashMap<>();
        Stack<Character> charOrder = new Stack<>();
        
        for(int i = 0; i < n - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            int j=0;
             for (; j < len; j++) {
                if(w1.charAt(j) != w2.charAt(j)) {
                    adj.computeIfAbsent(w1.charAt(j), k -> new ArrayList<>()).add(w2.charAt(j));
                    break; 
                }
            }
            
            if(j == len && w1.length() > w2.length()) return "";
            
            
        }
        
        for(String word : words){
            for(char c : word.toCharArray()){
                st.add(c);
            }
        }
       
        
        int[] state = new int[26];
        
        for(char c : st){
            if (state[c - 'a'] == 0) {
                if (!DFS(adj, state, charOrder, c)) return "";
            }
        }
        
        while(!charOrder.isEmpty()){
            result.append(charOrder.pop());
        }
        
        return result.toString();
    }
}
