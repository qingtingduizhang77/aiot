package com.cityiot.guanxin.branchLeader.repository;

import com.cityiot.guanxin.branchLeader.entity.BranchToArea;
import com.cityiot.guanxin.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BranchToAreaRepository extends BaseRepository<BranchToArea> {
private static final Logger log = LoggerFactory.getLogger(BranchToAreaRepository.class);
}
