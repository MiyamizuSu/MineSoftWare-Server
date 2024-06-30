package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.ConferenceBiz;
import com.Robin.RobinServer.Entity.Conference;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class ConferenceControllerTest {

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private ConferenceBiz conferenceBiz;

    public void setConferenceBiz(ConferenceBiz conferenceBiz) {
        this.conferenceBiz = conferenceBiz;
    }

    private ConferenceController controller = new ConferenceController();

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        controller.setConferenceBiz(conferenceBiz);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        System.out.println("会议管理模块测试开始---");
    }

    @Test
    void getConferenceList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/conference/listAll")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        // mockMvc.perform执行一个请求
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println(jsonResponse);
        // 使用 ObjectMapper 将 JSON 响应转换为 Map
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, Map.class);
        assertEquals(responseMap.get("isOk"), true);
        assertNotNull(responseMap.get("conferenceList"));
    }


    @Test
    void listConferencesByCompanyName() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Map<String, Object> request1 = new HashMap<>();
        request1.put("companyName", "米哈游");
        String jsonRequest1 = objectMapper.writeValueAsString(request1);
        System.out.println("jsonRequest1: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/listByCompany")
                .contentType(MediaType.APPLICATION_JSON) //注意不是写accept,而是写contentType
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);

        //异常的测试用例：公司名为空字符串
        Map<String, Object> request2 = new HashMap<>();
        request2.put("companyName", "");
        String jsonRequest2 = objectMapper.writeValueAsString(request2);
        System.out.println("jsonRequest2: "+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/listByCompany")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("isOk"), true);
        List<Conference> expecedList1 = conferenceBiz.getConferencesByBelongedCompany("米哈游");
        assertEquals(expecedList1.size(), ((List<Conference>)responseMap1.get("conferenceList")).size());
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("isOk"), false);
        assertNull(responseMap2.get("conferenceList"));
    }

    @Test
    void getConferenceById() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Map<String, Object> request1 = new HashMap<>();
        request1.put("conferenceId", 1);
        String jsonRequest1 = objectMapper.writeValueAsString(request1);
        System.out.println("jsonRequest1: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/getById")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);
        //异常的测试用例
        Map<String, Object> request2 = new HashMap<>();
        request2.put("conferenceId", -1); //confrenceId为-1
        String jsonRequest2 = objectMapper.writeValueAsString(request2);
        System.out.println("jsonRequest2: "+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/getById")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("isOk"), true);
        Conference expectedConference1 = conferenceBiz.getConferenceById(1);
        // 将返回的 JSON 解析成 Conference 对象
        Conference returnedConference1 = objectMapper.convertValue(responseMap1.get("conference"), Conference.class);
        assertEquals(expectedConference1.getImgUrl(), returnedConference1.getImgUrl() ); //由于编码问题，可选择检查imgUrl作为另一个唯一标识符
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("isOk"), false);
        assertNull(responseMap2.get("conference"));
    }

    @Test
    void getConferenceByName() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Map<String, Object> request1 = new HashMap<>();
        request1.put("conferenceName", "文学品读会");
        String jsonRequest1 = objectMapper.writeValueAsString(request1);
        System.out.println("jsonRequest1: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/getByName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);
        //异常的测试用例
        Map<String, Object> request2 = new HashMap<>();
        request2.put("conferenceName", ""); //conferenceName为空字符串
        String jsonRequest2 = objectMapper.writeValueAsString(request2);
        System.out.println("jsonRequest2: "+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/getByName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("isOk"), true);
        Conference expectedConference1 = conferenceBiz.getConferenceByName("文学品读会");
        // 将返回的 JSON 解析成 Conference 对象
        Conference returnedConference1 = objectMapper.convertValue(responseMap1.get("conference"), Conference.class);
        assertEquals(expectedConference1.getImgUrl(), returnedConference1.getImgUrl() ); //由于编码问题，可选择检查imgUrl作为另一个唯一标识符
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("isOk"), false);
        assertNull(responseMap2.get("conference"));
    }

    @Test
    void addConference() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Conference conference1 = new Conference(20, "会议20", "小光", "进行中", "会议内容", "2024-06-28 14:00:00", "2024-06-29 17:30:00", "", "米哈游");
        String jsonRequest1 = objectMapper.writeValueAsString(conference1);
        System.out.println("要插入的会议1（正常测试用例）: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);
        //异常的测试用例：试图插入重名的会议
        Conference conference2 = new Conference(20, "会议20", "小李", "进行中", "会议内容2", "2024-06-28 14:00:00", "2024-06-29 17:30:00", "", "米哈游");
        String jsonRequest2 = objectMapper.writeValueAsString(conference2);
        System.out.println("要插入的会议2（conferenceName重复）:"+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("statusCode"), "200");
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("statusCode"), "501"); //返回状态码告知会议重名
    }

    @Test
    void deleteById() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Map<String, Object> request1 = new HashMap<>();
        request1.put("conferenceId", 16);
        String jsonRequest1 = objectMapper.writeValueAsString(request1);
        System.out.println("jsonRequest1: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/deleteById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);
        //异常的测试用例：conferenceId为-1
        Map<String, Object> request2 = new HashMap<>();
        request2.put("conferenceId", -1);
        String jsonRequest2 = objectMapper.writeValueAsString(request2);
        System.out.println("jsonRequest2: "+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/deleteById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("isOk"), true);
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("isOk"), false);
    }

    @Test
    void updateConference() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //正常的测试用例
        Conference conference1 = new Conference(1, "工业创新讨论会", "江苏软件质量检测中心", "进行中", "会议内容", "2024-06-29 14:00:00", "2024-06-30 17:30:00", "", "米哈游");
        String jsonRequest1 = objectMapper.writeValueAsString(conference1);
        System.out.println("要更新的会议1（正常测试用例）: "+jsonRequest1);
        RequestBuilder requestBuilder1 = MockMvcRequestBuilders
                .post("/conference/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest1);
        MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
        String jsonResponse1 = result1.getResponse().getContentAsString();
        System.out.println("jsonResponse1: "+jsonResponse1);
        //异常的测试用例
        Conference conference2 = new Conference(-1, "XXX会议", "小明", "已结束", "会议内容2", "2024-06-28 14:00:00", "2024-06-29 17:00:00", "", "米哈游");
        String jsonRequest2 = objectMapper.writeValueAsString(conference2);
        System.out.println("异常测试用例（conferenceId为-1）: "+jsonRequest2);
        RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .post("/conference/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest2);
        MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
        String jsonResponse2 = result2.getResponse().getContentAsString();
        System.out.println("jsonResponse2: "+jsonResponse2);

        //分别检查测试结果是否与预期一致
        Map<String, Object> responseMap1 = objectMapper.readValue(jsonResponse1, Map.class);
        assertEquals(responseMap1.get("isOk"), true);
        Map<String, Object> responseMap2 = objectMapper.readValue(jsonResponse2, Map.class);
        assertEquals(responseMap2.get("isOk"), false);
    }

    @AfterEach
    void tearDown() {
        System.out.println("会议管理模块测试结束---");
    }
}