package com.flinders.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flinders.poc.entity.ApplicationUserEntity;

/**
 * Application User JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Integer>, ApplicationUserRepositoryCustom {
	
	List<ApplicationUserEntity> findAll();
	
	@Modifying
    @Query("UPDATE ApplicationUserEntity u SET u.lastLoginStatus = :lastLoginStatus, u.lastLoginTime = :lastLoginTime, "
    		+ "u.modifiedBy = :modifiedBy, u.modificationDate = :modificationDate "
    		+ "WHERE u.userId = :userId")
    int updateLoginLog(@Param("lastLoginStatus") String lastLoginStatus, @Param("lastLoginTime") Date lastLoginTime, 
    		@Param("modifiedBy") String modifiedBy, @Param("modificationDate") Date modificationDate, @Param("userId") String userId);
	
	@Modifying
    @Query("UPDATE ApplicationUserEntity u SET u.userName = :userName, u.password = :password, u.emailAddress = :emailAddress, "
    		+ "u.groupCode = :groupCode, u.modifiedBy = :modifiedBy, u.modificationDate = :modificationDate "
    		+ "WHERE u.userId = :userId")
    int updateApplicationUser(@Param("userName") String userName, @Param("password") String password, @Param("emailAddress") String emailAddress,
    		@Param("groupCode") String groupCode, @Param("modifiedBy") String modifiedBy, @Param("modificationDate") Date modificationDate, 
    		@Param("userId") String userId);
	
}
