package com.server.sf.server_user.user.model;


//import com.muyuer.springdemo.utils.SnowflakeIdHelper;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * 自定义ID生成器
 * @author muyuer 182443947@qq.com
 * @version 1.0
 * @date 2018-12-08 15:42
 */
public class CustomIDGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws MappingException {
//        Object id =  SnowflakeIdHelper.getId();
        Object id = new Date().toString();
        if (id != null) {
            return (Serializable) id;
        }
        return super.generate(session, object);
    }

//
//    @Override
//    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
//        return super.generate(session, object);
//    }
}