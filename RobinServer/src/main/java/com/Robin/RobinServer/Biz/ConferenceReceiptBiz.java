package com.Robin.RobinServer.Biz;

import com.Robin.RobinServer.Entity.ConferenceReceipt;
import com.Robin.RobinServer.Mapper.ConferenceReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceReceiptBiz {
    @Autowired
    private ConferenceReceiptMapper mapper;

    public void setMapper(ConferenceReceiptMapper mapper) {
        this.mapper = mapper;
    }

    public List<ConferenceReceipt> getAllReceipts() {
        return mapper.allReceipts();
    }

    public ConferenceReceipt getReceiptById(int receiptId) {
        return mapper.selectReceiptById(receiptId);
    }

    public boolean addReceipt(ConferenceReceipt receipt) {
        return mapper.insertConferenceReceipt(receipt) > 0;
    }

    public boolean removeReceiptById(int receiptId) {
        return mapper.deleteReceiptById(receiptId) > 0;
    }

    public boolean updateReceipt(ConferenceReceipt receipt) {
        return mapper.updateReceipt(receipt) > 0;
    }
}
