package br.com.project.msvotacao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CronUtil {

    private CronUtil() {}

    private static final long MINUTO = 60000;

    public static String converterCronExpression(long tempoCron) {
        tempoCron *= MINUTO;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + tempoCron);

        return new StringBuilder()
                .append(new SimpleDateFormat("ss").format(calendar.getTime())).append(" ")
                .append(new SimpleDateFormat("mm").format(calendar.getTime())).append(" ")
                .append(new SimpleDateFormat("HH").format(calendar.getTime())).append(" ")
                .append("* * ?").toString();
    }

}
