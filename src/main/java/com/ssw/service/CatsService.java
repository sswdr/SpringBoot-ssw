package com.ssw.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssw.mapper.CatMapper;
import com.ssw.po.Cat;
import org.springframework.stereotype.Service;

/**
 * @author ssw
 * @date 2022/8/2 11:34
 */
@Service
public class CatsService extends ServiceImpl<CatMapper,Cat> {
}
