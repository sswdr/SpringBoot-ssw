package com.ssw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw.po.Cat;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ssw
 * @date 2022/8/2 11:28
 */
@Mapper
public interface CatMapper extends BaseMapper<Cat> {
}
