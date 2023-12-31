package com.meghabassi.neetcode150;

/**
 * @mbassi
 * https://leetcode.com/problems/binary-search
 */
public class BinarySearch {
    private int[]  sortedIntArray;

    public BinarySearch(int[] sortedIntArray){
        this.sortedIntArray=sortedIntArray;

    }

    public int binarySearch(int target){
        int left=0;
        int right=sortedIntArray.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(sortedIntArray[mid]==target)
                return mid;
            if(sortedIntArray[mid]<target)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;

    }
}
