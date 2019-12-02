public class RangeSumQueryImutable {
    // sum 的 index 位置， 存储 0-index位置元素的和
    private int[] sums;
    public RangeSumQueryImutable(int[] nums) {
        if (nums == null){
            throw new IllegalArgumentException("sdf");
        }
        if(nums.length==0){
            return;
        }
        sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            sums[i] = sums[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if(i==0){
            return sums[j];
        }
        return sums[j] - sums[i-1];
    }
}
