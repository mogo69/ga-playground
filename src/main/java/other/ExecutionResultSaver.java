package other;

import com.fasterxml.jackson.databind.ObjectMapper;
import functions.Function;

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

    public String getFileDesc( int dimension,
                               int run,
                               Function func,
                               double fitnessSharingMinDistance,
                               double fitnessSharingShareParam,
                               boolean useHammingDist,
                               double pc,
                               double pm) {
        String desc = func.getName();
        desc += "_" + dimension;
        desc += "_Run" + run;
        desc += "_" + (useHammingDist ? "HammingDistance" : "EuclideanDistance");
        desc += "_SUSSelection";
        desc += "_OnePointCrossover" + pc;
        desc += "_OnePointMutation" + pm;
        desc += "_ShareParam" + fitnessSharingShareParam;
        desc += "_MinDistance" + fitnessSharingMinDistance;
        return desc;
    }

    public void save(ArrayList<ArrayList<PopulationItem>> stages,
                     int dimension,
                     int run,
                     Function func,
                     double fitnessSharingMinDistance,
                     double fitnessSharingShareParam,
                     boolean useHammingDist,
                     double pc,
                     double pm) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS");
        String executionDatetimeString = datetimeFormat.format(date);
        String executionDateString = dateFormat.format(date);
        String fileName =
                "graph_"
                        + this.getFileDesc(
                        dimension,
                        run,
                        func,
                        fitnessSharingMinDistance,
                        fitnessSharingShareParam,
                        useHammingDist,
                        pc,
                        pm)
                        + "_" + executionDatetimeString + ".json";
        String dirPath = this.resultsDir + File.separator + executionDateString;
        String filePath = dirPath + File.separator + fileName;

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File dir = new File(dirPath);
        File file = new File(filePath);
        dir.mkdirs();
        mapper.writeValue(file, stages);

        String dirPathForCharts = this.resultsDir + "_for_charts" + File.separator + executionDateString;
        String filePathForCharts = dirPathForCharts + File.separator + fileName;
        File dirForCharts = new File(dirPathForCharts);
        File fileForCharts = new File(filePathForCharts);
        dirForCharts.mkdirs();

        ArrayList<ArrayList<PopulationItem>> stagesForCharts = new ArrayList<ArrayList<PopulationItem>>();
        stagesForCharts.add(stages.get(stages.size() - 1));
        mapper.writeValue(fileForCharts, stagesForCharts);
    }

    public void saveStatisticItem(StatisticItem statisticItem,
                                  int dimension,
                                  int run,
                                  Function func,
                                  double fitnessSharingMinDistance,
                                  double fitnessSharingShareParam,
                                  boolean useHammingDist,
                                  double pc,
                                  double pm) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS");
        String executionDatetimeString = datetimeFormat.format(date);
        String executionDateString = dateFormat.format(date);
        String fileName =
                "stat_"
                + this.getFileDesc(
                    dimension,
                    run,
                    func,
                    fitnessSharingMinDistance,
                    fitnessSharingShareParam,
                    useHammingDist,
                    pc,
                    pm)
                + "_" + executionDatetimeString + ".json";
        String dirPath = this.statisticResultsDir + File.separator + executionDateString;
        String filePath = dirPath + File.separator + fileName;

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File dir = new File(dirPath);
        File file = new File(filePath);
        dir.mkdirs();

        mapper.writeValue(file, statisticItem);
    }
}
