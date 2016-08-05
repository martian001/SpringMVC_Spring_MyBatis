package com.et.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.et.base.BaseAction;
import com.et.web.listener.FileUploadListener;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月22日 下午4:44:52       ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class FileUtil {
   public static void main(String[] args) throws IOException {
      // filterFileByEndsWith(new File("F:\\"), ".java");
      // String http =
      // "http://g.hiphotos.baidu.com/image/w%3D2048/sign=9dfb6ee460d9f2d3201123ef9dd48a13/0d338744ebf81a4cb700bdccd52a6059252da681.jpg";
      // http =
      // "http://down1.app.uc.cn/pack/2013/09/26/manager.systemapp_V2.7.1163.apk?z=034&p=51131&s=0e6305cc7e3376576e9e8372829988da&app=1&f=12_0_0_0_0&t=1383846865&ptype=1&vtype=0&keyword=%E7%B3%BB%E7%BB%9F%E5%8D%B8%E8%BD%BD";
      // http = "thunder://QUFmdHA6Ly+67MLDtq/C/kB3dDQuaGx0bS5jYzo1MDQxL1u67MLDyte3ond3dy5obHRtLmNjXVu6o9T0zfVdWzY0M10ucm12Ylpa";
      System.out.println(textToFile("dfgdsf", "F:/TEST/", "test.ftl"));
      // copyFolder(new File("G:"), "d:\\G");
      // System.out.println("拷贝成功");
      // createDir("d:\\test\\vvv\\cc");
   }
   private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
   /**
    * 保存符合正则表达式的文件对象的集合
    */
   public static List<File> allFiles = new ArrayList<>();

   /**
    * 根据正则表达式收集（过滤）文件对象到allFiles集合中，不包括文件夹
    * @param file：文件对象（File为一个目录的对象）
    * @param regex：正则表达式
    */
   public static void filterFileByRegex(File file, String regex) {
      if (!file.exists()) {
         return;
      }
      File[] listFiles = file.listFiles();
      if (listFiles == null) {
         return;
      }
      for (File f : listFiles) {
         if (f.isDirectory()) {
            filterFileByRegex(f, regex);
         } else if (f.getName().matches(regex)) {
            allFiles.add(f);
         }
      }
   }

   /**
    * 文件复制，此方法适合字节流的操作，操作成功返回true，反则返回false
    * @param src：源文件
    * @param target：目标文件
    * @return
    * @throws IOException 
    */
   public static boolean copyFile(File src, File target) throws IOException {
      if (!src.exists()) {
         return false;
      }
      InputStream input = new FileInputStream(src);
      OutputStream output = new FileOutputStream(target);
      int length = 0;
      byte[] b = new byte[1024];
      while ((length = input.read(b)) > -1) {
         output.write(b, 0, length);
      }
      input.close();
      output.close();
      return true;
   }

   /**
    * 文本文件复制，此方法适合字符流的操作，操作成功返回true，反则返回false
    * @param src：源文件
    * @param target：目标文件
    * @return
    * @throws IOException 
    */
   public static boolean copyTextFile(File src, File target) throws IOException {
      if (!src.exists()) {
         return false;
      }
      Reader reader = new FileReader(src);
      Writer writer = new FileWriter(target);
      int length = 0;
      char[] c = new char[1024];
      while ((length = reader.read(c)) > -1) {
         writer.write(c, 0, length);
      }
      reader.close();
      writer.close();
      return true;
   }

   /**
    * 把文字放到指定目录下
    *@author:liangyanjun
    *@time:2016年6月6日上午11:00:03
    *@param text
    *@param folderPath
    *@param fileName
    *@return
    *@throws IOException
    */
   public static boolean textToFile(String text, String folderPath, String fileName) throws IOException {
      File folderFile = new File(folderPath);
      if (!folderFile.exists()) {
         folderFile.mkdirs();
      }
      Writer writer = new FileWriter(folderPath + fileName);
      writer.write(text);
      writer.close();
      return true;
   }

   /**
   
    * 创建文件目录
    * @param outputPath 输出文件路径
    */

   public static void createDir(String outputPath) {
      File outputFile = new File(outputPath);
      if (!outputFile.exists()) {
         outputFile.mkdirs();
      }
   }

   /**
    * 合并文件方法，该方法适合合并多个文本或者合并多个音乐文件，内容的顺序是以传入数组元素顺序为准
    * @param musics：文件路径数组
    * @param target：输出路径
    * @throws IOException 
    */
   public static void sequencemusic(String[] files, String target) throws IOException {
      FileOutputStream fos = new FileOutputStream(new File(target), true);
      RandomAccessFile raf;
      for (int i = 0; i < files.length; i++) {
         raf = new RandomAccessFile(new File(files[i]), "r");
         int length = 0;
         byte[] b = new byte[1024];
         while ((length = raf.read(b)) > -1) {
            fos.write(b, 0, length);
         }
      }
   }

   /**
    * 根据http返回一个字节输入流
    * @param http
    * @return
    * @throws IOException
    */
   public static InputStream getURLInputStream(String http) throws IOException {
      URL url = new URL(http);
      // 根据URL，发送http请求
      HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();// 打开连接，通过向下转型获取urlcon对象
      urlcon.setRequestMethod("GET");// 设置请求方式
      urlcon.setConnectTimeout(5000);// 请求时间
      urlcon.setRequestProperty(
            "User-Agent",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; InfoPath.2; doyo 2.6.1)");
      int respondCode = urlcon.getResponseCode();// 得到服务器返回的响应码
      if (respondCode == 200) {// 返回200就成功
         InputStream is = urlcon.getInputStream();// 通过urlcon活的inputstream输入流
         return is;
      }
      return null;
   }

   /**
    * 根据传入的输入字节流，写到outPath路径下
    * @param is
    * @param outPath
    * @throws IOException
    */
   public static void writerFile(InputStream is, String outPath) throws IOException {
      OutputStream os = new FileOutputStream(new File(outPath));
      int length = 0;
      byte[] b = new byte[1024];
      while ((length = is.read(b)) > -1) {
         os.write(b, 0, length);
      }
      os.close();
      is.close();
   }

   /**
    * 拷贝目录下的文件到指定目录下
    * @param file:文件/文件夹
    * @throws IOException 
    */
   public static void copyFolder(File file, String targetPath) throws IOException {
      File[] listFiles = file.listFiles();
      if ((null == listFiles) || (listFiles.length == 0)) {
         return;
      }
      // 遍历数组并且复制文件到指定目录下
      for (File srcFile : listFiles) {
         String outPath = targetPath + srcFile.getPath().substring(2);// 新建指定复制的目标路径
         // 如果是文件夹
         if (srcFile.isDirectory()) {
            createDir(outPath);// 创建文件夹
            copyFolder(srcFile, targetPath);// 递归
            continue;
         }
         if (!srcFile.isFile()) {
            continue;
         }
         String parentFolderPath = outPath.substring(0, outPath.lastIndexOf("\\"));// 获取文件的父文件夹路径
         createDir(parentFolderPath);// 创建文件父文件夹
         // 如果是文件直接复制
         FileOutputStream out = new FileOutputStream(outPath);
         FileInputStream input = new FileInputStream(srcFile);
         byte[] b = new byte[1024];
         int len = 0;
         while ((len = input.read(b)) != -1) {
            out.write(b, 0, len);
         }
         out.close();
         input.close();
      }
   }
   /**
    * 从文件路径中取出文件名
    */
   public static String takeOutFileName(String filePath) {
      int pos = filePath.lastIndexOf(".");
      if (pos > 0) {
         String pressix = filePath.substring(pos + 1);
         UUID uuid = UUID.randomUUID();
         return uuid + "." + pressix;
      } else {
         return filePath;
      }
   }

   /**
    * 从request中取出FileUploadStatus Bean
    */
   public static FileUploadStatus getStatusBean(HttpServletRequest request) {
      BaseAction beanCtrl = BaseAction.getInstance();
      return beanCtrl.getUploadStatus(request.getRemoteAddr());
   }

   /**
    * 把FileUploadStatus Bean保存到类控制器BaseAction
    */
   public static void saveStatusBean(HttpServletRequest request, FileUploadStatus statusBean) {
      statusBean.setUploadAddr(request.getRemoteAddr());
      BaseAction beanCtrl = BaseAction.getInstance();
      beanCtrl.setUploadStatus(statusBean);
   }

   /**
    * 删除已经上传的文件
    */
   public static void deleteUploadedFile(HttpServletRequest request, String uploadPath) {
      FileUploadStatus satusBean = getStatusBean(request);
      for (int i = 0; i < satusBean.getUploadFileUrlList().size(); i++) {
         File uploadedFile = new File(uploadPath + Constants.SEPARATOR + satusBean.getUploadFileUrlList().get(i));
         uploadedFile.delete();
      }
      satusBean.getUploadFileUrlList().clear();
      satusBean.setStatus("删除已上传的文件");
      saveStatusBean(request, satusBean);
   }

   /**
    * 上传过程中出错处理
    */
   public static void uploadExceptionHandle(HttpServletRequest request, String errMsg, String uploadPath) throws ServletException, IOException {
      // 首先删除已经上传的文件
      deleteUploadedFile(request, uploadPath);
      FileUploadStatus satusBean = getStatusBean(request);
      satusBean.setStatus(errMsg);
      saveStatusBean(request, satusBean);
   }

   /**
    * 初始化文件上传状态Bean
    */
   public static FileUploadStatus initStatusBean(HttpServletRequest request, String uploadPath) {
      FileUploadStatus satusBean = new FileUploadStatus();
      satusBean.setStatus("正在准备处理");
      satusBean.setUploadTotalSize(request.getContentLength());
      satusBean.setProcessStartTime(System.currentTimeMillis());
      satusBean.setBaseDir(request.getContextPath() + uploadPath);
      return satusBean;
   }

   @SuppressWarnings({ "unchecked", "deprecation", "deprecation" })
   public static Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response, String type, String modelPath, String rootPath, HashMap<String, Integer> map,String[] filevlitype) throws ServletException, IOException {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      Map<String, Object> result = new HashMap<String, Object>();
         // 设置内存缓冲区，超过后写入临时文件
      factory.setSizeThreshold(10240000);
      String tempPath = CommonUtil.getRootPath() + rootPath + Constants.SEPARATOR + "temp";
      // 设置临时文件存储位置
      File f = new File(tempPath);
      if (!f.exists()) {
         f.mkdirs();
      }
      factory.setRepository(f);
      ServletFileUpload upload = new ServletFileUpload(factory);
      // 设置上传目录位置
      String uploadPath = CommonUtil.getRootPath() + modelPath;
      File f1 = new File(uploadPath);
      if (!f1.exists()) {
         f1.mkdirs();
      }

      // 设置单个文件的最大上传值
      upload.setFileSizeMax(map.get("maxFileSize"));
      // 设置整个request的最大值
      upload.setSizeMax(map.get("maxReqSize"));
      upload.setProgressListener(new FileUploadListener(request));
      // 保存初始化后的FileUploadStatus Bean
      saveStatusBean(request, initStatusBean(request, uploadPath));
      FileUploadStatus satusBean = getStatusBean(request);
      String forwardURL = "";

      try {
         @SuppressWarnings("rawtypes")
         List items = upload.parseRequest(request);
         result.put("items", items);
         // 获得返回url
         for (int i = 0; i < items.size(); i++) {
            FileItem item = (FileItem) items.get(i);
            if (item.isFormField()) {
               forwardURL = item.getString();
               break;
            }
         }

         // 处理文件上传
         for (int i = 0; i < items.size(); i++) {
            FileItem item = (FileItem) items.get(i);

            // 取消上传
            if (getStatusBean(request).getCancel()) {
               deleteUploadedFile(request, uploadPath);
               break;
            }
            // 保存文件
            else if (!item.isFormField() && item.getName().length() > 0) {

               String fileName = takeOutFileName(item.getName());
               boolean b=vlifiletype(fileName,filevlitype);
               if (!b) {
                  result.put("flag", false);
                  result.put("errorMsg", "上传文件类型不合法");
                  logger.info("上传文件类型不合法");
                  return result;
               }
               File uploadedFile = new File(uploadPath + Constants.SEPARATOR + fileName);

               item.write(uploadedFile);
               // 更新上传文件列表
               satusBean.getUploadFileUrlList().add(fileName);
               saveStatusBean(request, satusBean);
               result.put("path", modelPath + "/" + fileName);
               result.put("flag", true);
               // Thread.sleep(500);
            }
         }
      } catch (FileUploadException e) {
         uploadExceptionHandle(request, "上传文件时发生错误:" + e.getMessage(), uploadPath);
         result.put("flag", false);
         result.put("path", "");
         return result;
      } catch (Exception e) {
         uploadExceptionHandle(request, "保存上传文件时发生错误:" + e.getMessage(), uploadPath);
         result.put("flag", false);
         result.put("path", "");
         return result;
      }
      if (forwardURL.length() == 0) {
         forwardURL = "";
      }
      request.setAttribute("msg", "<font size=2><b>文件上传成功!</b></font>");
      return result;
   }

   private static boolean vlifiletype(String fileName, String[] filevlitype) {
      for (int i = 0; i < filevlitype.length; i++) {
         if (fileName.toLowerCase().endsWith(filevlitype[i].toLowerCase())) {
         
            return true;
         
         }
      }
      return false;
   }

   /**
    * @Description: 上传文件
    * @param request
    * @param response
    * @param type
    * @param rootPath
    * @param map
    * @return
    * @throws ServletException
    * @throws IOException
    * @author: Chong.Zeng
    * @date: 2015年1月6日 下午7:43:32
    */
   public static Map<String, Object> processFileUpload(HttpServletRequest request, HttpServletResponse response, String type, String rootPath, HashMap<String, Integer> map, String formaterDate,String[] filevlitype) throws ServletException, IOException {
      String modelPath = rootPath + Constants.SEPARATOR + type + Constants.SEPARATOR + getDateToString(formaterDate);
      return fileUpload(request, response, type, modelPath, rootPath, map,filevlitype);
   }

   /**
    * 回应上传状态查询
    */
   public static void responseStatusQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("text/xml;charset=utf-8");
      response.setHeader("Cache-Control", "no-cache");
      FileUploadStatus satusBean = getStatusBean(request);
      response.getWriter().write(satusBean.toJSon());
   }

   /**
    * 处理取消文件上传
    */
   public static void processCancelFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
      FileUploadStatus satusBean = getStatusBean(request);
      satusBean.setCancel(true);
      saveStatusBean(request, satusBean);
      responseStatusQuery(request, response);
   }

   /**
    * @Description: 获取String型的时间
    * @return
    * @author: Chong.Zeng
    * @date: 2015年1月7日 下午2:13:25
    */
   public static String getDateToString(String format) {
      SimpleDateFormat sf = new SimpleDateFormat(format);
      return sf.format(new Date());
   }
}
