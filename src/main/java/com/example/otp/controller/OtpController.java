package com.example.otp.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.catalina.Host;
import org.hibernate.AnnotationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.otp.Repo.OtpRepo;
import com.example.otp.entity.Otp;

import com.example.otp.service.OtpService;
import com.example.otp.util.HttpResponse;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.rest.api.v2010.account.sip.Domain;
import org.springframework.data.mongodb.core.query.Query;

@RestController
public class OtpController<OtpVerificationResponse, JacksonCompatibleOtpVerificationRequest> {

	
private static final Object _id = null;
	//    private static final String Otp = null;
	@Autowired
    MongoTemplate mongoTemplate;
	@Autowired
	public OtpRepo OtpRepo;
	@Autowired
	public OtpService otpService;
	private Object expired_ts;
	private Object currentts;
	private Object accountId;


		@GetMapping("/home")
		public String home(String b) {
		
		System.out.println("hello vinay");
		return ("hello vinay");
	    }

//		POST MAPPING (POST DATA BY "accountId" AND "metadata" ) :-
	@PostMapping("/generateotpp")
	public Otp createData(@RequestParam(value = "accountId" ) String accountId ,
	                      @RequestParam (value = "meta_data" ) String meta_data){
					       Otp oneTimePass = new Otp();
		   
		   //DECLARE create_ts AND expire_ts
		    long created_ts= (System.currentTimeMillis()/1000);
		    System.out.println("epoch time" + created_ts);
	
	        long expired_ts= (System.currentTimeMillis()/1000+120);
		    System.out.println("epoch time" + expired_ts);
	
	        //DECLARE ONETIMEPASSWORD VARIABLE
			int randomNO=(int) (Math.random()*900000)+100000;
			String otp = String.valueOf(randomNO);
			

//			create random UUID
		    UUID vinay = UUID.randomUUID();
		  
		    oneTimePass.set_id(String.valueOf(vinay));
			oneTimePass.setAccountId(accountId);                           
	        oneTimePass.setCreated_ts(created_ts);          
			oneTimePass.setOtp(otp);                                        
	        oneTimePass.setExpired_ts(expired_ts);
	        oneTimePass.setOptStatus("Otp is expire after two minutes");
	        boolean is_verified = false;
			oneTimePass.setIs_verified(is_verified);                            
			oneTimePass.setMeta_data(meta_data); 
			otpService.createOtp(oneTimePass);
			System.out.println(oneTimePass);
			return oneTimePass;
	}
	
//	GET OTP VERIFY (VALID/OTP INVALID/ OTP EXPIRE) API:-
	@GetMapping("/cheakotpverify")
	public ResponseEntity<HttpResponse> checkOtpStatus(@RequestParam String _id,
								    					@RequestParam String otp) {
				Query query1 = new Query();
				query1.addCriteria( Criteria.where("_id").is(_id).and("otp").is(otp));
				List<Otp> data =mongoTemplate.find(query1, Otp.class);
	if(!(data.size()>0)) {
		
	      return ResponseEntity.badRequest().body(new HttpResponse(null, "OTP INVALID", 401 ));

   }else {
	    for(Otp obj:data) {
		
	long created_ts	=System.currentTimeMillis()/1000;
	long expired_ts= obj.getExpired_ts();
	
	
	System.out.println(expired_ts);
	System.out.println(created_ts);

	if (created_ts>expired_ts) {
		   return ResponseEntity.badRequest().body(new HttpResponse(null, "OTP IS EXPIRED", 401));

	}
	else {
	    return ResponseEntity.badRequest().body(new HttpResponse(data, "OTP VALID", 200 ));
	}

	}
	}
	  return ResponseEntity.badRequest().body(new HttpResponse(null, "INVALID", 401));
	
	}
 
		    @PostMapping(value ="/generateallotp")
			public Otp createData1(	@RequestBody Otp oneTimePass1)
				
			{
	
		    	//create epoch time	
				  long created_ts= (System.currentTimeMillis()/1000);
				  System.out.println("epoch time" + created_ts);
			   //create expire time in epoch time
			      long expired_ts= (System.currentTimeMillis()/1000+120);
				  System.out.println("epoch time" + expired_ts);
			
			   //DECLARE ONETIMEPASSWORD VARIABLE
					int randomNO=(int) (Math.random()*900000)+100000;
					String otp = String.valueOf(randomNO);
					
				    UUID vinay = UUID.randomUUID();
				  
				    oneTimePass1.set_id(String.valueOf(vinay));
				    String accountId = oneTimePass1.getAccountId();
		
					oneTimePass1.setAccountId(accountId);                           
			        oneTimePass1.setCreated_ts(created_ts);          
					oneTimePass1.setOtp(otp);                                        
			        oneTimePass1.setExpired_ts(expired_ts);           
			        boolean is_verified = false;
					oneTimePass1.setIs_verified(is_verified);
					oneTimePass1.setOptStatus("Otp is expire after two minutes");
					
			     	String meta_data = oneTimePass1.getMeta_data();
					
					oneTimePass1.setMeta_data(meta_data);  
					otpService.createOtp(oneTimePass1);
					System.out.println(oneTimePass1);
					System.out.println("meta_data" +meta_data);
					
					System.out.println("accountId" + accountId);

					return oneTimePass1;
			}

//		@GetMapping("/verifyotp/{accountId}/{otp}")
//		List<Otp>getaccountId(@PathVariable String accountId,
//							  @PathVariable (value="otp" ) String otp){			
//					          return otpService.getboth( accountId, otp);
//			}
			
//		CHEAK DATA(_id/otp) IS ALEARDY EXIXT IN RECORDS:-
	    @GetMapping("/verifyOtp1")
	    public ResponseEntity<HttpResponse> getbothdata(@RequestParam(value ="_id") String _id,
													    @RequestParam(value ="otp")  String otp){

				Query query1 = new Query();
				query1.addCriteria(Criteria.where("_id").is(_id).and("otp").is(otp));
				List<Otp> data =mongoTemplate.find(query1, Otp.class);
			if(data.size()>0) {
				    return ResponseEntity.badRequest().body(new HttpResponse(data, "OTP AND ID IS ALREDY EXIXT", 200 ));
			}else {
				    return ResponseEntity.badRequest().body(new HttpResponse(null, "DATA NOT FOUND", 401));
				}
				
			}	
	    
//	 long todayStartDateTime = 1671753600000;
//	 long current_ts=System.currentTimeMillis()/1000;
//	 Query query2 =new Query();
	
	
}
