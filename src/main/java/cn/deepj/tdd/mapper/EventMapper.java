package cn.deepj.tdd.mapper;

import cn.deepj.tdd.domain.Event;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 求你写点注释吧
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 18:58
 */
@Mapper
public interface EventMapper extends BaseMapper<Event> {
}
