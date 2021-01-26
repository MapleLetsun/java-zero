package letsun.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出excel类的vo对象添加注解
 */
public class ExportExcel {
    private static final Integer ONE = 1;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File("E://A.txt")), "UTF-8"));
            String lineTxt = null;
            int a=0;
            String str=null;
            while ((lineTxt = br.readLine()) != null) {
				/*if(a%2==0) {
					str=lineTxt.substring(lineTxt.indexOf('*')+3, lineTxt.lastIndexOf('*')-1);
				}else {*/
                if((lineTxt!=null && lineTxt.length()>0)) {
                    if(lineTxt.contains("*")) {
                        if(!lineTxt.contains("\\") && !lineTxt.contains("/")) {
                            str=lineTxt.replace("*", "").trim();
                        }
                    }else {
                        String[] strArr=lineTxt.trim().split(" ");
                        String value1=strArr[1];
                        String value2=strArr[2].substring(0,strArr[2].length()-1);
                        String value3=value2.substring(0,1).toUpperCase()+value2.substring(1);

                        //get  set 方法
                        System.out.println("public void set"+value3+"("+value1 +" "+value2+") {");
                        System.out.println("this."+value2+"="+value2+";");
                        System.out.println("}");
                        System.out.println("@ExcelField(title = \""+ str +"\", align = 2, sort = "+ (a+=10) +")");
                        System.out.println("public "+ value1 +" get"+ value3 +"() {");
                        System.out.println("return "+ value2+";");
                        System.out.println("}");
//
                        //对象转换
//					setMethod("excelVo","order",value3);

                        //数量
//					System.out.println("Integer "+value2+"=value2.getScheduleCount(companyId)");
//					System.out.println("scheduleCountVo.set"+value3+"("+value2+"!=null?"+value2+":0)");

                    }
                }
//				a++;
//				public void setProcInsId(String procInsId) {
//					this.procInsId = procInsId;
//				}
//
//				@ExcelField(title = "关联申请单", align = 2, sort = 6)
//				public String getApplyNo() {
//					return applyNo;
//				}

//				String[] names = lineTxt.split(",");
//				for (String name : names) {
//					if (map.keySet().contains(name)) {
//						map.put(name, (map.get(name) + ONE));
//					} else {
//						map.put(name, ONE);
//					}
//				}
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
    }
    private static void setMethod(String vo,String entity,String value) {
        System.out.println(vo+".set"+ value +"("+entity+".get"+value+"());");

    }

    private static void setMethod(String vo,String entity,String value,String zero) {
        if(zero.equals("list")) {
//			System.out.println(value);
            String copy=value.toString();
            StringBuilder sb=new StringBuilder(value);
            for(int i=0;i<sb.length();i++) {
                char c=sb.charAt(i);
                if(!Character.isLowerCase(c)) {
                    //是大写
                    if(i==0) {
                        c+=32;
                        String str=String.valueOf(c);
                        sb.replace(i,i+1,str);
                    }else {
                        c+=32;
                        String str="_"+String.valueOf(c);
                        sb.replace(i,i+1,str);
                        i=i+1;
                    }
                }

            }
            if(vo.equals("item")) {
                System.out.println("LODOP.SET_PRINT_STYLEA(\""+sb.toString()+"\" + i, \"CONTENT\", "+vo+"."+value+");");

            }else {
                System.out.println("LODOP.SET_PRINT_STYLEA(\""+sb.toString()+"\", \"CONTENT\", "+vo+"."+value+");");
            }
        }else {
            System.out.println(vo+".set"+ value +"(\"\");");
        }
    }
}



/* 输出数据 */
//		try {
//			BufferedWriter bw = new BufferedWriter(
//					new OutputStreamWriter(new FileOutputStream(new File("D:/结果.txt")), "UTF-8"));
//
//			for (String name : map.keySet()) {
//				bw.write(name + " " + map.get(name));
//				bw.newLine();
//			}
//			bw.close();
//		} catch (Exception e) {
//			System.err.println("write errors :" + e);
//		}
//	public static void main(String[] args) throws Exception {
//		File file = new File("E://A.txt");
//		//1.打开一个输入流
//		FileInputStream fileInputStream = new FileInputStream(file);
//		byte[] bs = new byte[100];
//		//2.读取数据
//		long start = System.currentTimeMillis();
//		//3.每次读取的字节数
//		int length;
//		//打印每次读取的长度
//		int a=0;
//		String str=null;
//		while((length=fileInputStream.read(bs))!=-1) {
//			str=new String(bs, 0, length);
//			if(a%2==0) {
//				String key=str.substring(str.indexOf('*')+3, str.lastIndexOf('*')-2);
//				System.out.println(key);
//			}else {
//				String[] value=str.split(" ");
//				System.out.println(value[2]);
//			}
//			a++;
//		}
//		//4.关闭流
//		fileInputStream.close();
//		//一共耗时：
//		System.out.println("耗时:"+(System.currentTimeMillis()-start));
//	}
