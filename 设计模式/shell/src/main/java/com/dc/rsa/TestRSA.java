package com.dc.rsa;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import com.alibaba.fastjson.JSON;

public class TestRSA {
	
	public static void main(String[] args) {
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
		map.put("exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
		System.out.println(JSON.toJSONString(map));
	}

}
