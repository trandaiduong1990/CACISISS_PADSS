// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Trailer.java

package com.transinfo.batch;


// Referenced classes of package vn.com.tivn.ctf:
//            TC

public class Trailer extends TC
{

    public Trailer()
    {
        super.add("RecordType", 1, false);
        super.add("NoOfTranx", 9, true);
        super.add("Filler", 90, false);
    }
}
