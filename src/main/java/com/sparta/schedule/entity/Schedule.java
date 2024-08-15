package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.*;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private String scheduleId;
    private String content;
    private String memberName;
    private String password;
    private String createAt;
    private String modifiedAt;


    public Schedule(ScheduleRequestDto requestDto) {
        this.scheduleId = requestDto.getScheduleId();
        this.content = requestDto.getContent();
        this.memberName = requestDto.getMemberName();
        this.password = requestDto.getPassword();
        this.createAt = getCurrent();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.scheduleId = requestDto.getScheduleId();
        this.content = requestDto.getContent();

    }

    public String getCurrent() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatterNow = now.format(formatter);

        return formatterNow;
    }
}


