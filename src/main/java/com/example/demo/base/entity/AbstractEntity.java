package com.example.demo.base.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> {

    /**
     * get ID
     *
     * @return Result<ID>
     */
    public abstract ID getId();

    /**
     * set ID
     *
     * @param id id
     */
    public abstract void setId(final ID id);


    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractEntity<?> that = (AbstractEntity<?>) obj;
        return null != this.getId() && this.getId().equals(that.getId());
    }


    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
