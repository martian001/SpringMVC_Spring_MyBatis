package com.et.mapper.checkingIn;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.mybatis.mapper.BaseMapper;
import com.et.base.BaseDao;
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
@MapperScan("checkingInMapper")
public interface CheckingInMapper<T, PK> extends BaseMapper<T, PK>,BaseDao<T> {

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
