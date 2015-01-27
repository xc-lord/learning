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
public class WorkRecord {
	private static String fileName = "E:/test/record.txt";
	
	public static void main(String[] args) throws Exception {
		File file = new File(fileName);
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		List<DateRecord> recordList = new ArrayList<DateRecord>();
		for (String line : lines) {
			String[] strs = line.split(" ");
			int len = strs.length;
			if(len <= 1)
				continue;
			DateRecord record = new DateRecord();
			
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
				record.setState(DateRecord.ERROR);
			}
			recordList.add(record);
		}
		
		displayOTDate(recordList);
	}
	
	public static void displayOTDate(List<DateRecord> list) {
		int count = 0;
		for (DateRecord dateRecord : list) {
			String state = dateRecord.getState();
			if(state.equals(DateRecord.OT) || state.equals(DateRecord.WEEKEND_OT)) {
				count++;
				System.out.println(dateRecord);
			}
		}
		System.out.println("共" + list.size() + "天上班，其中" + count + "天加班.");
	}
}

class DateRecord {
	public static final String OT = "平时";
	public static final String ERROR = "打卡异常";
	public static final String WEEKEND_OT = "周未";
	
	private Date date;
	private Date startWork;
	private Date endWork;
	private String state = "normal";
	
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
		return dateFormat.format(date) + s + timeFormat.format(startWork) + s + timeFormat.format(endWork) + s + state;
	}
	
	public void setState() throws ParseException {
		Calendar calendar = Calendar.getInstance();				
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
		String time = "19:00:00";
		Date offWork = timeformat.parse(time);
		if(w > 1 && w < 7) {
			//工作日
			if(endWork.getTime() > offWork.getTime()) {
				state = OT;
			}
		} else if(w == 1 || w == 7) {
			state = WEEKEND_OT;
		}
	}
}

