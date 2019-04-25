package other;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mogo on 4/9/19.
 */
public class ExecutionResultSaver {

    private String resultsDir;
    private String statisticResultsDir;

    public ExecutionResultSaver(String resultsDir, String statisticResultsDir) {
        this.resultsDir = resultsDir;
        this.statisticResultsDir = statisticResultsDir;
    }

    public void save(ArrayList<ArrayList<PopulationItem>> stages) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS");
        String executionDateString = dateFormat.format(date);
        String executionDatetimeString = datetimeFormat.format(date);
        String dirPath = this.resultsDir + File.separator + executionDateString;
        String filePath = dirPath + File.separator + executionDatetimeString + ".json";

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File dir = new File(dirPath);
        File file = new File(filePath);
        dir.mkdirs();

        mapper.writeValue(file, stages);
    }

    public void saveStatisticItem(StatisticItem statisticItem) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS");
        String executionDateString = dateFormat.format(date);
        String executionDatetimeString = datetimeFormat.format(date);
        String dirPath = this.statisticResultsDir + File.separator + executionDateString;
        String filePath = dirPath + File.separator + executionDatetimeString + ".json";

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File dir = new File(dirPath);
        File file = new File(filePath);
        dir.mkdirs();

        mapper.writeValue(file, statisticItem);
    }
}
