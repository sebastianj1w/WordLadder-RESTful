package wordladder;

import java.util.*;

public class WordLadder {
    private String startingWord, endingWord;
    private Stack<String> ans;
    private boolean found = false;
    private boolean err = false;
    private String err_message = "";

    WordLadder() {}
    WordLadder(String start, String end, Set<String> wordSet) {
        this.startingWord = start;
        this.endingWord = end;

        if (wordSet.isEmpty()) {
            this.err = true;
            err_message = "word set empty";
            return;
        }

        if (checkValidity(this.startingWord, this.endingWord, wordSet) == 1) {
            this.err = true;
            return;
        }

        this.ans = findLadder(this.startingWord, this.endingWord, wordSet);
        this.found = !this.ans.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private static Stack<String> findLadder(String startingWord, String endingWord, Set<String> wordSet) {
        Queue<Stack<String>> queue = new LinkedList<>();
        Stack<String> beginningList = new Stack<>();
        beginningList.push(startingWord);
        queue.offer(beginningList);
        int len = startingWord.length();

        while (!queue.isEmpty()) {
            Stack<String> curList = queue.poll();
            String curWord = curList.peek();
            for (int n = 0; n < len; n++) {
                for (int i = 0; i < 26; i++) {
                    if ((char) (i + 97) == curWord.charAt(n)) {
                        continue;
                    }
                    String newWord = curWord.replaceFirst(String.valueOf(curWord.charAt(n)), String.valueOf((char) (i + 97)));
                    if (newWord.equals(endingWord)) {
                        curList.push(newWord);
                        return curList;
                    }
                    if (wordSet.contains(newWord)) {
                        Stack<String> newList = (Stack<String>) curList.clone();
                        newList.push(newWord);
                        queue.offer(newList);
                    }
                }
            }
        }
        return new Stack<>();
    }

    private int checkValidity(String start, String end, Set<String> wordSet) {
        if (start.length() != end.length()) {
            this.err_message = "length of two words are not equal";
            return 1;
        }

        if (!wordSet.contains(start)) {
            this.err_message = "starting word not exits";
            return 1;
        }

        if (!wordSet.contains(end)) {
            this.err_message = "ending word not exits";
            return 1;
        }

        if (start.equals(end)) {
            this.err_message = "ending word is the same as starting word";
            return 1;
        }

        return 0;
    }

    public String getStart() {
        return this.startingWord;
    }

    public String getEnd() {
        return this.endingWord;
    }

    public String getErr_message() {
        return this.err_message;
    }

    public Stack<String> getAns() {
        return this.ans;
    }

    public boolean getFound() {
        return this.found;
    }

    public String getStartingWord() {
        return startingWord;
    }

    public void setStartingWord(String startingWord) {
        this.startingWord = startingWord;
    }

    public String getEndingWord() {
        return endingWord;
    }

    public void setEndingWord(String endingWord) {
        this.endingWord = endingWord;
    }

    public void setAns(Stack<String> ans) {
        this.ans = ans;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    public boolean getErr() {
        return this.err;
    }
}