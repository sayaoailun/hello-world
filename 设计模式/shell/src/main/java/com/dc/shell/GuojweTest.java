package com.dc.shell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuojweTest {

	public static void main(String[] args) {
		try {
			// 获取计算机名
			String name = InetAddress.getLocalHost().getHostName();
			// 获取IP地址
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("计算机名：" + name);
			System.out.println("IP地址：" + ip);
		} catch (UnknownHostException e) {
			System.out.println("异常：" + e);
			e.printStackTrace();
		}

	}

}
