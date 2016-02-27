package com.anagram;

   import java.io.*;
   import java.util.*;

/*
Ivan Craddock
CSCD 300 Homework 5


The API hash function detects 10,713 collisions on my computer
The hashing function from class detects 1495 collisions on my computer, indicating better performance than O*(log2 n)

Input is taken from a file called "input.txt"
Output is written to a file called "output.txt"

A custom input file can be used as a command line argument for this program.
 */

   public class Tester {
      public static void main(String...args) throws IOException {
         String fname;
         if(args.length != 0) {
            fname = args[0];
         } 
         else {
            fname = "input.txt";
         }
      	try{
         Scanner file = new Scanner(new File("dictionary.txt"));
         Scanner file2 = new Scanner(new File("dictionary.txt"));
         HashTable table = new HashTable(file,"custom hash");
         HashTable table2 = new HashTable(file2,"default");
		 Scanner burn = new Scanner(new File(fname));
         FileOutputStream out = new FileOutputStream("output.txt");
      
         PrintStream ps = new PrintStream(out);
         table.processAnagrams(burn, ps);
         table2.processAnagrams(burn, ps);
			}catch(Exception e){
				System.out.println("Error: Unable to open file");
			}
      }
   }
