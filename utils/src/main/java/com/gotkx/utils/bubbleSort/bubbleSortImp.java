package com.gotkx.utils.bubbleSort;

public class bubbleSortImp {

    public static void main(String[] args) {
        int[] nums = {1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99};
        long startTime  = System.currentTimeMillis();
        bubbleSort(nums);
        long endTime  = System.currentTimeMillis();
        //System.out.println("总耗时：" + (endTime - startTime) + "ms");

        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                System.out.print(nums[i]);
            } else {
                System.out.print(nums[i] + ", ");
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        int j, k,count = 0;
        int flag = nums.length;

        while (flag > 0) {
             count++;
            //System.out.println("执行while的次数：" +count);
            //System.out.println("边界值:  " + flag);
            k = flag;
            flag = 0;

            for (j = 1; j < k; j++) {
                System.out.println("K 值" + k);
                //System.out.println("进入for循环");
                // 前面的数字大于后面的数字就交换
                if (nums[j - 1] > nums[j]) {
                    System.out.println("前一个值：" + nums[j - 1] + "后一个值："+nums[j]);
                    // 交换a[j-1]和a[j]
                    int temp;
                    temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;

                    // 表示交换过数据;
                    flag = j;// 记录最新的尾边界.
                    System.out.println("最新边界值:  " + flag);
                }
            }

            System.out.println("结束for循环： " + count);

        }


    }

}
