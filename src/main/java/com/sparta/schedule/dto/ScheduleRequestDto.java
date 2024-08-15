package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String scheduleId;
    private String content;
    private String memberName;
    private String password;
    private String createAt;
    private String modifiedAt;
}
