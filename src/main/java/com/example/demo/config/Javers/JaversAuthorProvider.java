package com.example.demo.config.Javers;

import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.SpringSecurityAuthorProvider;


public class JaversAuthorProvider implements AuthorProvider {

    @Override
    public String provide() {
        //todo 更换成自身的用户名称或id
//        return new SpringSecurityAuthorProvider().provide();
        return "unknow";
    }

}
