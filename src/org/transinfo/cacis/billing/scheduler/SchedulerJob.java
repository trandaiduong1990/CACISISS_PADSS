package org.transinfo.cacis.billing.scheduler;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.transinfo.cacis.billing.BillingSystem;

@SuppressWarnings("unchecked")
public class SchedulerJob implements Job {
	
	private static final Logger billLog = Logger.getLogger("BillLog");

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		Map dataMap = context.getJobDetail().getJobDataMap();
		BillingSystem task = (BillingSystem) dataMap.get("schedulerTask");

		billLog.info("Billing System Scheduled task Started at " + new Date());
		
		// why -1 is, 
		// for manual running by batch file we can pass the particular day of month value. 
		// if it is -1 then it will go via scheduled tasks
		task.doBilling(-1);
		
		billLog.info("Billing System Scheduled task Finished at " + new Date());
	}

}
