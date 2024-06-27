package com.Robin.RobinServer.Biz;

import com.Robin.RobinServer.Entity.Conference;
import com.Robin.RobinServer.Mapper.ConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceBiz {
    @Autowired
    private ConferenceMapper mapper;

    public void setMapper(ConferenceMapper mapper) {
        this.mapper = mapper;
    }

    public List<Conference> getAllConferences() {
        return mapper.allConferences();
    }

    public List<Conference> getConferencesByBelongedCompany(String companyName) {
        return mapper.selectConferencesByBelongedCompany(companyName);
    }

    public Conference getConferenceById(int conferenceId) {
        return mapper.selectConferenceById(conferenceId);
    }

    public Conference getConferenceByName(String conferenceName) {
        return mapper.selectConferenceByName(conferenceName);
    }

    public String addConference(Conference conference) {
        try {
            boolean isDistinct = true;
            Conference dbConference = mapper.selectConferenceByName(conference.getConferenceName());
            if (dbConference != null) {
                isDistinct = false;
            }
            if (isDistinct) {
                System.out.println("将要insert的Conference："+conference);
                mapper.insertConference(conference);
                return "200";
            } else {
                return "501"; //会议名称重复
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }
    }

    public boolean removeConferenceById(int conferenceId) {
        return mapper.deleteConferenceById(conferenceId) > 0;
    }

    public boolean updateConference(Conference updatedConference) {
        //由于不会在session中存Conference，在更新之前还是得在前端先用getByName方法判断一下要更新的会议名是否重名
        return mapper.updateConference(updatedConference) > 0;
    }
}
