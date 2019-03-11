package com.hjkj.business.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
	private static File file = null;

    /**
     * 从本地文件读取图像的二进制流
     * 
     * @param infile
     * @return
     */
    public static FileInputStream getImageByte(String infile) {
        FileInputStream imageByte = null;
        file = new File(infile);
        try {
            imageByte = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageByte;
    }

    public static byte[] getBytes(File file){  
        byte[] buffer = null;
        if (file == null){
            return buffer;
        } else {             
            try {     
	                FileInputStream fis = new FileInputStream(file);  
	                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	                byte[] b = new byte[1000];  
	                int n;  
	                while ((n = fis.read(b)) != -1) {  
	                    bos.write(b, 0, n);  
	                }  
	                fis.close();  
	                bos.close();  
	                buffer = bos.toByteArray();  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
         return buffer;  
	}   

    
    /**
     * 将图片流读出为图�?
     * 
     * @param inputStream
     * @param path
     */
    public static void readBlob(InputStream inputStream, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static String writeFileToService(byte[] bt, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);
            System.out.println(dir.exists());
            if(!dir.exists()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
           bos.write(bt);  
       } catch (Exception e) {  
           e.printStackTrace();  
       } finally {  
           if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return fileName;
 }


    public static void byteToFile(byte[] buf, String filePath, String fileName)  
    {  
        BufferedOutputStream bufferOut = null;  
        FileOutputStream fileOut = null;  
        File file = null;  
        try  
        {  
            File resFile = new File(filePath);  
            if (!resFile.exists() && resFile.isDirectory())  
            {  
                resFile.mkdirs();  
            }  
            file = new File(filePath + File.separator + fileName);  
            fileOut = new FileOutputStream(file);  
            bufferOut = new BufferedOutputStream(fileOut);  
            bufferOut.write(buf);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (bufferOut != null)  
            {  
                try  
                {  
                    bufferOut.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (fileOut != null)  
            {  
                try  
                {  
                    fileOut.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }          
        
    } 

    
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件�? 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
    	boolean flag = false;  
        file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
    
    /** 
     * 删除目录（文件夹）以及目录下的文�? 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔�?  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则�??�?  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文�?(包括子目�?)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文�?  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目�?  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    /** 
     *  根据路径删除指定的目录或文件，无论存在与�? 
     *@param sPath  要删除的目录或文�? 
     *@return 删除成功返回 true，否则返�? false�? 
     */  
    public static boolean DeleteFolder(String sPath) {  
    	boolean flag = false;  
        file = new File(sPath);  
        // 判断目录或文件是否存�?  
        if (!file.exists()) {  // 不存在返�? false  
            return flag;  
        } else {  
            // 判断是否为文�?  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                return deleteFile(sPath);  
            } else {  // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);  
            }  
        }  
    }  
    
    
  //删除指定文件夹下�?有文�?
  //param path 文件夹完整绝对路�?
	 public static boolean delAllFile(String path) {
	     boolean flag = false;
	     File file = new File(path);
	     if (!file.exists()) {
	       return flag;
	     }
	     if (!file.isDirectory()) {
	       return flag;
	     }
	     String[] tempList = file.list();
	     File temp = null;
	     for (int i = 0; i < tempList.length; i++) {
	        if (path.endsWith(File.separator)) {
	           temp = new File(path + tempList[i]);
	        } else {
	            temp = new File(path + File.separator + tempList[i]);
	        }
	        if (temp.isFile()) {
	           temp.delete();
	        }
	        if (temp.isDirectory()) {
	           delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文�?
	           //delFolder(path + "/" + tempList[i]);//再删除空文件�?
	           flag = true;
	        }
	     }
	     return flag;
	   }
 
 
    
}
