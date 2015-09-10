package org.transinfo.cacis.test;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@SuppressWarnings("unchecked")
public class SchedularJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		Map dataMap = context.getJobDetail().getJobDataMap();
		SchedularTask task = (SchedularTask) dataMap.get("schedulerTask");
		task.printTestMesage();
	}

}
