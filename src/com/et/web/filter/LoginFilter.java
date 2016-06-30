package com.et.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.et.util.Constants;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年6月23日下午3:34:05 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class LoginFilter implements Filter {
   private String redirectUrl;

   @Override
   public void destroy() {
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest hsr = (HttpServletRequest) request;
      HttpServletResponse resp = (HttpServletResponse) response;
      HttpSession session = hsr.getSession();
      String servletPath = hsr.getServletPath();
      Object sessionObj = session.getAttribute(Constants.LOGIN_USER);
      // 1.如果用户已经登录，放过
      if (sessionObj != null) {
         chain.doFilter(request, response);
         return;
      }
      // 2.如果请求的是静态文件或登录页面，放过
      if (servletPath.endsWith(".css")
            || servletPath.contains(".js")
            || servletPath.endsWith(".png")
            || servletPath.endsWith(".jpg")
            || servletPath.endsWith(".gif")
            || servletPath.endsWith(".ico")
            || servletPath.endsWith(".html")
            || servletPath.endsWith(".htm")
            || servletPath.endsWith(".json")
            || servletPath.endsWith("login.do")
            || servletPath.endsWith("logout.do")
            ) {
         chain.doFilter(request, response);
         return;
      }
      // 不符合以上情况，重定向到登录页面:如session失效,没有登录直接请求本应用其他页面
      resp.sendRedirect(hsr.getContextPath() + redirectUrl);
   }

   @Override
   public void init(FilterConfig fConfig) throws ServletException {
      redirectUrl = fConfig.getInitParameter("redirectUrl");
   }

}
