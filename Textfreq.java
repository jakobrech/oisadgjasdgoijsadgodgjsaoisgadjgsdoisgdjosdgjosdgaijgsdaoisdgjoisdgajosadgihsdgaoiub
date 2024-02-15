package Hangman;
import java.io.*;
import java.util.*;

public class Textfreq {
    public static void main(String[] args) {
        List<String> words = readFile();
        HashMap<String, Integer> freq = freqCount(words);
        List<wordFreq> freqSorted = toOrder(freq);
        
        System.out.println("Word Frequencies: " );
        for (wordFreq word : freqSorted) {
            System.out.println("Word: " + word.word + " /// Freq: " + word.freq);
        }

    }
    private static List<String> readFile() {
        Scanner reader;
        try {
            reader = new Scanner(new File("Hangman/frank.txt"));
            List<String> words = new ArrayList<String>();

            while (reader.hasNext()){
                words.add(reader.nextLine().toLowerCase());
            } 
            reader.close ();
            return words;
        } catch (IOException e) {
            e.printStackTrace();
            List<String> error = new ArrayList<String>();
            error.add("computer");
            return error;
        }
    }

    @SuppressWarnings("unused")
    private static HashMap<String, Integer> freqCount(List<String> listy) {
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        for (String sentance : listy) {
            String[] word = sentance.split(" ");
            for(String sentWord : word){
                sentWord = sentWord.toLowerCase();
                sentWord = sentWord.replaceAll("[^\\sa-zA-Z0-9]", "");
                freq.put(sentWord, (freq.get(sentWord) == null ? 1 : freq.get(sentWord))+1);
            }
        }
        return freq;
    }

    @SuppressWarnings("unused")
    private static List<wordFreq> toOrder(HashMap<String, Integer> freqMap){
        List<wordFreq> freqList = new ArrayList<wordFreq>();
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            wordFreq wf = new wordFreq(key,value);
            freqList.add(wf);
        }
        Collections.sort(freqList);
        return freqList;
    }
}

class wordFreq implements Comparable<wordFreq> {
    String word;
    int freq;

    wordFreq(String word, int freq){
        this.word = word;
        this.freq = freq;
    }

    @Override
    public int compareTo(wordFreq o) {
        return  o.freq - this.freq;
    }
}
