package com.lord.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
 
public class JobCheckUtil {
	/**
	 * 判断当前服务器否是要执行JOB的服务器
	 * @return
	 */
	public boolean jobCheck(){
		String jobExecIp = getJobIp("jobExecIp");
		System.out.println("---jobExecIp"+jobExecIp);
		Enumeration allNetInterfaces;
		InetAddress ip = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements())
			{
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				System.out.println(netInterface.getName());
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address)
					{
						String ipAdd = ip.getHostAddress();
						System.out.println("---ipAdd:"+ipAdd);
						if(  jobExecIp != null && jobExecIp.equals(ipAdd)){
							return true;
						}
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 判断当前服务器否是要执行JOB的服务器
	 * @return
	 */
	public boolean jobCheckService(String jobServiceIp){
		String jobExecIp = getJobIp(jobServiceIp);
		System.out.println("---jobExecIp"+jobExecIp);
		Enumeration allNetInterfaces;
		InetAddress ip = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements())
			{
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				//System.out.println(netInterface.getName());
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address)
					{
						String ipAdd = ip.getHostAddress();
						System.out.println("---ipAdd:"+ipAdd);
						if(  jobExecIp != null && jobExecIp.equals(ipAdd)){
							return true;
						}
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法功能：读取配置文件
	 */
	public String getJobIp(String propKey) {
		String fileUrl = this.getClass().getResource("/pathSet.properties").getPath();
		InputStream in = null;
		String result = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileUrl));
			Properties pro = new Properties();
			pro.load(in);
			result = pro.getProperty(propKey);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
		}
		return result;
	}
	
}

