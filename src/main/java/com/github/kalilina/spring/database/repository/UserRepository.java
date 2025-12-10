package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.bpp.InjectBean;
import com.github.kalilina.spring.database.pool.ConnectionPool;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ToString
public class UserRepository {

//    @InjectBean // own Autowired
//    @Autowired
//    @Qualifier("cp1")
    private ConnectionPool connectionPool1;
//    @Value("${db.driver}")
    private String driverName;
//    @Autowired
    List<ConnectionPool> connectionPools;

    public UserRepository(ConnectionPool connectionPool1,
                          @Value("${db.driver}") String driverName,
                          List<ConnectionPool> connectionPools) {
        this.connectionPool1 = connectionPool1;
        this.driverName = driverName;
        this.connectionPools = connectionPools;
    }
}

