package com.gotkx.utils.bubbleSort;

public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99, 1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99,
                1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99, 1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99, 1, 9, 5,
                7, 9, 2, 10, 14, 8, 77, 66, 99};
        long startTime = System.currentTimeMillis();
        bubbleSort(nums);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) + "ms");


        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                System.out.print(nums[i]);
            } else {
                System.out.print(nums[i] + ", ");
            }
        }

    }

    public static void bubbleSort(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }

        //主逻辑
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

    }

}
