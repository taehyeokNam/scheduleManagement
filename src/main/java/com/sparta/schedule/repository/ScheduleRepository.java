package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (scheduleId, content, memberName, password, createdAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getScheduleId());
                    preparedStatement.setString(2, schedule.getContent());
                    preparedStatement.setString(3, schedule.getMemberName());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getCreateAt());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setScheduleId(Long.toString(id));

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM memo";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Schedule 데이터들을 SchduleResponseDto 타입으로 변환해줄 메서드
                String scheduleId = rs.getString("scheduleId");
                String content = rs.getString("content");
                String memberName = rs.getString("memberName");
                String password = rs.getString("password");
                return new ScheduleResponseDto(scheduleId, content, memberName, password);
            }
        });
    }

    public void update(String scheduleId, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET memberName = ?, content = ? WHERE scheduleId = ?";
        jdbcTemplate.update(sql, requestDto.getMemberName(), requestDto.getContent(), scheduleId);
    }

    public void delete(String scheduleId) {
        String sql = "DELETE FROM schedule WHERE scheduleID = ?";
        jdbcTemplate.update(sql, scheduleId);
    }

    public Schedule findById(String id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE scheduleId = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(resultSet.getString("username"));
                schedule.setContent(resultSet.getString("contents"));
                schedule.setMemberName(resultSet.getString("username"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setCreateAt(resultSet.getString("createdAt"));

                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
