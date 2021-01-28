package cn.deepj.tdd;

import cn.deepj.tdd.domain.Event;
import cn.deepj.tdd.domain.Events;
import cn.deepj.tdd.mapper.EventMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * TODO 求你写点注释吧
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 11:19
 */
@Service
public class EventService {

    private EventMapper eventMapper;

    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public Events getEventDetails(String query) {
        List<Event> eventList = eventMapper.selectList(null);
        if (CollectionUtils.isEmpty(eventList)) {
            throw new EventNotFoundException();
        }
        Events events = new Events("新冠记忆", "2019-至今");
        events.setEventList(eventList);
        return events;
    }
}
