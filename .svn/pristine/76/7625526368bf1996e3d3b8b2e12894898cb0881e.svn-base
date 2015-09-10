package org.transinfo.cacis.bgprocess.scheduler;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.transinfo.cacis.bgprocess.CardClosingProcess;

@SuppressWarnings("unchecked")
public class CardCloseScheduler implements Job {
	
	private static final Logger cardCloseLog = Logger.getLogger("CardCloseLog");

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {


		Map dataMap = jobexecutioncontext.getJobDetail().getJobDataMap();
		CardClosingProcess task = (CardClosingProcess) dataMap.get("cardClosingTask");

		cardCloseLog.info("Card Close batch process Scheduled task Started at " + new Date());
		
		// for scheduled task pass the empty string
		task.doClosing("");
		
		cardCloseLog.info("Card Close batch process Scheduled task Finished at " + new Date());

	}

}
