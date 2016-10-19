package com.et.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.et.bean.Json;
import com.et.util.ExceptionUtil;

public class ExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    /** 全局异常处理方法 */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        logger.error(ExceptionUtil.getExceptionMessage(e), e);
        String requestType = request.getHeader("X-Requested-With");
        //1.没有权限异常
        if (e instanceof UnauthorizedException) {
            if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
                fillReturnJson(response, false, "对不起,您无该权限!");
                return new ModelAndView();
            }
            return new ModelAndView("redirect:/toUnauthor.do");
        }
        //ajax请求异常
        if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
            fillReturnJson(response, false, "出现未知异常,请与系统管理员联系!");
            return new ModelAndView();
        }
        return new ModelAndView("redirect:/toError.do");
    }

    protected void fillReturnJson(HttpServletResponse response, boolean isSucc, String msg) {
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
}
