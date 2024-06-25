package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.ConferenceBiz;
import com.Robin.RobinServer.Entity.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conference")
public class ConferenceController {
    @Autowired
    private ConferenceBiz conferenceBiz;

    public void setConferenceBiz(ConferenceBiz conferenceBiz) {
        this.conferenceBiz = conferenceBiz;
    }

    @RequestMapping("/listAll")
    public Map<String, Object> getConferenceList() {
        Map<String, Object> response = new HashMap<>();
        List<Conference> conferenceList = conferenceBiz.getAllConferences();
        response.put("isOk", true);
        response.put("conferenceList", conferenceList);
        return response;
    }

    @RequestMapping("/getById")
    public Map<String, Object> getConferenceById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int conferenceId = (int) request.get("conferenceId");
        Conference conference = conferenceBiz.getConferenceById(conferenceId);
        if (conference != null) {
            response.put("isOk", true);
            response.put("conference", conference);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("conference", null);
            response.put("msg", "查询失败，请检查conferenceId");
        }
        return response;
    }

    @RequestMapping("/getByName")
    public Map<String, Object> getConferenceByName(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String conferenceName = (String) request.get("conferenceName");
        Conference conference = conferenceBiz.getConferenceByName(conferenceName);
        if (conference != null) {
            response.put("isOk", true);
            response.put("conference", conference);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("conference", null);
            response.put("msg", "查询失败，请检查conferenceName");
        }
        return response;
    }

    @RequestMapping("/add")
    public Map<String, Object> addConference(@RequestBody Conference conference) {
        Map<String, Object> response = new HashMap<>();
        String statusCode = conferenceBiz.addConference(conference);
        response.put("statusCode", statusCode); // "200" / "501" / "777"
        return response;
    }

    @RequestMapping("/deleteById")
    public Map<String, Object> deleteById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int conferenceId = (int) request.get("conferenceId");
        boolean isOk = conferenceBiz.removeConferenceById(conferenceId);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "删除成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "删除失败，请检查conferenceId");
        }
        return response;
    }

    @RequestMapping("/update")
    public Map<String, Object> updateConference(@RequestBody Conference updatedConference) {
        Map<String, Object> response = new HashMap<>();
        boolean isOk = conferenceBiz.updateConference(updatedConference);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "修改成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "修改失败");
        }
        return response;
    }

}
