package com.dc.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// git diff --name-status 6b876dac52b9deb89209f7c92f920140901a9223 fc55f2ca761068fedba0ead0299fa90676f3021a > /e/git_diff.txt
public class Compiler {

	// E:\git\guoshou\SmartPaaS
	private String home;
	// E:\build_all
	private String destination;
	// e:\git_diff.txt
	private String fileName;

	public Compiler(String home, String destination, String fileName) {
		this.home = home;
		this.destination = destination;
		this.fileName = fileName;
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	private void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	private boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	public void clean() {
		this.delFolder(destination);
	}
	
	private void copyFile(String in, String out) {
		try {
			File outFile = new File(out);
			File path = outFile.getParentFile();
			if (!path.exists()) {
				path.mkdirs();
			}
			InputStream is = new FileInputStream(new File(in));
			OutputStream os = new FileOutputStream(new File(out));
			byte[] b = new byte[1024];
			int count = 0;
			while ((count = is.read(b)) != -1) {
				os.write(b, 0, count);
			}
			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void copy() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			Pattern pattern = Pattern.compile("\\w*/\\w*/(.*)(.java)");
			String str;
			while ((str = br.readLine()) != null) {
				if (!"".equals(str)) {
					String[] strs = str.split("\t");
					if ("M".equals(strs[0]) || "A".equals(strs[0])) {
						if (strs[1].endsWith(".java")) {
							this.copyFile(home + File.separator + strs[1], destination + File.separator + strs[1]);
							System.out.println("copy " + strs[1]);
							Matcher matcher = pattern.matcher(strs[1]);
							if (matcher.find()) {
								this.copyFile(home + File.separator + "bin" + File.separator + matcher.group(1) + ".class", destination + File.separator + "lib" + File.separator + matcher.group(1) + ".class");
								System.out.println("copy " + home + File.separator + "bin" + File.separator + matcher.group(1) + ".class");
							}
						}
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Compiler compiler = new Compiler("E:/git/guoshou/SmartPaaS", "E:/build_all", "e:/git_diff.txt");
		compiler.clean();
		compiler.copy();
	}

}
