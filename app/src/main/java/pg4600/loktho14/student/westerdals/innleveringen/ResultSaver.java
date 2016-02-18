package pg4600.loktho14.student.westerdals.innleveringen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Thorstein on 18.02.2016.
 */
public class ResultSaver {
    private static ResultSaver instance;
    private final String TIME_ZONE = "GMT+1:00";
    private final String HOURS = "HH:mm a";
    private ArrayList<String> winner = new ArrayList<>();

    private ResultSaver(){
    }

    public static ResultSaver getInstance() {
        if(instance == null){
            instance = new ResultSaver();
        }
        return instance;
    }

    public void setWinner(String input){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
        Date timeOfDay = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat(HOURS);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        String displayTime = dateFormat.format(timeOfDay);

        input = input + " at " + displayTime;

        winner.add(input);
    }

    public List<String> getWinner(){
        return winner;
    }

}
