package com.fujitsu.telehealth.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fujitsu.telehealth.dao.AppPatientImplementation;

public class NotifBackgroundTask extends SQLQuery implements Job{
	
	AppPatientImplementation App = new AppPatientImplementation();
	
	private static Long getDiff(String systemTime, String th_time) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("hh:ss aa"); 
	    Date d1 = null; 
	    Date d2 = null; 
	    
	    try { 
	        d1 = format.parse(systemTime);
	        d2 = format.parse(th_time);
	    } catch (ParseException e) { 
	        e.printStackTrace(); 
	    } 
	 
	    long diff = d2.getTime() - d1.getTime(); 
	    long actDiff = TimeUnit.MILLISECONDS.toHours(diff);
	    return actDiff;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
	}
	
	
}
