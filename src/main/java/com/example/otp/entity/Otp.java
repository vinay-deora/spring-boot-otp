package com.example.otp.entity;

import java.sql.Date;
import java.text.DateFormat;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Document(collection = "otp")
public class Otp {
	@Id
	private String _id;
	private String accountId;
	private String meta_data;
    private String otp;
	private long created_ts;
	private boolean is_verified;
	private long expired_ts;
	private String optStatus ; 
	private DateFormat todayStartDateTime;
	
public Otp(String _id, String accountId, String meta_data, String otp, long created_ts, boolean is_verified,
			long expired_ts, String optStatus, DateFormat todayStartDateTime) {
		super();
		this._id = _id;
		this.accountId = accountId;
		this.meta_data = meta_data;
		this.otp = otp;
		this.created_ts = created_ts;
		this.is_verified = is_verified;
		this.expired_ts = expired_ts;
		this.optStatus = optStatus;
		this.todayStartDateTime = todayStartDateTime;
	}
public DateFormat getTodayStartDateTime() {
		return todayStartDateTime;
	}
	public void setTodayStartDateTime(DateFormat todayStartDateTime) {
		this.todayStartDateTime = todayStartDateTime;
	}
public String getOptStatus() {
		return optStatus;
	}
	public void setOptStatus(String optStatus) {
		this.optStatus = optStatus;
	}

public Otp(String _id, String accountId, String meta_data, String otp, long created_ts, boolean is_verified,
			long expired_ts, Date otpRequestedTime) {
		super();
		this._id = _id;
		this.accountId = accountId;
		this.meta_data = meta_data;
		this.otp = otp;
		this.created_ts = created_ts;
		this.is_verified = is_verified;
		this.expired_ts = expired_ts;
//		this.otpRequestedTime = otpRequestedTime;
	}
public Otp() {
	// TODO Auto-generated constructor stub
}

private int getbothdata() {
		// TODO Auto-generated method stub
		return 0;
	}
 
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public long getCreated_ts() {
		return created_ts;
	}
	public void setCreated_ts(long created_ts) {
		this.created_ts = created_ts;
	}
	public boolean isIs_verified() {
		return is_verified;
	}
	public void setIs_verified(boolean is_verified) {
		this.is_verified = is_verified;
	}
	public long getExpired_ts() {
		return expired_ts;
	}
	public void setExpired_ts(long expired_ts) {
		this.expired_ts = expired_ts;
	}
	

	@Override
	public String toString() {
		return "Otp [_id=" + _id + ", accountId=" + accountId + ", meta_data=" + meta_data + ", otp=" + otp
				+ ", created_ts=" + created_ts + ", is_verified=" + is_verified + ", expired_ts=" + expired_ts
				+ ", otpRequestedTime=" + "]";
	}
	public static Object getaccountId() {
		// TODO Auto-generated method stub
		return null;
	}
	public void set_id(UUID randomUUID) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}


	
	