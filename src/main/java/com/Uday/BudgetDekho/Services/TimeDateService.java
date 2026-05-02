package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.TimeDate;
import com.Uday.BudgetDekho.Repo.TimeDateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class TimeDateService {

    @Autowired
    TimeDateRepo timeDateRepo;

    public String getDate() {
        TimeDate timeDate = timeDateRepo.findById("last").orElse(null);
        if (timeDate==null) return getTodayDate();
        return timeDate.getDate();
    }

    public void saveDate() {
        String date = getTodayDate();
        Long milli = getMilliseconds();
        TimeDate timeDate = getObj(date,milli);
        TimeDate lastTimeDate = timeDateRepo.findById("current").orElse(new TimeDate());
        timeDateRepo.deleteById("last");
        timeDateRepo.deleteById("current");
        if (lastTimeDate.getDate()==null) {
            lastTimeDate.setTimeInMillieSecs(timeDate.getTimeInMillieSecs());
            lastTimeDate.setDate(timeDate.getDate());
        }
        lastTimeDate.setType("last");
        timeDateRepo.save(lastTimeDate);
        timeDateRepo.save(timeDate);
    }

    private TimeDate getObj(String date, Long milli) {
        TimeDate timeDate = new TimeDate();
        timeDate.setType("current");
        timeDate.setDate(date);
        timeDate.setTimeInMillieSecs(milli);
        return timeDate;
    }

    private String getTodayDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDateTime.format(dateTimeFormatter);
    }

    private Long getMilliseconds() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }


    public Long getTime() {
        TimeDate timeDate = timeDateRepo.findById("last").orElse(null);
        if (timeDate==null) return getMilliseconds();
        return timeDate.getTimeInMillieSecs();
    }

    public void insertFirstDate() {
        TimeDate timeDate = new TimeDate();
        timeDate.setType("current");
        timeDate.setDate(getTodayDate());
        timeDate.setTimeInMillieSecs(getMilliseconds());
        timeDateRepo.save(timeDate);
    }
}
