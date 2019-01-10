package com.xwb.job.service;

import com.xwb.job.mapper.MailJobMapper;
import com.xwb.job.model.MailJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailJobServiceImpl implements MailJobService{

    @Autowired
    MailJobMapper mailJobMapper;

    @Override
    public MailJob selectOne(Long id) {
        MailJob mailJob = mailJobMapper.selectOne(id);
        return mailJob;
    }

    @Override
    public List<MailJob> selectAll() {
        List<MailJob> mailJobs = mailJobMapper.selectAll();
        return mailJobs;
    }


}
