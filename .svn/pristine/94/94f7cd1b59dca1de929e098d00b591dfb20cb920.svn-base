package org.transinfo.cacis.bgprocess.scheduler;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.transinfo.cacis.bgprocess.CardChangeClosingProcess;

@SuppressWarnings("unchecked")
public class CardChangeCloseScheduler implements Job {
	
	private static final Logger cardChangeCloseLog = Logger.getLogger("CardChangeCloseLog");

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {


		Map dataMap = jobexecutioncontext.getJobDetail().getJobDataMap();
		CardChangeClosingProcess task = (CardChangeClosingProcess) dataMap.get("cardChangeClosingTask");

		cardChangeCloseLog.info("Card Change Close batch process Scheduled task Started at " + new Date());
		
		// for scheduled task pass the empty string
		task.doChangeClosing("");
		
		cardChangeCloseLog.info("Card Change Close batch process Scheduled task Finished at " + new Date());

	}

}
