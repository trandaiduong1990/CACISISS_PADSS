// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   MessageFactory.java

package com.transinfo.batch;


// Referenced classes of package vn.com.tivn.ctf:
//            DraftData, DocumentRequest, FeeCollectionRequest, Header,
//            Trailer, TC

public class MessageFactory
{

    public MessageFactory()
    {
    }

    public static TC getTranxType(String st)
        throws Exception
    {
        if( st.length() != 168)
            throw new Exception("Invalid message length");

        switch(Integer.parseInt(st.substring(0, 2)))
        {
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
            return new DraftData(Integer.parseInt(st.substring(3, 4)));


        }
        return null;
    }
}
