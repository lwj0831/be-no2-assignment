package org.kakaoTechCampus.scheduleApp.lv1.repository;


import org.kakaoTechCampus.scheduleApp.lv1.repository.interfaces.ScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv1.domain.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id");
                //.usingColumns("title", "password", "content", "created_at", "updated_at", "writer_name"); //생략 가능
    }

    @Override
    public Long save(Schedule schedule) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        Number key = jdbcInsert.executeAndReturnKey(param); //KeyHolder안쓰고도 간편하게 id값 들고오기 가능
        System.out.println(key);
        System.out.println(key.longValue());
        return key.longValue();

        /*String sql = "INSERT INTO Schedule (title, password, content, created_at, updated_at, writer_name) " +
                "VALUES (:title, :password, :content, :created_at, :updated_at, :writer_name)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return keyHolder.getKey().longValue();*/
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM Schedule WHERE id = :id";
        try {
            Map<String, Object> param = Map.of("id", id);
            return Optional.of(jdbcTemplate.queryForObject(sql, param, scheduleRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        /*List<Schedule> results = jdbcTemplate.query(sql, scheduleRowMapper(), id);
        return results.stream().findFirst();*/
    }

    @Override
    public List<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Schedule WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (writerName != null && !writerName.isBlank()) {
            sql.append(" AND writer_name = :writerName");
            params.put("writerName",writerName);
        }

        if (updatedAt != null) {
            sql.append(" AND DATE(updated_at) = :updatedAt");
            params.put("updatedAt",updatedAt.toLocalDate()); // LocalDate로 변환하여 DATE 컬럼과 비교
        }

        sql.append(" ORDER BY updated_at DESC");

        return jdbcTemplate.query(sql.toString(), params,scheduleRowMapper());
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return BeanPropertyRowMapper.newInstance(Schedule.class);

        /*return (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("schedule_id"));
            schedule.setTitle(rs.getString("title"));
            schedule.setPassword(rs.getString("password"));
            schedule.setContent(rs.getString("content"));
            schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            schedule.setWriterName(rs.getString("writer_name"));
            return schedule;
        };*/
    }
}


