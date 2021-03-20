package basicalgo.s1binarysearch;

/**
 * @author coryjia@gmail.com
 * @date 2/22/21 5:25 PM
 */
public class ClassicalBinarySearch {
    public int binarySearch(final int[] arr, final int target) {
        int left = 0, right = arr.length - 1, mid = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -(left + 1); // the same as Java library
    }

    // need post processing
    private int binarySearch2(final int[] arr, final int target) {
        int left = 0, right = arr.length - 1, mid = 0;

        while (left + 1 < right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (arr[left] == target) return left;
        if (arr[right] == target) return right;
        return -1;
    }

    private int binarySearch3(final int[] arr, final int target) {
        int left = 0, right = arr.length, mid = 0; // right == length, not length -1

        while (left < right) {
            mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -left;
    }
}
