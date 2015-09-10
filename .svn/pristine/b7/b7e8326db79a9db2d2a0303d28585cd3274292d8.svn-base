package org.transinfo.cacis.util;

public class NumberUtil extends Object{

    public static boolean greater(String fmin,String  fmax) {
        float min=0;
        float max=0;
        boolean flag= false;
        min = Float.parseFloat(fmin);
        max = Float.parseFloat(fmax);
        if(min==0 && max==0)
            flag= false;
        else
            if(min >= max)
                flag=true;
            else
                flag=false;
        return flag;
    }

    public static boolean greater_equ(String fmin,String  fmax) {
        float min=0;
        float max=0;
        boolean flag= false;
        min = Float.parseFloat(fmin);
        max = Float.parseFloat(fmax);
        if(min==0 && max==0)
            flag= false;
        else
            if(min>max)
                flag=true;
            else
                flag=false;
        return flag;
    }

    public static int convToint(String str){
        return Integer.parseInt(str);
    }

    public static float convTofloat(String str){
        return Float.parseFloat(str);
    }

    public static boolean checkFloat(String str){
        boolean flag = false;
        try{
            float i = Float.parseFloat(str);
            flag = true;
            return flag;
        }catch(NumberFormatException ex) {
            return flag;
        }
    }

    public static boolean checkFloat(String str,int length){  // example valid.checkFloat(coupon_value,10) if coupon_value is number(12,2)
        boolean flag = false;
        try{
            if(str.length()<=length){
                float i = Float.parseFloat(str);
                flag = true;
                return flag;
            }else if(str.indexOf('.')<=length &&  str.indexOf('.')>0){
                float i = Float.parseFloat(str);
                flag = true;
                return flag;
            }else{
                return flag;
            }
        }catch(NumberFormatException ex){
            return flag;
        }
    }
    public static boolean checkInteger(String str){
        boolean flag = false;
        try{
            float i = Integer.parseInt(str);
            flag = true;
            return flag;
        }catch(NumberFormatException ex) {
            return flag;
        }
    }
	 public  static String getRandNo(int len) {
        java.util.Random ran = new java.util.Random();
        String strRan ="";
        for(int i=0;i<len;i++)
            strRan += ran.nextInt(9);

        System.out.println("Ran="+strRan);
        return strRan;
    }

}