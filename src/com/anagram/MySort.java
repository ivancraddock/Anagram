package com.anagram;

/*
Ivan Craddock
CSCD 300 Homework 5
 */


public class MySort
{
	public static char[] sort(char[] sortArr)
	{
		int outerL, innerL, min; 
		
		for(outerL = 0; outerL < sortArr.length-1; outerL++)
		{
			min = outerL; 
			
			for(innerL = outerL +1; innerL < sortArr.length; innerL++)
			{
				if (sortArr[innerL] < sortArr[min])
					min = innerL; 
			}
			char temp = sortArr[min];
			sortArr[min]= sortArr[outerL];
			sortArr[outerL] = temp; 
		}
		
		return sortArr; 
	}
}