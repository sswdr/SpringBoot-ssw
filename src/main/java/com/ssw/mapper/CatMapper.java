package com.ssw.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw.po.Cat;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ssw
 * @date 2022/8/2 11:28
 */
@Mapper
@DS("mysql")
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface CatMapper extends BaseMapper<Cat> {
}
