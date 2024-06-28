package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.Conference;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConferenceMapper {
    @Select("select * from conference")
    List<Conference> allConferences();

    @Select("select * from conference where conferenceId=#{conferenceId}")
    Conference selectConferenceById(int conferenceId);

    @Select("select * from conference where conferenceName=#{conferenceName}")
    Conference selectConferenceByName(String conferenceName);

    @Insert("insert into conference values (null, #{conferenceName}, #{creator}, #{state}, #{content}, #{beginTime}, #{endTime}, #{imgUrl})")
    int insertConference(Conference conference);

    @Delete("delete from conference where conferenceId=#{conferenceId}")
    int deleteConferenceById(int conferenceId);

    @Update("update conference set conferenceName=#{conferenceName}, creator=#{creator}, state=#{state}, content=#{content}, beginTime=#{beginTime}, endTime=#{endTime}, imgUrl=#{imgUrl} where conferenceId=#{conferenceId}")
    int updateConference(Conference updatedConference);


}
