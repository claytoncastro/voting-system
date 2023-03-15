package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.service.TaskSchedulingService;
import br.com.project.msvotacao.service.impl.definition.TaskDefinitionBean;
import br.com.project.msvotacao.util.CronUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class TaskSchedulingServiceImpl implements TaskSchedulingService {

    private final TaskScheduler taskScheduler;
    private final TaskDefinitionBean taskDefinitionBean;
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(long valueCronExpression) {
        String cronExpression = CronUtil.converterCronExpression(valueCronExpression);

        ScheduledFuture<?> scheduledTask = taskScheduler
                .schedule(taskDefinitionBean, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));

        jobsMap.put(UUID.randomUUID().toString(), scheduledTask);
    }

}