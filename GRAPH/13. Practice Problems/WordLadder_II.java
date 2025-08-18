// Problem Link : https://leetcode.com/problems/word-ladder-ii/

// Old Solution --> TLE (33/38 T.C passed)
// Basic BFS + Set solution
// Important for Interview

import java.util.*;
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> searchBox = new HashSet<>();
        ArrayList<List<String>> result = new ArrayList<>();
        boolean found = false;

        for (String word : wordList)
            searchBox.add(word);

        Queue<ArrayList<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(Arrays.asList(beginWord))); 

        searchBox.remove(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            List<String> toBeDeleted = new ArrayList<>();
            while (size-- > 0) {
                ArrayList<String> currPath = q.poll();
                String curr = currPath.get(currPath.size() - 1);

                if (curr.equals(endWord)) {
                    int len = currPath.size(); 
                    found = true;
                     result.add(currPath);
                    // while (!q.isEmpty() && q.peek().size() == len) {
                    //     result.add(q.poll());
                    // }
                    // return result;
                }

               if(!found){ 
                char[] chars = curr.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];  // store original char
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        
                        chars[i] = c;                          
                        String next = new String(chars);       
                        
                        if (searchBox.contains(next)) {
                            ArrayList<String> newPath = new ArrayList<>(currPath); 
                            newPath.add(next);
                            q.offer(newPath);
                            toBeDeleted.add(next);
                        }
                    }                    
                    chars[i] = original; 
                }}
            }
            searchBox.removeAll(toBeDeleted);
            if (found) return result;
        }
        return result;
    }
}


// All T.C Passed 
// Reverse Graph + BFS + Backtracing
// Optimal Solution : https://leetcode.com/problems/word-ladder-ii/solutions/6319954/i-spent-3-hours-developing-this-fastest-approach-check-it-out-and-don-t-forget-to-drop-a-like/

class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>(); 
        Map<String, Set<String>> reverse = new HashMap<>(); // reverse graph start from endWord
        Set<String> wordSet = new HashSet<>(wordList); // remove the duplicate words

        wordSet.remove(beginWord); // remove the first word to avoid cycle path

        Queue<String> queue = new LinkedList<>(); // store current layer nodes
        queue.add(beginWord); // first layer has only beginWord

        Set<String> nextLevel = new HashSet<>(); // store nextLayer nodes
        boolean findEnd = false; // find endWord flag
        
        while (!queue.isEmpty()) { // traverse current layer nodes
            String word = queue.remove();
            for (String next : wordSet) {
                if (isLadder(word, next)) { // is ladder words
					// construct the reverse graph from endWord
                    Set<String> reverseLadders = reverse.computeIfAbsent(next, k -> new HashSet<>());
                    reverseLadders.add(word); 
                    if (endWord.equals(next)) {
                        findEnd = true;
                    }
                    nextLevel.add(next); // store next layer nodes
                }
            }
            if (queue.isEmpty()) { // when current layer is all visited
                if (findEnd) break; // if find the endWord, then break the while loop
                queue.addAll(nextLevel); // add next layer nodes to queue
                wordSet.removeAll(nextLevel); // remove all next layer nodes in wordSet
                nextLevel.clear();
            }
        }
        if (!findEnd) return ans; // if can't reach endWord from startWord, then return ans.
        Set<String> path = new LinkedHashSet<>();
        path.add(endWord);
		// traverse reverse graph from endWord to beginWord
        findPath(endWord, beginWord, reverse, ans, path); 
        return ans;
    }


    private void findPath(String endWord, String beginWord, Map<String, Set<String>> graph,
                                 List<List<String>> ans, Set<String> path) {
        Set<String> next = graph.get(endWord);
        if (next == null) return;
        for (String word : next) {
            path.add(word);
            if (beginWord.equals(word)) {
                List<String> shortestPath = new ArrayList<>(path);
                Collections.reverse(shortestPath); // reverse words in shortest path
                ans.add(shortestPath); // add the shortest path to ans.
            } else {
                findPath(word, beginWord, graph, ans, path);
            }
            path.remove(word);
        }
    }

    private boolean isLadder(String s, String t) {
        if (s.length() != t.length()) return false;
        int diffCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) diffCount++;
            if (diffCount > 1) return false;
        }
        return diffCount == 1;
    }
}



//  DRY RUN
/*
---

### Example Input

```java
beginWord = "hit"
endWord   = "cog"
wordList  = ["hot","dot","dog","lot","log","cog"]
```

---

### Step 1: Initialization

* `reverse` = {} (empty map, will store reverse graph edges like `child -> {parents}`)
* `wordSet` = { "hot","dot","dog","lot","log","cog" }
* `queue` = \["hit"]
* `findEnd = false`

---

### Step 2: BFS Layer by Layer

We build **reverse graph** as we explore.

#### Layer 1 (queue = \["hit"])

* Pop `"hit"`
* Compare with all words in `wordSet`:

  * `"hot"` differs by 1 → valid ladder word.
    `reverse["hot"] = {"hit"}`
    `nextLevel = {"hot"}`
* Other words differ > 1 → skip.

👉 End of layer.

* `queue = ["hot"]`
* `wordSet = {"dot","dog","lot","log","cog"}`
* `nextLevel` cleared.

---

#### Layer 2 (queue = \["hot"])

* Pop `"hot"`
* Compare with `wordSet`:

  * `"dot"` → differs by 1.
    `reverse["dot"] = {"hot"}`
    add to `nextLevel`.
  * `"lot"` → differs by 1.
    `reverse["lot"] = {"hot"}`
    add to `nextLevel`.

👉 End of layer.

* `queue = ["dot","lot"]`
* `wordSet = {"dog","log","cog"}`

---

#### Layer 3 (queue = \["dot","lot"])

* Pop `"dot"`

  * `"dog"` → differs by 1.
    `reverse["dog"] = {"dot"}`
    add to `nextLevel`.
* Pop `"lot"`

  * `"log"` → differs by 1.
    `reverse["log"] = {"lot"}`
    add to `nextLevel`.

👉 End of layer.

* `queue = ["dog","log"]`
* `wordSet = {"cog"}`

---

#### Layer 4 (queue = \["dog","log"])

* Pop `"dog"`

  * `"cog"` → differs by 1.
    `reverse["cog"] = {"dog"}`
    `findEnd = true` ✅
    add `"cog"` to `nextLevel`.
* Pop `"log"`

  * `"cog"` → differs by 1.
    `reverse["cog"]` already has `{"dog"}` → add `"log"`.
    So `reverse["cog"] = {"dog","log"}`

👉 End of layer.
Since `findEnd = true`, **stop BFS**.

---

### Step 3: Reverse Graph

Now we have:

```
hot -> {hit}
dot -> {hot}
lot -> {hot}
dog -> {dot}
log -> {lot}
cog -> {dog, log}
```

This graph lets us backtrack from `cog` to `hit`.

---

### Step 4: DFS Backtracking

Start from `endWord = "cog"`, path = \["cog"]

* `cog` → {dog, log}

  * Take `"dog"`:

    * Path = \["cog","dog"]
    * `dog` → {dot}

      * `"dot"`

        * Path = \["cog","dog","dot"]
        * `dot` → {hot}

          * `"hot"`

            * Path = \["cog","dog","dot","hot"]
            * `hot` → {hit}

              * `"hit"`

                * Path = \["cog","dog","dot","hot","hit"]
                * Reached beginWord ✅
                * Reverse path → \["hit","hot","dot","dog","cog"]
                * Add to ans.
  * Backtrack, then take `"log"`:

    * Path = \["cog","log"]
    * `log` → {lot}

      * `"lot"`

        * Path = \["cog","log","lot"]
        * `lot` → {hot}

          * `"hot"`

            * Path = \["cog","log","lot","hot"]
            * `hot` → {hit}

              * `"hit"`

                * Path = \["cog","log","lot","hot","hit"]
                * Reverse path → \["hit","hot","lot","log","cog"]
                * Add to ans.

---

### Final Answer

```java
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
``` 
---
*/