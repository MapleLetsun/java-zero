package Leetcode;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 数据结构
 */
public class DataStructure {

    public static void main(String[] args) {
        int[] nums1={1,2};

        int[] nums2={1,1};


        int[][] ns= {{1,2},{3,4}};
        char[][] c={
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};


        System.out.println(isValidSudoku(c));
        System.out.println("end");
    }

    private static  void test(){

    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    private static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int m=i+1;m<nums.length;m++){
                if(target-nums[i]==nums[m]){
                    return new int[]{i, m};
                }
            }
        }
        return null;
    }

    /**
     * 36. 有效的数独
     * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     */
    private static boolean isValidSudoku(char[][] board) {
        //1.找到相同数字所在的坐标
        Map<Character,List<int[]>> total=new HashMap<>(81);
        Character ch;
        for(int x=0;x<board.length;x++){
            char[] child=board[x];
            for(int y=0;y<child.length;y++){
                List<int[]> fatherList=new ArrayList<>();
                ch=child[y];
                if(Character.isDigit(ch)){
                   if(total.containsKey(ch)){
                       total.get(ch).add(new int[]{x+1,y+1});
                   }else{
                       fatherList.add(new int[]{x+1,y+1});
                       total.put(ch,fatherList);
                   }
                }
            }
        }

        //2.拆解map 分析坐标是否符合要求
        Set<Integer> checkX;
        Set<Integer> checkY;
        Set<Integer> checkArea;
        int x;
        int y;
        int area;
        for (List<int[]> arr:total.values()){
            if(arr.size()==1){
                continue;
            }

            checkX=new HashSet<>();
            checkY=new HashSet<>();
            checkArea=new HashSet<>();
            for(int[] num:arr){
                x=num[0];
                y=num[1];
                area=(y-1)/3*3+(x-1)/3+1;
                if(checkX.contains(x) || checkY.contains(y) || checkArea.contains(area)){
                    return false;
                }
                checkX.add(x);
                checkY.add(y);
                checkArea.add(area);
            }
        }
        return true;
    }

    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     */
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=0;i< nums2.length;i++){
            nums1[m+i]=nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */
    private static int maxSubArray(int[] nums) {
        /*int total=0;
        int  max=nums[0];

        for(int i=0;i<nums.length;i++){
            total=nums[i];
            if(total>max){
                max=total;
            }
            for(int m=i+1;m<nums.length;m++){
                total+=nums[m];
                if(total>max){
                    max=total;
                }
            }
        }*/

        //动态规划 f(i)=max(f(i-1)+a,a);
        //贪心法 小于0归0,大于0相加比较赋值
        int total=0;
        int max=nums[0];

        for(int num:nums){
            if((total+num)>num){
                total+=num;
            }else{
                total=num;
            }

            if(total>max){
                max=total;
            }
        }
        return max;
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     */
    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList<List<Integer>>(numRows);
        List<Integer> nl=null;
        for(int m=0;m<numRows;m++){
            nl=new  ArrayList<Integer>(m+1);
            for(int n=0;n<m+1;n++){
                if(n==0){
                    nl.add(1);
                }else if(n==m){
                    nl.add(1);
                    break;
                }else{
                    List<Integer> upperFloor=result.get(m-1);
                    nl.add(upperFloor.get(n)+upperFloor.get(n-1));
                }
            }
            result.add(nl);
        }
        return result;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     */
    private static int maxProfit(int[] prices) {
        int money= 0;
        int now = prices[0];
        int max = prices[0];

        for(int p:prices){
            if(p>now){
                max=p;
                if((max-now)>money){
                    money=max-now;
                }
            }else if(p<now){
                now=p;
            }
        }

        return money<0?0:money;
    }

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    private static boolean containsDuplicate(int[] nums){
       /* for(int i=0;i<nums.length;i++){
            for (int m=i+1;m<nums.length;m++){
                if(nums[i]==nums[m]){
                    return true;
                }
            }
        }*/
       // hash sort
        HashSet numSet=new HashSet<>();
        for(int num:nums){
            if(numSet.contains(num)){
                return true;
            }else{
                numSet.add(num);
            }
        }
        return false;
    }

    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     */
    private static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map=new HashMap<Integer, Integer>(100);

        if(nums1.length<nums2.length){
            int[] nums3=nums2;
            nums2=nums1;
            nums1=nums3;

        }

        List<Integer> result = new ArrayList<>(nums2.length);
        for(int i=0;i<nums1.length;i++){
            map.put(i,nums1[i]);
        }
        for(Integer num:nums2){
            if (map.containsValue(num)){
                result.add(num);
                map.values().remove(num);
            }
        }

        int[] arr=new int[result.size()];
        for(int i=0;i<result.size();i++){
            arr[i]=result.get(i);
        }

        return arr;
    }

    /**
     * 566. 重塑矩阵
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     */
    private static int[][] matrixReshape(int[][] mat, int r, int c) {
        int total=mat.length*mat[0].length;
        if(total!=r*c){
            return mat;
        }

        int[] all=new int[total];
        int i=0;
        for(int[] arr:mat){
            for(int num:arr){
                all[i++]=num;
            }
            if(i==total){
                break;
            }
        }

        Arrays.sort(all);

        int[][] result=new int[r][c];
        i=0;
        for(int m=0;m<r;m++){
            int[] children=new int[c];
            for(int n=0;n<c;n++){
                children[n]=all[i++];
            }
            result[m]=children;
        }

        return result;
    }

}
