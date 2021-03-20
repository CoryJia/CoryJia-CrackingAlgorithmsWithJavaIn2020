package basicalgo.s1binarysearch;

import java.util.Arrays;

/**
 * @author coryjia@gmail.com
 * @date 3/19/21 5:49 PM
 */
public class LC35SearchInsertPosition {
    public int searchInsert1(int[] arr, int target){
        int index = Arrays.binarySearch(arr, target);

        return index >= 0 ? index : -(index + 1);
    }

    public int searchInsert2(int[] arr, int target) {
        if(arr == null || arr.length == 0) return 0;

        int l = 0, r = arr.length - 1, mid = 0;

        while(l <= r){
            mid = l + (r - l) / 2;

            if (arr[mid] == target) return mid;
            else  if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }

        return l;
    }
}
