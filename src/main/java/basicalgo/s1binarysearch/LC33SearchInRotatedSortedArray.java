package basicalgo.s1binarysearch;

/**
 * @author coryjia@gmail.com
 * @date 2/22/21 5:51 PM
 */
public class LC33SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            int temp = nums[mid];
            if (temp == target) return mid;
            if (temp >= nums[left]){
                if (target < temp && target >= nums[left]) right = mid - 1;
                else left = mid + 1;
            }else {
                if (target > temp && target < nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }
}
