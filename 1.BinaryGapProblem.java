package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*****************************
 * 
 * 
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros 
 * that is surrounded by ones at both ends in the binary representation of N.
For example, number 9 has binary representation 1001 and contains a binary gap of length 2. 
The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. 
The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. 
The number 32 has binary representation 100000 and has no binary gaps.

Write a function:
class Solution { public int solution(int N); }
that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. 
Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..2,147,483,647].
Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 
 
 
 * 
 * 
 * @author gopalakrishnanc
 *
 */

class BinaryGapProblem {

	public static int solution (int N) {
		
		
		int arr [] = getBinaryValues(N);
		
		int count =0;
		ArrayList<Integer> gapList = new ArrayList();
		int startIndex = arr.length-1;

		for ( int i = arr.length - 1; i >=0 ; i-- ) {
		
			
			if (arr[startIndex]  ==1  && startIndex != i && arr[i] == 0) {
				++count;
			}
			else if (arr[startIndex] ==1 && startIndex != i &&  arr[i] == 1) {
				gapList.add(count);
				
				//reset the counter 
				count  = 0;
				
				//set the i value the startIndex
				startIndex = i;
			}
		}
		

		//now sort the arraylist in descending order
		Collections.sort(gapList,Collections.reverseOrder());
		
		//return the sort arraylist of first element
		return null != gapList && gapList.size() > 0 ? gapList.get(0): 0;
	}
	
	
	//get the binary values for the given number
	public static int[] getBinaryValues(int N) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		/*
		if (N==1) {
			queue.add(new Integer(N));
		}
		*/
		while ( N != 1) {
			
			//find the remainder and the quotient 
			int newN =  N / 2;
			int remainderN =  N % 2;

			
			queue.add(new Integer(remainderN));
			
			//swap new value to old value
			N = newN;
		}
		queue.add(new Integer(N));

		
		//convert stack to array
		
		return getArrayFromStack(queue);
	}

	
	//convert the stack to array
	public static int[] getArrayFromStack( Queue<Integer> queue) {

		int arr[] =  new int [queue.size()];
		int index = 0;
		while(!queue.isEmpty()) {
			 arr[index++] = queue.poll();
		}
		
		return arr;
	}
	
	public static void main(String args[]) {
		
		System.out.println(solution(529));
	}
}

