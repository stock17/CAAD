package com.Yurym;

public class TimeHelper {

    int secNum;

    TimeHelper(int seconds){
        secNum = seconds;
    }

    public int inMinutes(){
        return secNum / 60;
    }

    public int inHours() {
        return secNum / 3600;
    }

    public String toString() {
        int s = secNum;
        int h = s / 3600;
        s = s % 3600;
        int m = s / 60;
        s = s % 60;

        String str =  h + (h == 1 ? " hour " : " hours ") + m + (m == 1 ? " minute " : " minutes ") + s + (s == 1 ? " second" : " seconds");

        return str;

    }

    public static void main (String[] args) {
        TimeHelper th = new TimeHelper(7322);
        System.out.println(th.inHours());
        System.out.println(th.inMinutes());
        System.out.println(th);
    }



}