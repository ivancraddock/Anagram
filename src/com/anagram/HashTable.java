package com.anagram;

   import java.io.*;
   import java.util.*;
	
/*
Ivan Craddock
CSCD 300 Homework 5
*/

   public class HashTable 
   {
      public LinkedList<Anagram> [] hashList = new LinkedList[199999];
      private int collisions; 
      private int totalWords; 
      private Scanner fin;
   
      public HashTable(Scanner fileIn, String hashType) 
      {
         this.collisions = 0;
         this.totalWords = 0; 
         this.fin = fileIn; 
         loadTable(hashType);
      }
   
      public void loadTable(String hashType)
      {
         String temp = " ";
         Anagram tempA; 
      	
         while(this.fin.hasNext())
         {
            temp = this.fin.nextLine().trim();
         
            if(isWord(temp))
            {
               tempA = new Anagram(temp);
               int tempHV;
            
               if(hashType.equals("custom hash"))
                  tempHV = Math.abs(tempA.getHashValue());
               else
                  tempHV = (Math.abs(tempA.getWord().hashCode())) % 19999;	
               if(!(hashList[tempHV] == null))
               {
                  if (!tempA.getAlphagram().equals(hashList[tempHV].peekFirst().getAlphagram()))
                  {
                     this.collisions++;
                     hashList[tempHV].add(tempA);
                     this.totalWords++;
                  }
                  else 
                  {
                     if(!hashList[tempHV].contains(tempA))
                     {
                        hashList[tempHV].add(tempA);
                        this.totalWords++;
                     }
                  }// end if it does or doesn't 
               }
               else   // no list exists ther yet 
               {
                  hashList[tempHV] = new LinkedList<Anagram>();// create a new list 
                  hashList[tempHV].add(tempA);//adding to the list already existing or new 
                  this.totalWords++;
               }
            }
         }
         if(hashType.equals("custom hash"))
            System.out.println("*** My Hash Function ****");
         else
            System.out.println("**** API Hash Function ******");
      	
         System.out.println("Total Words: " + this.totalWords);
         System.out.println("Total Collision: " + this.collisions);
         System.out.println();
      }
      private boolean isWord(String wordIn)
      {
         boolean temp = true; 
         wordIn = wordIn.toLowerCase();
      
         for(int i = 0; i < wordIn.length(); i++)
         {
            char j = wordIn.charAt(i);
         
            if (j > 'z' || j < 'a')
               temp = false;
         }
         return temp;
      }
      public void processAnagrams(Scanner anaIn, PrintStream fout)
      {
         String temp; 
         Anagram tempA; 
         int hashIndex, anaCount; 
      
         while(anaIn.hasNext())
         {
            anaCount = 0;
            temp = anaIn.nextLine().trim();
         
            if(isWord(temp))
            {
               tempA = new Anagram(temp);
            
               fout.print(tempA.getWord() + " " );
            
               hashIndex = tempA.getHashValue();
            
               if(this.hashList[hashIndex] ==null)
               {
                  fout.println("0");
               }
               else
               {
                  String tempAnaString = "";
                  for(int i = 0; i < this.hashList[hashIndex].size(); i++)
                  {
                     Anagram temps = this.hashList[hashIndex].get(i);
                  
                     if(tempA.getAlphagram().equals(temps.getAlphagram()))
                     {
                        if(!tempA.getWord().equals(temps.getWord()))
                        {
                           tempAnaString += temps.getWord() + " ";
                           anaCount++;
                        }
                     }
                  }
                  fout.println(anaCount+ " " + tempAnaString);
               }
            }
         }
      }
   }


















