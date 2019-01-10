package com.xwb.job.mapper;

import com.xwb.job.model.MailJob;

import java.util.List;

public interface MailJobMapper {

    MailJob selectOne(Long id);

    List<MailJob> selectAll();

}
