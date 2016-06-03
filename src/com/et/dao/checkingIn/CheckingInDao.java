package com.et.dao.checkingIn;

import java.util.List;

import com.et.base.BaseDao;
import com.et.bean.checkingIn.CheckingIn;
import com.et.bean.checkingIn.CheckingInIndex;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年5月24日上午10:04:27 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */

public interface CheckingInDao extends BaseDao<CheckingIn> {

   /**
    *@author:liangyanjun
    *@time:2016年5月25日下午3:47:20
    *@param checkingInIndex
    *@return
    */
   List<CheckingInIndex> queryCheckingInIndex(CheckingInIndex checkingInIndex);

   /**
    *@author:liangyanjun
    *@time:2016年5月25日下午3:47:41
    *@param checkingInIndex
    *@return
    */
   int getCheckingInIndexTotal(CheckingInIndex checkingInIndex);

   /**
    *@author:liangyanjun
    *@time:2016年6月3日下午3:05:10
    *@param checkingInIndex
    *@return
    */
   List<CheckingInIndex> queryHandleCheckingIn(CheckingInIndex checkingInIndex);

}
