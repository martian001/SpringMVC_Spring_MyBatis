package com.et.service.checkingIn;

import java.util.List;

import com.et.base.BaseService;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.checkingIn.CheckingInIndex;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午9:59:44 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */

public interface CheckingInService extends BaseService<CheckingIn> {
   public List<CheckingInIndex> queryCheckingInIndex(CheckingInIndex checkingInIndex);

   public int getCheckingInIndexTotal(CheckingInIndex checkingInIndex);
   
   public List<CheckingInIndex> queryHandleCheckingIn(CheckingInIndex checkingInIndex);
}
