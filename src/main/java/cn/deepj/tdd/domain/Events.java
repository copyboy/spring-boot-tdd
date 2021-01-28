package cn.deepj.tdd.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 求你写点注释吧
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 10:17
 */
public class Events {
    private String mainTitle;
    private String timeTitle;

    private List<Event> eventList;

    public Events(String mainTitle, String timeTitle) {
        this.mainTitle = mainTitle;
        this.timeTitle = timeTitle;
    }

    public void add(Event e) {
        if (CollectionUtils.isEmpty(eventList)) {
            eventList = new ArrayList<>();
        }
        eventList.add(e);
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getTimeTitle() {
        return timeTitle;
    }

    public void setTimeTitle(String timeTitle) {
        this.timeTitle = timeTitle;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public String toString() {
        return "Events{" +
                "mainTitle='" + mainTitle + '\'' +
                ", timeTitle='" + timeTitle + '\'' +
                ", eventList=" + eventList +
                '}';
    }
}
