package com.et.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achievo.framework.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.et.bean.Json;
import com.et.bean.WebSysConfig;
import com.et.bean.system.BizFile;
import com.et.bean.system.SysUser;
import com.et.util.Constants;
import com.et.util.FileUploadStatus;
import com.et.util.FileUtil;
import com.et.util.ParseDocAndDocxUtil;


/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年4月20日下午6:43:30 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class BaseAction {
   private Logger logger = LoggerFactory.getLogger(BaseAction.class);
   private Vector<FileUploadStatus> vector = new Vector<FileUploadStatus>();
   private static BaseAction baseAction = new BaseAction();
   @Resource(name = "webSysConfig")
   private WebSysConfig webSysConfig;
   /**
    * 获取服务端的地址
    * 例如：http://localhost:8080
    *@author:liangyanjun
    *@time:2016年4月7日上午11:36:39
    *@param request
    *@return
    */
   protected String getServerUrl(HttpServletRequest request) {
      StringBuffer requestURL = request.getRequestURL();
      String contextPathFullUrl = requestURL.substring(0, requestURL.indexOf(request.getServletPath()));
      String serverUrl = contextPathFullUrl.substring(0, contextPathFullUrl.indexOf(request.getContextPath()));
      return serverUrl;
   }

   /**
    * 
    *@author:liangyanjun
    *@time:2016年4月20日下午6:45:23
    *@param returnMap
    *@param response
    */
   public void outputJson(Map<String, Object> returnMap, HttpServletResponse response) {
      try {
         String retrunJson = JSON.toJSONString(returnMap);
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.write(retrunJson);
         out.flush();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   protected void fillReturnJson(HttpServletResponse response,boolean isSucc,String msg) {
      com.et.bean.Json j = new Json();
      j.getHeader().put("success", isSucc);
      j.getHeader().put("msg", msg);
      outputJson(j, response);
   }
   protected void outputJson(Object jsonObj, HttpServletResponse response) {
      // 兼容IE浏览器
      response.setContentType("text/html;charset=utf-8");
      response.setHeader("Cache-Control", "no-cache");
      try {
         PrintWriter pw = response.getWriter();
         // 将Java对象转换为JSON字符串
         String gsonStr = new ObjectMapper().writeValueAsString(jsonObj);
         // 输出JSON字符串
         pw.print(gsonStr);
         pw.flush();
         pw.close();
      } catch (IOException e) {
         System.out.println("输出GSON出错：" + e);
      }
   }
   /**
    * 文件下载
    *@author:liangyanjun
    *@time:2016年7月18日上午11:23:24
    *@param response
    *@param request
    *@param path
    *@param model
    */
   /*@RequestMapping(value = "download")
   protected void download(HttpServletResponse response, HttpServletRequest request, @RequestParam("path") String path, ModelMap model) {
      try {
         FileDownload.downloadLocal(response, request, path);
      } catch (IOException e) {
         fillReturnJson(response, false, "下载文件异常");
         logger.error("下载文件异常" + ExceptionUtil.getExceptionMessage(e));
         e.printStackTrace();
      }
   }*/
   
   protected Map<String, Object> getFileMap(HttpServletRequest request, HttpServletResponse response, String remark,String businessModule) throws ServletException, IOException {
      // 文件信息BIZ_FILE
      Map<String, Object> resultMap = new HashMap<String, Object>();
      BizFile bizFile = new BizFile();
      resultMap.put("bizFile", bizFile);
      Map<String, Object> map = new HashMap<String, Object>();
      map = FileUtil.processFileUpload(request, response, businessModule, getUploadFilePath(), getFileSize(), getFileDateDirectory(), getUploadFileType());
      resultMap.put("flag", map.get("flag"));
      resultMap.put("errorMsg", map.get("errorMsg"));
      @SuppressWarnings("rawtypes")
      List items = (List) map.get("items");
      for (int i = 0; i < items.size(); i++) {
         FileItem item = (FileItem) items.get(i);
         String fieldName = item.getFieldName();
         if (item.isFormField()) {
            resultMap.put(item.getFieldName(), ParseDocAndDocxUtil.getStringCode(item));
         }else if ("offlineMeetingFile".equals(fieldName)) {
               if (item.getSize() != 0) {
                  bizFile.setFileSize((int) item.getSize());
                  // 获得文件名
                  String fileFullName = item.getName().toLowerCase();
                  int dotLocation = fileFullName.lastIndexOf(".");
                  String fileName = fileFullName.substring(0, dotLocation);
                  String fileType = fileFullName.substring(dotLocation).substring(1);
                  bizFile.setFileType(fileType);
                  bizFile.setFileName(fileName);
               }
         }
      }
     
      if (bizFile.getFileSize() == 0) {
         return resultMap;
      }
      // 文件信息设置值
      String uploadDttm = DateUtil.format(new Date());
      bizFile.setUploadDttm(uploadDttm);
      String fileUrl = String.valueOf(map.get("path"));
      if (fileUrl == null || "null".equalsIgnoreCase(fileUrl)) {
         return resultMap;
      }
      bizFile.setFileUrl(fileUrl);
      String uploadUserId = getLoginUser(request).getId();
      bizFile.setUploadUserId(uploadUserId);
      bizFile.setStatus(Constants.STATUS_ENABLED);
      bizFile.setRemark(remark);
      return resultMap;
   }
   /**
    *@author:liangyanjun
    *@time:2016年8月5日上午11:45:44
    *@return
    */
   private SysUser getLoginUser(HttpServletRequest req) {
      return (SysUser) req.getSession().getAttribute(Constants.LOGIN_USER);
   }

   /**
    * 获取文件上传的根目录
    * 
    * @return
    */
   protected String getUploadFilePath() {
      return webSysConfig.getUploadFileRoot();
   }
   /**
    * 获取文件上传应许的类型
    * 
    * @return
    */
   protected String[] getUploadFileType() {
      return webSysConfig.getFileType();
   }

   /**
    * 
    * @Description: 获取文件上传大小限制
    * @return
    */
   protected HashMap<String, Integer> getFileSize() {
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      map.put("maxFileSize", webSysConfig.getMaxFileSize());
      map.put("maxReqSize", webSysConfig.getMaxRequestSize());
      return map;
   }
   protected String getFileDateDirectory() {
      return DateUtil.format(new Date(), "yyyy/MM/dd");
   }

   /**
    *@author:liangyanjun
    *@time:2016年8月5日上午11:13:28
    *@return
    */
   public static BaseAction getInstance() {
      return baseAction;
   }
   public FileUploadStatus getUploadStatus(String strID) {
      return vector.elementAt(indexOf(strID));
   }

   public void setUploadStatus(FileUploadStatus status) {
      int nIndex = indexOf(status.getUploadAddr());
      if (-1 == nIndex) {
         vector.add(status);
      } else {
         vector.insertElementAt(status, nIndex);
         vector.removeElementAt(nIndex + 1);
      }
   }

   public void removeUploadStatus(String strID) {
      int nIndex = indexOf(strID);
      if (-1 != nIndex)
         vector.removeElementAt(nIndex);
   }
   private int indexOf(String strID) {
      int nReturn = -1;
      for (int i = 0; i < vector.size(); i++) {
         FileUploadStatus status = vector.elementAt(i);
         if (status.getUploadAddr().equals(strID)) {
            nReturn = i;
            break;
         }
      }
      return nReturn;
   }
}
