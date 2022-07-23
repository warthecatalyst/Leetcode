package pkg1.ByteDance.XiaLingYing.Pro1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(47,50);
    }
}

class MyCalendar {
    TreeSet<int[]> booked = new TreeSet<>((a,b)->a[0]-b[0]);

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[]{start, end});
            return true;
        }
        int[] tmp = {end, 0};
        int[] arr = booked.ceiling(tmp);
        int[] prev = arr == null ? booked.last() : booked.lower(arr);
        if (arr == booked.first() || booked.lower(tmp)[1] <= start) {
            booked.add(new int[]{start, end});
            return true;
        }
        return false;
    }
}


