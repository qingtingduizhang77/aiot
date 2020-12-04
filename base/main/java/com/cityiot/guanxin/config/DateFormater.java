package com.cityiot.guanxin.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.springframework.util.StringUtils;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater extends StdDateFormat {

    private static final long serialVersionUID = -3201781773655300201L;

    public static final DateFormater instance = new DateFormater();

    @Override
    /**
     * @ClassName: DateFormater
     * 这个方法可不写，jckson主要使用的是parse(String)这个方法用来转换日期格式的，
     * 只要覆盖parse(String)这个方法即可
     * @date 2018年01月23日 下午4:28:57
     */
    public Date parse(String dateStr, ParsePosition pos) {
        SimpleDateFormat sdf  = null;
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        if (dateStr.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 16) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 23) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.parse(dateStr, pos);
        }
        else if (dateStr.length() == 5){
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.parse(dateStr, pos);
        }
        return super.parse(dateStr, pos);
    }

    @Override
    public Date parse(String dateStr) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf  = null;
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        if (dateStr.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 16) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 23) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.parse(dateStr, pos);
        }
        else if (dateStr.length() == 5){
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.parse(dateStr, pos);
        }
        return super.parse(dateStr, pos);
    }
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date, toAppendTo, fieldPosition);
    }
    @Override
    public DateFormater clone() {
        return new DateFormater();
    }
}
