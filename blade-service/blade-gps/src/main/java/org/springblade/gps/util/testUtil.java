/**
 * Copyright (C), 2015-2021
 * FileName: Demo4
 * Author:   呵呵哒
 * Date:     2021/3/27 14:06
 * Description:
 */
package org.springblade.gps.util;

/**
 * @创建人 hyp
 * @创建时间 2021/3/27
 * @描述
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testUtil {

//    public static void main(String[] args) throws IOException{
//        //封装目录
//        createdocNewFile(new File("e:\\a"),new File("E:\\test1"));
//
//    }

	/**
	 * 将一个list均分成n个list,主要通过偏移量来实现的
	 * @param source
	 * @return
	 */
	public static <T> List<List<T>> averageAssign(List<T> source, int n){
		List<List<T>> result=new ArrayList<List<T>>();
		int remaider=source.size()%n; //(先计算出余数)
		int number=source.size()/n; //然后是商
		int offset=0;//偏移量
		for(int i=0;i<n;i++){
			List<T> value=null;
			if(remaider>0){
				value=source.subList(i*number+offset, (i+1)*number+offset+1);
				remaider--;
				offset++;
			}else{
				value=source.subList(i*number+offset, (i+1)*number+offset);
			}
			result.add(value);
		}
		return result;
	}

	static class HandleThread extends Thread {
		private String threadName;
		private List<String> list;
		private int startIndex;
		private int endIndex;

		public HandleThread(String threadName, List<String> list, int startIndex, int endIndex) {
			this.threadName = threadName;
			this.list = list;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			List<String> subList = list.subList(startIndex, endIndex);
			System.out.println(threadName+"处理了"+subList.size()+"条！startIndex:"+startIndex+"|endIndex:"+endIndex);
		}

	}

//	public static void main(String[] args) {
//		testUtil test = new testUtil();
//		List<String> tmpList = new ArrayList<String>();
//		for (int i = 0; i < 120; i++) {
//			tmpList.add("test" + i);
//		}
//		int length = tmpList.size();
//		int num = 10; //初始线程数
//		//启动多线程
//		if(num > length){
//			num = length;
//		}
//		int baseNum = length / num;
//		int remainderNum = length % num;
//		int end  = 0;
//		for (int i = 0; i < num; i++) {
//			int start = end ;
//			end = start + baseNum;
//			if(i == (num-1)){
//				end = length;
//			}else if( i < remainderNum){
//				end = end + 1;
//			}
//			HandleThread thread = new HandleThread("线程[" + (i + 1) + "] ",  tmpList,start , end);
//			thread.start();
//		}
//	}


	//新建文件
    public static void createdocNewFile(File f1,File f2,String newfileName) throws IOException {
        if(!f2.exists()){
            f2.mkdir();
        }
        //获取File文件数组
        //用过滤器把.at文件过滤处理
        File[] ff=f1.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return new File(dir,name).isFile() && name.endsWith(".at");
            }
        });
        //遍历该数组，得到每一个对象
        for(File xx:ff){
            System.out.println(xx);
            //数据源：E:\test\JDK安装.md
            //目的地：E：\test1\JDK安装.mdd
            String name=xx.getName();
			System.out.println("222222222222222222");
            System.out.println(name);
            File newFile=new File(f2,newfileName);
            copyFile(xx,newFile);
        }

        //在目的地下改名
        File[] dest=f2.listFiles();
        for(File yy:dest){
            String name=yy.getName();
            String newName=name.replace(".at",".docx");
            File ne=new File(f2,newfileName);
			System.out.println("-------------------------------------------------------");
			System.out.println(newfileName);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			yy.renameTo(ne);
        }
    }

    public static void createdjpgNewFile(File f1,File f2,String newfileName) throws IOException {
        if(!f2.exists()){
            f2.mkdir();
        }
        //获取File文件数组
        //用过滤器把.at文件过滤处理
        File[] ff=f1.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return new File(dir,name).isFile() && name.endsWith(".at");
            }

        });
        //遍历该数组，得到每一个对象
        for(File xx:ff){
            //System.out.println(xx);
            //数据源：E:\test\JDK安装.md
            //目的地：E：\test1\JDK安装.mdd
            String name=xx.getName();
            //System.out.println(name);
            File newFile=new File(f2,newfileName);
            copyFile(xx,newFile);
        }

        //在目的地下改名
        File[] dest=f2.listFiles();
        for(File yy:dest){
            String name=yy.getName();

            String newName=name.replace(".at",".jpg");
            File ne=new File(f2,newfileName);
			System.out.println("-------------------------------------------------------");
			System.out.println(newfileName);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            yy.renameTo(ne);
        }
    }

    public static void createdpdfNewFile(File f1,File f2,String newfileName) throws IOException {
        if(!f2.exists()){
            f2.mkdir();
        }
        //获取File文件数组
        //用过滤器把.at文件过滤处理
        File[] ff=f1.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return new File(dir,name).isFile() && name.endsWith(".at");
            }

        });
        //遍历该数组，得到每一个对象
        for(File xx:ff){
            //System.out.println(xx);
            //数据源：E:\test\JDK安装.md
            //目的地：E：\test1\JDK安装.mdd
            String name=xx.getName();
            //System.out.println(name);
            File newFile=new File(f2,newfileName);
            copyFile(xx,newFile);
        }

        //在目的地下改名
        File[] dest=f2.listFiles();
        for(File yy:dest){
            String name=yy.getName();
//            String newName=name.replace(".at",".pdf");
            File ne=new File(f2,newfileName);
			System.out.println("-------------------------------------------------------");
			System.out.println(newfileName);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			yy.renameTo(ne);
        }
    }

    private static void copyFile(File xx, File newFile) throws IOException{
        // TODO Auto-generated method stub
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(xx));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(newFile));
        int len;
        byte[] bt=new byte[1024];
        while((len=bis.read(bt))!=-1){
            bos.write(bt,0,len);
        }
        //释放资源
        bis.close();
        bos.close();
    }


}



