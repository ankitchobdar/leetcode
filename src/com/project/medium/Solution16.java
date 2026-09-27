package com.project.medium;

import java.util.Arrays;

public class Solution16 {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
        System.out.println(threeSumClosest(new int[]{0,0,0}, 1));
        System.out.println(threeSumClosest(new int[]{7,8,9}, -1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length-2; i++) {
            int j = i + 1, k = nums.length - 1;

            while(j < k) {
                int res = nums[i] + nums[j] + nums[k];

                if(res == target)
                    return res;
                else  if(res < target)
                    j++;
                else
                    k--;
                if(Math.abs(res - target) < Math.abs(sum - target))
                    sum = res;
            }
        }
        return sum;
    }

    //Solution-2
    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;

        int minDiff = Integer.MAX_VALUE;
        int closest = 0;

        for (int i = 0; i < len - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = len - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = sum - target;
                int absDiff = diff < 0 ? -diff : diff;

                if (absDiff < minDiff) {
                    minDiff = absDiff;
                    closest = sum;
                }

                if (diff == 0) {
                    return sum;
                } else if (diff > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return closest;
    }
}
