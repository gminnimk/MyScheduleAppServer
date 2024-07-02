package com.sparta.schedulemanagementappserver.repository;


import com.sparta.schedulemanagementappserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
