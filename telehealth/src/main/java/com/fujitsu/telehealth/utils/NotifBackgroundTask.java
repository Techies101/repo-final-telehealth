package com.fujitsu.telehealth.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fujitsu.telehealth.dao.AppPatientImplementation;
import com.fujitsu.telehealth.model.PatientModel;

public class NotifBackgroundTask  {

	AppPatientImplementation App = new AppPatientImplementation();
	PatientModel patient = new PatientModel();

	public static Boolean getTimeDiff(String th_time, String th_date_shcedule) throws ParseException {

		Boolean invokeReminder = false;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
		SimpleDateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
		String currentTime = format.format(new Date());
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(currentTime);
			d2 = format.parse(th_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		long actDiff = TimeUnit.MILLISECONDS.toHours(diff);

		invokeReminder = (actDiff <= 2 && actDiff >= 0 && th_date_shcedule.equals(df.format(new Date()))) ? true : false;
		return invokeReminder;
	}

}
