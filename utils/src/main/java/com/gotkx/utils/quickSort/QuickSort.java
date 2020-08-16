package com.gotkx.utils.quickSort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arrs = {1, 9, 5, 7, 9, 2, 10, 14, 8, 77, 66, 99};
        quickSort(arrs);
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(arrs[i] +",");
        }
    }

    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        // 将数组分为两部分
            qsort(arr, low, pivot - 1);                   // 递归排序左子数组
            qsort(arr, pivot + 1, high);                  // 递归排序右子数组
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];               // 枢轴记录
        //System.out.println("枢轴值 :" + pivot);
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                --high;
            }
            arr[low] = arr[high];           // 交换比枢轴小的记录到左端
            System.out.println("arr[low]值：" + arr[low] +" ,pivot的值：" + pivot);

            while (low < high && arr[low] <= pivot) {
                ++low;
            }
            arr[high] = arr[low];           // 交换比枢轴小的记录到右端
        }
        // 扫描完成，枢轴到位
        arr[low] = pivot;
        // 返回的是枢轴的位置
        System.out.println("最终arr[low] 的值：" + arr[low]  +", 最终low值: " + low);
        return low;
    }

}
