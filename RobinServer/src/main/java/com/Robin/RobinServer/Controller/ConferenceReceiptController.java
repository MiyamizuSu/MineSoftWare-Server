package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.ConferenceReceiptBiz;
import com.Robin.RobinServer.Entity.ConferenceReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conferenceReceipt")
public class ConferenceReceiptController {
    @Autowired
    private ConferenceReceiptBiz receiptBiz;

    public void setReceiptBiz(ConferenceReceiptBiz receiptBiz) {
        this.receiptBiz = receiptBiz;
    }

    @RequestMapping("/listAll")
    public Map<String, Object> getReceiptList() {
        Map<String, Object> response = new HashMap<>();
        List<ConferenceReceipt> receiptList = receiptBiz.getAllReceipts();
        response.put("isOk", true);
        response.put("receiptList", receiptList);
        return response;
    }

    @RequestMapping("/getById")
    public Map<String, Object> getReceiptById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int receiptId = (int) request.get("receiptId");
        ConferenceReceipt receipt = receiptBiz.getReceiptById(receiptId);
        if (receipt != null) {
            response.put("isOk", true);
            response.put("receipt", receipt);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("receipt", null);
            response.put("msg", "查询失败，请检查receiptId");
        }
        return response;
    }

    @RequestMapping("/add")
    public Map<String, Object> addReceipt(@RequestBody ConferenceReceipt receipt) {
        Map<String, Object> response = new HashMap<>();
        boolean isOk = receiptBiz.addReceipt(receipt);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "添加成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "添加失败");
        }
        return response;
    }

    @RequestMapping("/deleteById")
    public Map<String, Object> deleteById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int receiptId = (int) request.get("receiptId");
        boolean isOk = receiptBiz.removeReceiptById(receiptId);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "删除成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "删除失败，请检查receiptId");
        }
        return response;
    }

    @RequestMapping("/update")
    public Map<String, Object> updateReceipt(@RequestBody ConferenceReceipt receipt) {
        Map<String, Object> response = new HashMap<>();
        boolean isOk = receiptBiz.updateReceipt(receipt);
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
