package org.transinfo.cacis.dataacess.daoimpl.oracle.printing;

import javax.print.DocPrintJob;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

class PrintJobWatcher {
	// true iff it is safe to close the print job's input stream
	boolean done = false;

	PrintJobWatcher(DocPrintJob job) {
		// Add a listener to the print job
		System.out.println("In the PrintJobWatcher");
		job.addPrintJobListener(new PrintJobAdapter() {
			public void printJobCanceled(PrintJobEvent pje) {
				System.out.println("in printJobCanceled");
				allDone();
			}

			public void printJobCompleted(PrintJobEvent pje) {
				System.out.println("in printJobCompleted");
				allDone();
			}

			public void printJobFailed(PrintJobEvent pje) {
				System.out.println("in printJobFailed");
				allDone();
			}

			public void printJobNoMoreEvents(PrintJobEvent pje) {
				System.out.println("in printJobNoMoreEvents");
				allDone();
			}

			public void printJobRequiresAttention(PrintJobEvent e) {
				System.out.println("Attention" + e);
			}

			void allDone() {
				synchronized (PrintJobWatcher.this) {
					done = true;
					System.out.println("in all done");
					PrintJobWatcher.this.notify();
				}
			}
		});
	}

	public synchronized void waitForDone() {
		try {
			System.out.println("in wait for done");
			while (!done) {
				System.out.println("going to wait");
				wait();
			}
		} catch (InterruptedException e) {
		}
	}
}
