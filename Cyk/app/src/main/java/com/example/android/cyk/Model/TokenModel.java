package com.example.android.cyk.Model;//
//	TokenModel.java
//
//	Create by 伟成 乔 on 28/7/2017
//	Copyright © 2017 伟成 乔. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//

public class TokenModel{

	private String access_token;
	private String expires_in;
	private String refresh_token;
	private String scope;
	private String token_type;

	public void setAccess_token(String accessToken){
		this.access_token = accessToken;
	}
	public String getAccess_token(){
		return access_token;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	@Override
	public String toString() {
		return  "TokenModel: access_token="+access_token+", expires_in="+expires_in+", refresh_token="
				+refresh_token +", scope="+scope+", token_type="+token_type;
	}
}