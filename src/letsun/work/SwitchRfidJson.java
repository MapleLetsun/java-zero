package letsun.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 平板json数据括号转义
 */
public class SwitchRfidJson {

    public static void main(String[] args) {
        SwitchRfidJson check=new SwitchRfidJson();
//		check.createCode();
        check.method1();
    }

    public void method1() {
        try {
            // 1.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File("E://stack.txt")), "UTF-8"));
            String lineTxt = null;

            Integer count = 0;

            Map<String, String> replace = new HashMap<>();
            replace.put("{", "%7b");
            replace.put("}", "%7d");
            replace.put("[", "%4b");
            replace.put("]", "%4d");

            StringBuffer str = new StringBuffer();

            while ((lineTxt = br.readLine()) != null) {
                lineTxt = replaceChar(lineTxt);
                lineTxt = replaceAllBlank(lineTxt);
                str.append(lineTxt);
            }

            String result = str.toString();

            str = new StringBuffer();
            for (int i = 0; i < result.length(); i++) {
                if (i < 2 || i > (result.length() - 2)) {
                    str.append(result.charAt(i));
                } else {
                    if (result.charAt(i - 2) == '-' && result.charAt(i + 3) == ':') {
                        str.append(result.charAt(i) + " ");
                    } else {
                        str.append(result.charAt(i));
                    }
                }
            }

            System.out.println(str.toString());

            // 输出到指定文件
            File f = new File("E:\\code.txt");
            PrintWriter pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

            pw.close();
            br.close();
            System.out.println("End!");
        } catch (Exception e) {

        } finally {

        }

    }

    public void createCode() {
        int code=801;
//		int stack="d-200132550008831";
        StringBuffer str=new StringBuffer();
        for(int i=0;i<20;i++) {
            if(i%10==0) {
                str.append("{");
//				str.append("\"stackPosition\": \""+stack+"\",");
                str.append("\"stackPosition\": \"201811010000000100019489\",");
                str.append("  \"stackRealNum\": 10,");
                str.append("  \"codeLabels\": [");
//				stack=addOne(stack);
            }else {
                str.append("{");
                str.append("  \"code\": \"5203658010356"+code+"\",");
                str.append(" \"createTimeStr\": \"2020-08-26 14:25:35\"");
                str.append("},");
                code++;
            }
            if(i/10==9){
                str.append("],");
            }
        }
        System.out.println(str.toString());

    }

    /**

     * 字符串+1方法，该方法将其结尾的整数+1,适用于任何以整数结尾的字符串,不限格式，不限分隔符。
     * @author zxcvbnmzb
     * @param testStr 要+1的字符串
     * @return +1后的字符串
     * @exception NumberFormatException
     */
    public static String addOne(String testStr){
        String[] strs = testStr.split("[^0-9]");//根据不是数字的字符拆分字符串
        String numStr = strs[strs.length-1];//取出最后一组数字
        if(numStr != null && numStr.length()>0){//如果最后一组没有数字(也就是不以数字结尾)，抛NumberFormatException异常
            int n = numStr.length();//取出字符串的长度
            int num = Integer.parseInt(numStr)+1;//将该数字加一
            String added = String.valueOf(num);
            n = Math.min(n, added.length());
            //拼接字符串
            return testStr.subSequence(0, testStr.length()-n)+added;
        }else{
            throw new NumberFormatException();
        }
    }

    // 去除所有空格
    public static String replaceAllBlank(String str) {
        String s = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            /*
             * \n 回车(\u000a) \t 水平制表符(\u0009) \s 空格(\u0008) \r 换行(\u000d)
             */
            Matcher m = p.matcher(str);
            s = m.replaceAll("");
        }
        return s;
    }

    public static String replaceChar(String str) {
        String s = "";

        Map<String, String> replace = new HashMap<>();
        replace.put("{", "%7b");
        replace.put("}", "%7d");
        replace.put("[", "%5b");
        replace.put("]", "%5d");

        if (str != null) {
            s = str;
            for (Map.Entry<String, String> entry : replace.entrySet()) {
                s = s.replace(entry.getKey(), entry.getValue());
            }
        }
        return s;
    }
}
