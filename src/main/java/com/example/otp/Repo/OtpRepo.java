package com.example.otp.Repo;

import java.util.List;
import java.util.UUID;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.otp.entity.Otp;
import com.mongodb.bulk.BulkWriteResult;
import com.twilio.rest.api.v2010.account.sip.Domain;

@Repository
public interface OtpRepo extends MongoRepository<Otp, String>{

//	Object accountId = null;
	@Query("{'accountId':?0}")
	public List<Otp> findOtpByaccountId(String accountId);
	
	
	@Query("{'otp':?0}")
	public List<Otp> findOtpByotp(String otp );


	@Query("{_id:?0, otp: ?1}")
	public List<Otp> findOtpBy_idAndOtp(String _id, String otp);



//
//	@Query("{accountId:?0, otp: ?1}")
//	public List<Otp> checkOtpVerify(String accountId, String otp);


//	 @Query("{(Criteria.where(\"accountId\").is(otp));")
//	public List<Otp> checkOtpVerify(String accountId, String otp);


//);
}
