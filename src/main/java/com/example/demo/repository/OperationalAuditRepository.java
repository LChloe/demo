package com.example.demo.repository;

import com.example.demo.base.repository.IBaseRepository;
import com.example.demo.entity.OperationalAudit;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationalAuditRepository extends IBaseRepository<OperationalAudit, Long> {

    OperationalAudit findByServiceName(String serviceName);

}
