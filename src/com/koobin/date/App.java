package com.koobin.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) {
        // 기계용
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC == GMT
        System.out.println(instant.atZone(ZoneId.of("UTC"))); // 기준시 UTC == GMT
        System.out.println(instant.atZone(ZoneId.of("GMT")));

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        // 사람용
        // system Default값을 가져온다.
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        // 글로벌한 서버로 한다면 나라별 system Default가 다르기 때문에 주의가 필
        LocalDateTime birthDay =
                LocalDateTime.of(1995, Month.JULY, 27,0,0,0);
        System.out.println("birthDay = " + birthDay);

        ZonedDateTime nowInKorea = zonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);


        // Instant => ZonedDateTime
        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTimeAsiaSeoul = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTimeAsiaSeoul = " + zonedDateTimeAsiaSeoul);
        // ZonedDateTime => Instant
        Instant toInstant = zonedDateTimeAsiaSeoul.toInstant();
        System.out.println(toInstant);

        // 날짜 사람
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.JULY, 27);

        // 기간을 표현하는 방법
        Period between = Period.between(today, thisYearBirthday);
        System.out.println(between.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        // 날짜 기계
        Instant nowDuration = Instant.now();
        Instant plus = nowDuration.plus(10, ChronoUnit.SECONDS);

        // 기간을 표현하는 방법
        Duration betweenDuration = Duration.between(nowDuration, plus);
        System.out.println("betweenDuration = " + betweenDuration.getSeconds());

        // 포맷팅
        LocalDateTime dateTime = LocalDateTime.now();
        // 두번째 매개변수는 ChronoUnit을 사용한다는 것을 기억하자
        // 혹은 TimeUtil 대신 ChronoUnit 사용한다는 것을 기억하자
        // now.plus(10, ChronoUnit.DAYS) 만 작성하면 불변이기 때문에 아무런 동작안하는 코드가 된다.
        // now.plus(10, ChronoUnit.DAYS) 결과를 담을게 필요하다.
        LocalDateTime plusLocalDateTime = now.plus(10, ChronoUnit.DAYS);

        System.out.println(dateTime);
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(dateTime.format(MMddyyyy));

        // 파싱
        LocalDate parse = LocalDate.parse("07/27/1995", MMddyyyy);
        System.out.println("parse = " + parse);

        // date => Instant
        Date date = new Date();
        Instant instantDate = date.toInstant();
        // Instant => date
        Date newDate = Date.from(instantDate);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // GregorianCalendar => LocalDateTime
        LocalDateTime localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // ZonedDateTime => GregorianCalendar
        ZonedDateTime atZone = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(atZone);

        // 예전 => 최신 API
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        
        // 최신 => 예전 API
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }
}
