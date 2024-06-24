package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.ConferenceReceipt;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConferenceReceiptMapper {
    @Select("select * from conference_receipt")
    List<ConferenceReceipt> allReceipts();

    @Select("select * from conference_receipt where receiptId=#{receiptId}")
    ConferenceReceipt selectReceiptById(int receiptId);

    @Insert("insert into conference_receipt values (null, #{companyName}, #{userName}, #{userGender}, #{phoneNumber}, #{userEmail}, #{roomType}, " +
            "#{arrive}, #{arriveDetail}, #{leave}, #{leaveDetail}, #{DTW})")
    int insertConferenceReceipt(ConferenceReceipt receipt);

    @Delete("delete from conference_receipt where receiptId=#{receiptId}")
    int deleteReceiptById(int receiptId);

    @Update("update conference_receipt set companyName=#{companyName},userName=#{userName},userGender=#{userGender},phoneNumber=#{phoneNumber},userEmail=#{userEmail}," +
            "roomType=#{roomType},arrive=#{arrive},arriveDetail=#{arriveDetail},leave=#{leave},leaveDetail=#{leaveDetail},DTW=#{DTW} where receiptId=#{receiptId}")
    int updateReceipt(ConferenceReceipt updatedReceipt);

}
