/**
 * <p>
 * Q8.1) Write a program (in any language you like) to help an analyst decrypt a simple substitution cipher.
 * Your program should take the ciphertext as input, compute letter frequency counts, and display these for the analyst.
 * The program should then allow the analyst to guess a key and display the results of the corresponding "decryption" with the putative key.
 * input: GBSXUCGSZQGKGSQPKQKGLSKASPCGBGBKGUKGCEUKUZKGGBSQEICACGKGCEUERWKLKUPKQQGCIICUAEUVSHqKGCEUPCGBCGQOEVSHUNSUGKUZCGQSNLSHEHIEEDCUOGEPKHZGBSNKCUGSUKUASERLSKASCUGBSLKACRCACUZSSZEUSBEXHKRGSHWKLKUSQSKCHQTXKZHEUQBKZAENNSUASZFENFCUOCUEKBXGBSWKLKUSQSKNFKQQKZEHGEGBSXUCGSZQGKGSQKUZBCQAEIISKOXSZSICVSHSZGEGBSQSAHSGKHMERQGKGSKREHNKIHSLIMGEKHSASUGKNSHCAKUNSQQKOSPBCISGBCqHSLIMQGKGSZGBKGCGQSSNSZXQSISQQGEAEUGCUXSGBSSJCqGCUOZCLIENKGCAUSOEGCKGCEUqCGAEUGKCUSZUEGBHSKGEHBCUGERPKHEHKHNSZKGGKAD
 * <p>
 *
 * @author: Diem Vu
 * @version: 1.0
 */

import java.util.*;
import java.util.Map.Entry;
import java.util.Set.*;

public class SimpleSubstitutionExampleDecrypt {
    // function to sort the HashMap by the decending order of values
    static LinkedHashMap<Character, Integer> sortMap(HashMap<Character, Integer> map) {
        TreeMap<Character, Integer> sorted = new TreeMap<>(map);
        Set<Entry<Character, Integer>> mappings = map.entrySet();
        Comparator<Entry<Character, Integer>> valueComparator = new Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return Integer.valueOf(v2).compareTo(Integer.valueOf(v1));
            }
        };
        List<Entry<Character, Integer>> listOfEntries = new ArrayList<Entry<Character, Integer>>(map.entrySet());
        Collections.sort(listOfEntries, valueComparator);
        //put all sorted entries in LinkedHashMap
        LinkedHashMap<Character, Integer> sortedByValue = new LinkedHashMap<Character, Integer>(listOfEntries.size());
        // copying entries from List to Map
        for (Entry<Character, Integer> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //read input
        System.out.print("ciphertext: ");
        String cipher = "GBSXUCGSZQGKGSQPKQKGLSKASPCGBGBKGUKGCEUKUZKGGBSQEICACGKGCEUERWKLKUPKQQGCIICUAEUVSHqKGCEUPCGBCGQOEVSHUNSUGKUZCGQSNLSHEHIEEDCUOGEPKHZGBSNKCUGSUKUASERLSKASCUGBSLKACRCACUZSSZEUSBEXHKRGSHWKLKUSQSKCHQTXKZHEUQBKZAENNSUASZFENFCUOCUEKBXGBSWKLKUSQSKNFKQQKZEHGEGBSXUCGSZQGKGSQKUZBCQAEIISKOXSZSICVSHSZGEGBSQSAHSGKHMERQGKGSKREHNKIHSLIMGEKHSASUGKNSHCAKUNSQQKOSPBCISGBCqHSLIMQGKGSZGBKGCGQSSNSZXQSISQQGEAEUGCUXSGBSSJCqGCUOZCLIENKGCAUSOEGCKGCEUqCGAEUGKCUSZUEGBHSKGEHBCUGERPKHEHKHNSZKGGKAD";
        System.out.println(cipher);
        // split the string to array of chars
        char[] lettersArr = cipher.toCharArray();
        ArrayList<Character> lettersList = new ArrayList<>();
        for (char c : lettersArr) {
            lettersList.add(c);
        }

        // compute letter frequency counts
        HashMap<Character, Integer> countTable = new HashMap<>();
        for (char c : lettersList) {
            if (Character.isUpperCase(c)) {
                if (countTable.containsKey(c)) {
                    countTable.replace(c, countTable.get(c) + 1);
                } else {
                    countTable.put(c, 1);
                }
            } else {
                if (!countTable.containsKey(c)) {
                    countTable.put(c, 0);
                }
            }
        }

        //sort the hashtable
        LinkedHashMap<Character, Integer> sortedMap = sortMap(countTable);
        HashMap<Character, Integer> sortedHashedMap = new HashMap<>(sortedMap);

        //create table of English frequency
        LinkedHashMap<Character, Integer> EnglishFrequency = new LinkedHashMap<Character, Integer>(26);
        EnglishFrequency.put('E', 1);
        EnglishFrequency.put('T', 2);
        EnglishFrequency.put('A', 3);
        EnglishFrequency.put('O', 4);
        EnglishFrequency.put('I', 5);
        EnglishFrequency.put('N', 6);
        EnglishFrequency.put('S', 7);
        EnglishFrequency.put('R', 8);
        EnglishFrequency.put('H', 9);
        EnglishFrequency.put('D', 10);
        EnglishFrequency.put('L', 11);
        EnglishFrequency.put('U', 12);
        EnglishFrequency.put('C', 13);
        EnglishFrequency.put('M', 14);
        EnglishFrequency.put('F', 15);
        EnglishFrequency.put('Y', 16);
        EnglishFrequency.put('W', 17);
        EnglishFrequency.put('G', 18);
        EnglishFrequency.put('P', 19);
        EnglishFrequency.put('B', 20);
        EnglishFrequency.put('V', 21);
        EnglishFrequency.put('K', 22);
        EnglishFrequency.put('X', 23);
        EnglishFrequency.put('Q', 24);
        EnglishFrequency.put('J', 25);
        EnglishFrequency.put('Z', 26);

        //create lists of keys of each entry in sortedMap and EnglishFrequency
        List<Entry<Character, Integer>> sortedListEntry = new ArrayList<Entry<Character, Integer>>(sortedMap.entrySet());
        List<Entry<Character, Integer>> frequencyListEntry = new ArrayList<Entry<Character, Integer>>(EnglishFrequency.entrySet());
        // newList contains letter following the sorted order from sortedMap
        ArrayList<Character> inputList = new ArrayList<>();//[sortedListEntry.size()];
        int i = 0;
        for (Entry<Character, Integer> entry : sortedListEntry) {
            inputList.add(i, entry.getKey());
            i++;
        }
        // newList contains letter following frequency order from the mpst popular to the least popular
        ArrayList<Character> newList = new ArrayList<>();
        int j = 0;
        for (Entry<Character, Integer> entry : frequencyListEntry) {
            newList.add(j, entry.getKey());
            j++;
        }

        //combine sortedMap and EnglishFrequency to get a newMap of corresponding characters to replace by
        HashMap<Character, Character> newMap = new HashMap<Character, Character>(sortedHashedMap.size());
        for (int k = 0; k < inputList.size(); k++) {
            if (Character.isUpperCase(inputList.get(k))) {
                newMap.put(inputList.get(k), newList.get(k));
            } else {
                newMap.put(inputList.get(k), inputList.get(k));
            }
        }

        // print key
        System.out.println("\nThe MAP/KEY of C -> P following English Frequency: ");
        System.out.println(newMap);

        //replace the input message
        ArrayList<Character> newLettersArr = new ArrayList<>();
        for (int l = 0; l < lettersList.size(); l++) {
            if (newMap.containsKey(lettersList.get(l))) {
                newLettersArr.add(l, newMap.get(lettersList.get(l)));
            }
        }
        //print the prediction following English Frequency
        System.out.println("The result of message following English Frequency:");
        for (char c : newLettersArr) {
            System.out.print(c);
        }
        System.out.println('\n');

        //update and print out the final key map
        System.out.println();
        System.out.println("The final Key Map");
        newMap.replace('F', 'B');
        newMap.replace('C', 'I');
        newMap.replace('U', 'N');
        newMap.replace('X', 'U');
        newMap.replace('E', 'O');
        newMap.replace('I', 'L');
        newMap.replace('A', 'C');
        newMap.replace('O', 'G');
        newMap.replace('R', 'F');
        newMap.replace('W', 'J');
        newMap.replace('L', 'P');
        newMap.replace('N', 'M');
        newMap.replace('D', 'K');
        newMap.replace('T', 'Q');
        newMap.replace('M', 'Y');
        newMap.replace('J', 'X');
        System.out.println(newMap);

        // decrypt the message
        newLettersArr.clear();
        for (int l = 0; l < lettersArr.length; l++) {
            if (newMap.containsKey(lettersList.get(l))) {
                newLettersArr.add(l, newMap.get(lettersList.get(l)));
            }
        }
        System.out.println("The plaintext is solve: ");
        //print the result
        for (char c : newLettersArr) {
            System.out.print(c);
        }
        System.out.println("\n");

    }
}
