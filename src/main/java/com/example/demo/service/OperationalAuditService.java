package com.example.demo.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.entity.OperationalAudit;
import com.example.demo.repository.OperationalAuditRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationalAuditService extends BaseService<OperationalAuditRepository, OperationalAudit, Long> {

    public OperationalAudit findByServiceName(String serviceName) {
        return baseRepository.findByServiceName(serviceName);
    }
}
