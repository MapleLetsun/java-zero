package Leetcode;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 数据结构
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution =new Solution();

//        List<String> listStr=solution.removeInvalidParentheses("(r(()()(");
//        for(String str:listStr){
//            System.out.println(str);
//        }

        System.out.println("end");
    }

    public  void test(){

    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    public int[] twoSum(int[] nums, int target) {
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
    public boolean isValidSudoku(char[][] board) {
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
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=0;i< nums2.length;i++){
            nums1[m+i]=nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */
    public int maxSubArray(int[] nums) {
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
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * 进阶：

     * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个仅使用常量空间的解决方案吗？
     */
    public <Char> void setZeroes(int[][] matrix) {
        Set lineNum=new HashSet<Integer>();
        int[] lineZero = new int[matrix[0].length];
        for(int i=0;i<matrix[0].length;i++){
            lineZero[i]=0;
        }
        boolean flag;

        for (int n=0;n<matrix.length;n++){
            flag=false;
            for(int m=0;m<matrix[n].length;m++){
                if(matrix[n][m]==0){
                    flag=true;
                    lineNum.add(m);
                    for(int r=n-1;r>=0;r--){
                        matrix[r][m]=0;
                    }
                }else{
                    if(!flag && lineNum.contains(m)){
                        matrix[n][m]=0;
                    }
                }
            }
            if(flag){
                matrix[n]=lineZero;
            }
        }
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     */
    public List<List<Integer>> generate(int numRows) {
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
    public int maxProfit(int[] prices) {
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
    public boolean containsDuplicate(int[] nums){
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
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     */
    public boolean isAnagram(String s, String t) {
        char[] sc=s.toCharArray();
        char[] tc=t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        return String.valueOf(sc).equals(String.valueOf(tc));
    }

    /**
     * 301. 删除无效的括号
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     */
    public List<String> removeInvalidParentheses(String s) {
      /*  Solution solution=new Solution();
        List<Set<String>> section=new ArrayList<>();
        Set<String> strSet;
        int start=0;
        int end=-1;
        int balance=0;

        //遍历处理多余右括号
        char[] sc= s.toCharArray();
        for(int i=0;i<sc.length;i++){
            if (sc[i] =='(') {
                balance++;
            }else if(sc[i]==')'){
                balance--;
                end=i;
            }else{
                end=i;
            }

            if(balance<0){
                strSet=solution.getTotalResult(s.substring(start,end+1),false);
                if(strSet.size()>0){
                    section.add(strSet);
                }
                //记录位置
                start=i+1;
                balance=0;
            }
        }

        //获取最后的字符串
        if(end>=start){
            String tail=s.substring(start);
            if(balance==0){
                strSet=new HashSet<>(1);
                strSet.add(tail);
                section.add(strSet);
            }else if(balance>0){
                // 最后多余左括号倒置
                tail=new StringBuffer(tail).reverse().toString();
                tail=tail.replace('(','#');
                tail=tail.replace(')','(');
                tail=tail.replace('#',')');

                start=0;
                end=-1;
                balance=0;
                sc= tail.toCharArray();
                List<Set<String>> tailList=new ArrayList<>();
                for(int i=0;i<sc.length;i++){
                    if (sc[i] =='(') {
                        balance++;
                    }else if(sc[i]==')'){
                        balance--;
                        end=i;
                    }else{
                        end=i;
                    }

                    if(balance<0){
                        strSet=solution.getTotalResult(tail.substring(start,end+1),true);
                        if(strSet.size()>0){
                            tailList.add(strSet);
                        }
                        //记录位置
                        start=i+1;
                        balance=0;
                    }
                }
                if(balance == 0 && end>=start) {
                    //倒置
                    String tailTail = new StringBuffer(tail.substring(start)).reverse().toString();
                    tailTail=tailTail.replace('(','#');
                    tailTail=tailTail.replace(')','(');
                    tailTail=tailTail.replace('#',')');
                    strSet = new HashSet<>(1);
                    strSet.add(tailTail);
                    tailList.add(strSet);
                }
                if(tailList.size()>0){
                    //list倒置
                    Collections.reverse(tailList);
                    for(Set<String> tailSet:tailList){
                        section.add(tailSet);
                    }
                }
            }
        }

        Set<String> resultSet=new HashSet<>();
        Set<String> betweenSet=new HashSet<>();
        for(Set<String> secSet:section){
            for(String sec:secSet){
                if(resultSet.isEmpty()){
                    betweenSet.add(sec);
                }else{
                    for(String finalStr:resultSet){
                        betweenSet.add(finalStr+sec);
                    }
                }
            }
            resultSet=betweenSet;
            betweenSet=new HashSet<>();
        }


        return null;
    }
   /* private Set<String> getTotalResult(String str,Boolean reverse){
        Set<String> strSet=new HashSet<>();
        //返回所有正确的结果
        StringBuffer sb=new StringBuffer(str);
        String reverseStr;
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)==')'){
                sb.delete(i,i+1);
                if(sb.length()>0){
                    if(reverse){
                        reverseStr=sb.reverse().toString();
                        reverseStr=reverseStr.replace('(','#');
                        reverseStr=reverseStr.replace(')','(');
                        reverseStr=reverseStr.replace('#',')');
                        strSet.add(reverseStr);
                        sb.reverse();
                    }else{
                        strSet.add(sb.toString());
                    }
                }
                sb.replace(i,i,")");
            }
        }

        return strSet;
        */
        return null;
    }

    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
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
     * 383. 赎金信
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        boolean result=true;

        for(char c:ransomNote.toCharArray()){
            if(!magazine.contains(String.valueOf(c))){
                result=false;
                break;
            }
            magazine=magazine.substring(0,magazine.indexOf(String.valueOf(c)))+magazine.substring(magazine.indexOf(String.valueOf(c))+1);
        }

        return result;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public int firstUniqChar(String s) {
        Set check=new HashSet<Character>(s.length());
        Set result = new HashSet<Character>(s.length());
        for(int i=0;i<s.length();i++){
            if(check.contains(s.charAt(i))){
                result.add(s.charAt(i));
            }else{
                check.add(s.charAt(i));
            }
        }
        for(int i=0;i<s.length();i++){
            if(!result.contains(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    /**
     * 496. 下一个更大元素 I
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int num;
        boolean compare;
        for(int n=0;n<nums1.length;n++){
            num=nums1[n];
            compare=false;
            for(int m=0;m<nums2.length;m++){
                if(nums2[m]==num){
                    compare=true;
                }
                if(compare){
                    if(nums2[m]>num){
                        nums1[n]=nums2[m];
                        break;
                    }
                }
            }
            if (num==nums1[n]){
                nums1[n]=-1;
            }
        }
        return nums1;
    }

    /**
     * 500. 键盘行
     * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
     * 美式键盘 中：
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     */
    public String[] findWords(String[] words) {
        String line1="qwertyuiop";
        String line2="asdfghjkl";
        String line3="zxcvbnm";

        StringBuffer sb=new StringBuffer();
        String cs;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        for(String word:words){
            flag1=true;
            flag2=true;
            flag3=true;
            for(char c:word.toCharArray()){
                cs=String.valueOf(Character.toLowerCase(c));

                if(flag1 && !line1.contains(cs)){
                    flag1=false;
                }
                if(flag2 && !line2.contains(cs)){
                    flag2=false;
                }
                if(flag3 && !line3.contains(cs)){
                    flag3=false;
                }
            }

            if(flag1 || flag2 || flag3){
                sb.append(word).append(",");
            }
        }

        return sb.length()>0?sb.substring(0,sb.length()-1).split(","):new String[0];
    }

    /**
     * 566. 重塑矩阵
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
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
