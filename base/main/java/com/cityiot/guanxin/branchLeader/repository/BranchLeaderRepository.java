package com.cityiot.guanxin.branchLeader.repository;

import com.cityiot.guanxin.branchLeader.entity.BranchLeader;
import com.cityiot.guanxin.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class BranchLeaderRepository extends BaseRepository<BranchLeader> {
    private static final Logger log = LoggerFactory.getLogger(BranchLeaderRepository.class);


}
