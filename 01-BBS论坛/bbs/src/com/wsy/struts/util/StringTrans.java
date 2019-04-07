package com.wsy.struts.util ;
import java.io.*;

public class StringTrans
{
  public static String tranA(String chi)
  {
    String result=null;
    byte temp[];
    try
    {
      temp=chi.getBytes("UTF-8");
      result=new String(temp);
    }catch(UnsupportedEncodingException e)
    {
      System.out.println(e.toString());
    }
    return result;
  }
  public static String tranB(String chB)
  {
    String result=null;
    byte temp[];
    try
    {
      temp=chB.getBytes("UTF-8");
      result=new String(temp,"iso-8859-1");
    }catch(UnsupportedEncodingException e)
    {
      System.out.println(e.toString());
    }
    return result;
  }
    public static String tranC(String chB)
  {
    String result=null;
    byte temp[];
    try
    {
      temp=chB.getBytes("iso-8859-1");
      result=new String(temp,"UTF-8");
    }catch(UnsupportedEncodingException e)
    {
      System.out.println(e.toString());
    }
    return result;
  }
}
