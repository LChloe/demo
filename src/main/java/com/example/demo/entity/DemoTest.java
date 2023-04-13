package com.example.demo.entity;



import com.example.demo.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "demo_test")
public class DemoTest extends BaseEntity<Integer> {

    private String name;
}
