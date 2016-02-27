package com.anagram;
/*Ivan Craddock
CSCD 300 Homework 5
 */

import java.math.BigInteger;

public class Anagram
{
	private String word; 
	private String alphagram;
	private char[] sortArr; 
	int hashValue; 
	
	public Anagram(String wordIn)
	{
		this.word = wordIn.toLowerCase();
		this.alphagram = sortTheWord(this.word);
		this.hashValue = 0;
		this.hasher();
	}
	
	public String getWord(){return this.word; }
	
	public String getAlphagram(){return this.alphagram;}
	
	public int getHashValue(){ return this.hashValue; }
	
	public static String sortTheWord(String wordIn)
	{
		String temp;
		char[] sortArr = wordIn.toCharArray();
		
		sortArr = MySort.sort(sortArr);
		
		temp = new String(sortArr);
		
		return temp;
	}
	
	public void hasher()
	{
		BigInteger hash = new BigInteger("3571");
		
		for(int i = 0; i < this.word.length(); i++)
		{
			int temp = (int) this.word.charAt(i);
			String temps = Integer.toString(temp);
			hash = hash.multiply(new BigInteger(temps));
		}
		hash = hash.abs();
		
		this.hashValue = hash.mod(new BigInteger("199999")).intValue();
	}
}