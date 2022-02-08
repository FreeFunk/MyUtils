import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author:Qiutianzhu
 * @Date 2022-02-08 15:45
 * @Description: jdk8日期类使用
 */
public class DateUtils {


    public static void main(String[] args) throws InterruptedException {
        localDateInLocalDateTime();
    }

    public static void localTime() throws InterruptedException {
        LocalTime localTime1 = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        Thread.sleep(600);
        LocalTime localTime2 = LocalTime.now(ZoneId.of("Asia/Shanghai"));

        System.out.println(ChronoUnit.MILLIS.between(localTime1,localTime2));
    }

    /**
     * @Author:Qiutianzhu
     * @Description: 结合jdk8中的localTime、localDate、localDateTime
     * 可以理解 localDateTime = localDate + localTime
     * @Date: 2022/2/8 16:20
     **/
    public static void localDateInLocalDateTime() throws InterruptedException {
        //借用日期格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        //转换字符串，转换localDate，拿到年月日
        LocalDate dt1 = LocalDate.parse("20220208", dateTimeFormatter);
        //通过 localTime 拿到结尾时分秒毫秒
        LocalTime localTimebr = LocalTime.of(23,59,59);
        //通过 localTime 拿到开始时分秒毫秒
        LocalTime localTimest = LocalTime.of(0,0,0,0);
        //通过localDateTime 组合 localDate + localTime
        LocalDateTime localDateTimeBer = LocalDateTime.of(dt1,localTimebr);
        LocalDateTime localDateTimeSta = LocalDateTime.of(dt1,localTimest);
        //转化指定时间格式
        System.out.println(localDateTimeSta.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localDateTimeBer.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //转化毫秒值，分区对象也可以使用ZoneId.of("Asia/Shanghai")
        System.out.println(localDateTimeSta.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(localDateTimeBer.toEpochSecond(ZoneOffset.of("+8")));
        //可以通过ChronoUnit工具类计算区间
        System.out.println(ChronoUnit.MILLIS.between(localDateTimeSta,localDateTimeBer));
    }
}
