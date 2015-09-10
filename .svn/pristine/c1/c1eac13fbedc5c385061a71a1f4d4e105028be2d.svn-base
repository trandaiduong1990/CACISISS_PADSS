package org.transinfo.cacis.bgprocess.scheduler;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.transinfo.cacis.bgprocess.CardRenewalProcess;

@SuppressWarnings("unchecked")
public class CardRenewalScheduler implements Job {
	
	private static final Logger cardRenewalLog = Logger.getLogger("CardRenewalLog");

	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {


		Map dataMap = jobexecutioncontext.getJobDetail().getJobDataMap();
		CardRenewalProcess task = (CardRenewalProcess) dataMap.get("cardRenewalTask");

		cardRenewalLog.info("Card Renewal batch process Scheduled task Started at " + new Date());
		
		// for scheduled task pass the empty string
		task.doRenewal("");
		
		cardRenewalLog.info("Card Renewal batch process Scheduled task Finished at " + new Date());

	}

}
