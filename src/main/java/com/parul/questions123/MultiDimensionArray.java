package com.parul.questions123;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MultiDimensionArray {

    // This is a provided function, Assume it works
    public Long getValue(int... indexOfDimension) {
        //...
        return Long.valueOf(1);
    }

    /**
     * Resolution - Using two Array List of Strings, By append operation calculate each possible index and
     * then the sum.
     * Complexity - O(product of length of all dimensions).
     * Space Complexity - O(product of length of all dimensions) to store all indexes.
     * @param mArray
     * @param lengthOfDimension
     * @return
     */
    public static long sum(MultiDimensionArray mArray, int[] lengthOfDimension) throws ZeroDimensionLengthException {
        if(lengthOfDimension.length == 0) {
            throw new ZeroDimensionLengthException("Length of Dimension should be greater than 0");
        }
        int numDimensions = lengthOfDimension.length - 1;

        List<String> finalList = new ArrayList<>();
        finalList.add("");
        List<String> tempList = new ArrayList<>();

        while (numDimensions >= 0) {
            for (int i = 0; i < lengthOfDimension[numDimensions]; i++) {
                for (String s : finalList) {
                    tempList.add(i + s);
                }
            }
            numDimensions--;
            finalList.clear();
            finalList.addAll(tempList);
            tempList.clear();
        }

        long sum = 0L;
        int[] indexes = new int[lengthOfDimension.length];
        for(String string:finalList) {
            for(int i=0;i<string.length();i++) {
                indexes[i] = Character.getNumericValue(string.charAt(i));
            }
            sum += mArray.getValue(indexes);
        }



        return sum;
    }

    /**
    * Custom Exception to throw in case of zero dimensions is passed.
    */
    public static class ZeroDimensionLengthException extends Exception {
        public ZeroDimensionLengthException() {
        }

        public ZeroDimensionLengthException(String message) {
            super(message);
        }
    }

    /**
     * Test case to check sum function for zero dimension.
     */
    @Test(expected = ZeroDimensionLengthException.class)
    public void checkSumForZeroDimension() throws ZeroDimensionLengthException {
        MultiDimensionArray multiDimensionArray = new MultiDimensionArray();
        sum(multiDimensionArray, new int[]{});
    }

    /**
     * Test case to check sum function for non zero dimensions.
     */
    @Test
    public void checkSum() throws ZeroDimensionLengthException {
        MultiDimensionArray multiDimensionArray = new MultiDimensionArray();

        long sum = sum(multiDimensionArray, new int[]{2});
        Assert.assertEquals(2, sum);

        sum = sum(multiDimensionArray, new int[]{2, 3});
        Assert.assertEquals(2*3, sum);

        sum = sum(multiDimensionArray, new int[]{2, 3, 4});
        Assert.assertEquals(2*3*4, sum);

        sum = sum(multiDimensionArray, new int[]{2, 3, 4, 5});
        Assert.assertEquals(2*3*4*5, sum);

    }

}
