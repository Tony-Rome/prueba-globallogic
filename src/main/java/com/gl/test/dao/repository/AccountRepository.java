package com.gl.test.dao.repository;

import com.gl.test.dao.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    AccountEntity findByEmail(String email);

    @Query("SELECT a.email FROM AccountEntity a where a.email=:email ")
    String verifyAccountEmail(@Param("email") String email);
}
