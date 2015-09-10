// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   CTFMsg.java

package com.transinfo.batch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package vn.com.tivn.ctf:
//            Trailer, LogicalTranx, DraftData, DocumentRequest,
//            FeeCollectionRequest, MessageFactory, TC

@SuppressWarnings( { "unchecked", "unused" })
public class CTFMsg {

	public CTFMsg() {
		batchList = new Vector();
		TCRs = new Hashtable();
		Draft0s = new Vector();
		Draft1s = new Vector();
		DocRequests = new Vector();
		FeeCollectionRequests = new Vector();
		Misc = new Vector();
		loaded = false;
		fileTrailer = new Trailer();
		// fileTrailer.setTypeFileTrailer();
	}

	public void add(LogicalTranx tranx) throws Exception {
		Object obj;

		for (Enumeration enmrt = tranx.getTCRs(); enmrt.hasMoreElements(); TCRs
				.put(obj, obj))
			obj = enmrt.nextElement();

		batchList.add(tranx);
	}

	public void write2Stream(OutputStream out) throws Exception {
		long fileTotal = 0L;
		int fileCount = 0;
		int monetaryTranxCount = 0;
		int TCCount = 0;
		String result = "";
		for (Enumeration enmrt = batchList.elements(); enmrt.hasMoreElements();) {
			LogicalTranx tnx = (LogicalTranx) enmrt.nextElement();
			tnx.write2Stream(out);

		}

	}

	private void getData(InputStream in) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		for (String st = br.readLine(); st != null; st = br.readLine()) {

			if (st.length() != 168) {
				st = CTFUtils.rpad(st, " ", 168);

			}

			TC aTC = MessageFactory.getTranxType(st);

			if (aTC != null) {
				aTC.unpackString(st);

				if ((aTC instanceof DraftData) && aTC.get("TCR").equals("0")) {
					Draft0s.add(Draft0s.size(), aTC);
					continue;
				}
				if ((aTC instanceof DraftData) && aTC.get("TCR").equals("1")) {
					Draft1s.add(Draft1s.size(), aTC);
					continue;
				}
				if (aTC instanceof DocumentRequest) {
					DocRequests.add(DocRequests.size(), aTC);
					continue;
				}

				else {

					Misc.add(Misc.size(), aTC);

				}
			} else {

				// Misc.add(Misc.size(), aTC);
				System.out.println("Tranx not configured");
			}
		}
		System.out.println("Loaded");
		loaded = true;
	}

	public Enumeration getDraftData0(InputStream in) throws Exception {
		if (!loaded)
			getData(in);
		return Draft0s.elements();
	}

	public Enumeration getDraftData1(InputStream in) throws Exception {
		if (!loaded)
			getData(in);
		return Draft1s.elements();
	}

	public Enumeration getDocumentRequests(InputStream in) throws Exception {
		if (!loaded)
			getData(in);
		return DocRequests.elements();
	}

	public Enumeration getFeeCollectionRequest(InputStream in) throws Exception {
		if (!loaded)
			getData(in);
		return FeeCollectionRequests.elements();
	}

	public Enumeration getMisc(InputStream in) throws Exception {
		if (!loaded)
			getData(in);
		return Misc.elements();
	}

	public int getTranxCount() {
		try {
			int i = Integer.parseInt(fileTrailer.get("NoOfTranx"));
			return i;
		} catch (Exception e) {
			int j = 0;
			return j;
		}
	}

	private Vector batchList;
	private Trailer fileTrailer;
	private Hashtable TCRs;
	private Vector Draft0s;
	private Vector Draft1s;
	private Vector DocRequests;
	private Vector FeeCollectionRequests;
	private Vector Misc;
	private boolean loaded;
}
