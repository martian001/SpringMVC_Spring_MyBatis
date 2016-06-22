package com.et.service.system.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.et.base.BaseServiceImpl;
import com.et.bean.system.SysMenu;
import com.et.mapper.system.SysMenuMapper;
import com.et.service.system.SysMenuService;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016-06-22 16:07:35 <br>
 * ★☆ @version：  1.0<br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 系统菜单<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
   @Resource
   private SysMenuMapper SysMenuMapper;

   @PostConstruct
   public void init() {
      System.out.println("初始化");
      setBaseDao(SysMenuMapper);
   }
}
