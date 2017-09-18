package com.parul.questions123;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SeparateNumbers {

    //Recursive Solution checking each and every element if it can be included.
    //Time Complexity - O(k^n)
    //Space Complexity - O(n*k) (Doubtful)[Depends on function calls on Stack]
    public boolean separate(List<Integer> list, int k) {
        if (k == 1) {
            return true;
        }

        if (k > list.size()) {
            return false;
        }

        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int subsetSum = sum / k;

        boolean[] used = new boolean[list.size()];
        int[] subsets = new int[k];

        return isPartitionPossible(list, k, used, subsets, 0, subsetSum);
    }


    private boolean isPartitionPossible(List<Integer> list, int k, boolean[] used, int[] subsets,
                                        int currIndex, int subsetSum) {
        if (subsets[currIndex] == subsetSum) {
            if (currIndex == k - 2) {
                return true;
            }
            return isPartitionPossible(list, k, used, subsets, currIndex + 1, subsetSum);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (used[i] == false) {
                    int sum = subsets[currIndex] + list.get(i);
                    if (sum <= subsetSum) {
                        used[i] = true;
                        subsets[currIndex] = sum;

                        boolean isPossible = isPartitionPossible(list, k, used, subsets, currIndex, subsetSum);

                        used[i] = false;
                        subsets[currIndex] -= list.get(i);

                        if (isPossible) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    }

    @Test
    public void checkPartitionInitial() {
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(1);
            add(1);
            add(1);
        }};

        Assert.assertEquals(true, separate(list, 1));
        Assert.assertEquals(true, separate(list, 4));
        Assert.assertEquals(false, separate(list, 3));
        Assert.assertEquals(false, separate(list, 5));
    }

    @Test
    public void checkPartitionComplex() {
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(7);
            add(5);
            add(5);
            add(10);
        }};

        Assert.assertEquals(true, separate(list, 3));
        Assert.assertEquals(true, separate(list, 2));
        Assert.assertEquals(false, separate(list, 4));
    }
}
