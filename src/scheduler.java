
import java.io.File;
import java.util.*;

public class scheduler extends TimerTask {

    public void run() {
        System.out.println("Scheduled Full System Scan");

        try {
            fullscan fs = new fullscan();
        } catch (Exception exception5) {
        }
        //TODO generate report
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }

}

class scheduling {

    static long dly;

    public static void main(String[] args) {
        while (true) {
            Timer timer = new Timer();

            Calendar date = Calendar.getInstance();

            // Date date = new Date();
            //int cday=1+date.getDay();
            if (settings.schstatus == true) {
                int day = settings.days.getSelectedIndex() - 1;
                int hr = settings.hrs.getSelectedIndex() - 1;
                int min = settings.hrs.getSelectedIndex() - 1;
                int sec = settings.secs.getSelectedIndex() - 1;

                if (day >= 0) {
                    date.set(
                            Calendar.DAY_OF_WEEK,
                            day
                    );
                    dly = 1000 * 60 * 60 * 24 * 7;
                } else {
                    dly = 1000 * 60 * 60 * 24 * 1;
                }

                date.set(Calendar.HOUR, hr);
                date.set(Calendar.MINUTE, min);
                date.set(Calendar.SECOND, sec);
                date.set(Calendar.MILLISECOND, 0);
                //date.setHours(hr);
                //date.setMinutes(min);
                //date.setSeconds(sec);
                //dly=1000 * 60 * 60 * 24 * day+1;

            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            // Schedule to run every Sunday in midnight
            timer.schedule(new scheduler(), date.getTime(), dly);
        }
    }
}
