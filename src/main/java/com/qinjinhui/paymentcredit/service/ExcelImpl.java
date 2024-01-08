package com.qinjinhui.paymentcredit.service;

import com.qinjinhui.paymentcredit.utils.AbstractChunkedFileImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author qinjinhui
 * @Date 2024/1/3
 * @Describe
 **/
@Service
public class ExcelImpl extends AbstractChunkedFileImporter {
    @Autowired
    public ExcelImpl(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    protected void processData(List<String> data) {
        System.out.println("-------------------------------------");
        System.out.println(data);
    }

    @Override
    protected String getSourceFilePath() {
        return "E:\\qinjinhui\\职能升级.txt";
    }

    public void tttt(){
        execute();
    }

}
