package com.gotkx.utils.exam;

public class quickSort {

    public static void main(String[] args) {
        int array[] = {1, 5, 3, 7, 4, 8, 2, 6, 9, 10};

        sort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    private static void sort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = work(array, low, high);
            sort(array, low, pivot - 1);
            sort(array, pivot + 1, high);
        }

    }

    private static int work(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] >= pivot) {
                --high;
            }
            array[low] = array[high];

            while (low < high && array[low] <= pivot) {
                ++low;
            }
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }
}
