package com.lord.tools;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 工作统计
 * @author xj_xiaocheng
 * @Data   2013-9-7
 */
public class CopyOfWorkRecord {
	private static String fileName = "E:/test/record.txt";
	
	public static void main(String[] args) throws Exception {
		File file = new File(fileName);
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		List<CopyOfDateRecord> recordList = new ArrayList<CopyOfDateRecord>();
		for (String line : lines) {
			String[] strs = line.split(" ");
			int len = strs.length;
			if(len <= 1)
				continue;
			CopyOfDateRecord record = new CopyOfDateRecord();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
			Date date = format.parse(strs[0]);			
			record.setDate(date);
			
			if(len >= 3) {
				String start = strs[1].replace(";", "");
				String end = strs[len - 1].replace(";", "");				
				record.setStartWork(timeformat.parse(start));
				record.setEndWork(timeformat.parse(end));
				record.setState();
			} else if (len < 3) {
				String start = strs[1].replace(";", "");
				record.setStartWork(timeformat.parse(start));
				record.setState(CopyOfDateRecord.ERROR);
			}
			recordList.add(record);
		}
		
		displayOTDate(recordList);
	}
	
	public static void displayOTDate(List<CopyOfDateRecord> list) {
		int count = 1;
		for (CopyOfDateRecord dateRecord : list) {
			System.out.println(count + "\t" + dateRecord);
			count++;
		}
		System.out.println("共" + list.size() + "天上班");
	}
}

class CopyOfDateRecord {
	public static final String OT = " ";
	public static final String ERROR = "打卡异常";
	public static final String WEEKEND_OT = "周未";
	public static final String DATSOFF = "调休";
	public static final String BEFORE_NI = "可能通宵";
	
	private Date date;
	private Date startWork;
	private Date endWork;
	private String state = " ";
	private double workHour = 8;
	private double otHour = 0;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartWork() {
		return startWork;
	}
	public void setStartWork(Date startWork) {
		this.startWork = startWork;
	}
	public Date getEndWork() {
		return endWork;
	}
	public void setEndWork(Date endWork) {
		this.endWork = endWork;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String s = "\t";
		double daysoff = 0;
		if(otHour < 0) 
			daysoff = -otHour;
		
		//String old = dateFormat.format(date) + s + timeFormat.format(startWork) + s + timeFormat.format(endWork) + s;
		
		return dateFormat.format(date) + s
			+ "肖诚" + s + workHour + s + otHour + s + daysoff + s
			+ state;
	}
	
	public void setState() throws ParseException {
		Calendar calendar = Calendar.getInstance();				
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
		Date befWork = timeformat.parse("09:00:00");
		Date goWork = timeformat.parse("09:31:00");
		Date eWork = timeformat.parse("18:30:00");
		Date msWork = timeformat.parse("12:30:00");
		Date meWork = timeformat.parse("13:30:00");
		Date offWork = timeformat.parse("19:30:00");
		
		if(w > 1 && w < 7) {
			//工作日
			if(goWork.getTime() > startWork.getTime() && endWork.getTime() < offWork.getTime() && endWork.getTime() > eWork.getTime()) {
			
			} else if(endWork.getTime() > offWork.getTime()) {
				long difference = 0;
				if(goWork.getTime() >= startWork.getTime()) {
					difference = endWork.getTime() - offWork.getTime();
				} else if(goWork.getTime() < startWork.getTime()) {
					difference = endWork.getTime() - offWork.getTime() - (startWork.getTime() - goWork.getTime());
				}
				
				long hour=difference/(3600*1000);
				long minute=difference/(60*1000) - hour*60;
				
				otHour = hour;
				if(minute >= 30) {
					otHour = otHour + 0.5;
				}
				//System.out.println(workHour + "--" + otHour);
				workHour = workHour + otHour;
				if(workHour >= 8)
					workHour = 8;
				
				if(otHour > 0)
					state = OT;
				else if(otHour < 0) {
					state = DATSOFF;
				}
			}
			
			if(startWork.getTime() < befWork.getTime()) {
				state = BEFORE_NI;
			}
			
		} else if(w == 1 || w == 7) {
			long difference = 0;
			if(startWork.getTime() <= msWork.getTime()) {
				difference = (msWork.getTime() - startWork.getTime()) + (endWork.getTime() - meWork.getTime());
			} else if(startWork.getTime() >= meWork.getTime()) {
				difference = endWork.getTime() - startWork.getTime();
			}
			
			long hour=difference/(3600*1000);
			long minute=difference/(60*1000) - hour*60;
			
			otHour = hour;
			if(minute >= 30) {
				otHour = otHour + 0.5;
			}
			workHour = otHour;
			
			state = WEEKEND_OT;
		}
	}
}

