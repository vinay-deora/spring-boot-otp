package com.example.otp.service;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.AnnotationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.AbstractCachingViewResolver.CacheFilter;

import com.example.otp.Repo.OtpRepo;
import com.example.otp.controller.OtpController;
import com.example.otp.entity.Otp;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
public class OtpService{


	@Autowired
    private OtpRepo otpRepo;
	
	
	public static void send(String accountId) {
		// TODO Auto-generated method stub
		
	}
private int created_ts(java.lang.String string) {
	// TODO Auto-generated method stub
	return 0;
}
public static void create(String otp) {
	// TODO Auto-generated method stub
	return;
}

	private java.lang.String accountId() {
		// TODO Auto-generated method stub
		return accountId() ;
}

	public Otp createOtp (Otp otp) {
		return  (Otp) otpRepo.save(otp);
	}
	
	public List<Otp> verify (String accountId) {
		return otpRepo.findOtpByaccountId(accountId);
	}


	public static int generateOtp(Object getaccountId) {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<Otp> getboth(String _id, String otp) {
		// TODO Auto-generated method stub
		return otpRepo.findOtpBy_idAndOtp(_id, otp);
	}
	public List<Otp> getbothdata(String _id, String otp) {
		// TODO Auto-generated method stub
		return otpRepo.findOtpBy_idAndOtp(_id, otp);
	}


}

	


