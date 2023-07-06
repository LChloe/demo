package com.example.demo.entity;

import com.example.demo.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
public class OperationalAudit extends BaseEntity<Long> {

    private String prefix;
    private String serviceName;
    private String title;
    private String oaId;
    private String logParamsResult;
    private String logParams;
    private String requestUrl;
    private String requestMethod;
    private String servletPath;
    private String fullParams;
    private Integer execStatus;
    private String execMsg;

}
