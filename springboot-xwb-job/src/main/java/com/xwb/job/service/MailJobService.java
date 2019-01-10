package com.xwb.job.service;

import com.xwb.job.model.MailJob;

import java.util.List;

public interface MailJobService {

    MailJob selectOne(Long id);

    List<MailJob> selectAll();

}
