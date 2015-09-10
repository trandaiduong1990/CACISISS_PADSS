// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   CTFMessageFactory.java

package com.transinfo.batch;

// Referenced classes of package vn.com.tivn.ctf:
//            DocumentRequest, FeeCollectionRequest, DraftData, TC

@SuppressWarnings("unused")
public class CTFMessageFactory {

	public CTFMessageFactory() {
	}

	public static TC getMessage(String tnxCode) {
		int tranxcode = Integer.parseInt(tnxCode);
		try {
			DraftData draft = new DraftData(1);
			return draft;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
