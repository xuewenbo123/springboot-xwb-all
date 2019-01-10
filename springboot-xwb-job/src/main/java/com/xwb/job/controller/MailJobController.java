package com.xwb.job.controller;

import com.xwb.job.model.MailJob;
import com.xwb.job.service.MailJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail_job")
public class MailJobController {

    @Autowired
    MailJobService mailJobService;

    @RequestMapping("/selectOne")
    public MailJob selectOne(@RequestParam("id") Long id){
        MailJob mailJob = mailJobService.selectOne(id);
        return mailJob;
    }


    @RequestMapping("/selectAll")
    public List<MailJob> selectAll(){
        List<MailJob> mailJobs = mailJobService.selectAll();
        return mailJobs;
    }


}
