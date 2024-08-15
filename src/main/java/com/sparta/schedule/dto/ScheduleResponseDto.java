package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ScheduleResponseDto {
    private String scheduleId;
    private String content;
    private String memberName;
    private String password;
    private String createAt;
    private String modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.content = schedule.getContent();
        this.memberName = schedule.getMemberName();
        this.password = schedule.getPassword();
        this.createAt = getCurrent();
    }

    public ScheduleResponseDto(String scheduleId, String content, String memberName, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.memberName = memberName;
        this.password = password;
        this.createAt = getCurrent();
    }

    public String getCurrent() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatterNow = now.format(formatter);

        return formatterNow;
    }
}
