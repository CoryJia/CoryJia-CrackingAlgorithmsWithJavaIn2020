package basicalgo.s1binarysearch;

/**
 * @author coryjia@gmail.com
 * @date 3/19/21 4:54 PM
 */
public class LC34FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1, -1};

        int startIndex = findStartIndex(nums, target);
        int lastIndex = findLastIndex(nums, target);

        return new int[]{startIndex, lastIndex};
    }

    private int findStartIndex(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = 0;

        while (l + 1 < r) {
            mid = l + (r - l) / 2;

            if (nums[mid] >= target) r = mid;
            else l = mid;
        }

        if(nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }

    private int findLastIndex(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = 0;

        while (l + 1 < r) {
            mid = l + (r - l) / 2;

            if (nums[mid] <= target) l = mid;
            else r = mid;
        }

        if (nums[r] == target) return r;
        if (nums[l] == target) return l;
        return -1;
    }
}
