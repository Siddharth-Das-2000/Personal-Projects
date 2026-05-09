package com.dashboard.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dashboard.model.TransactionLog;

@Repository
public class LogRepository {

    private final List<TransactionLog> logs = new ArrayList<>();

    public void save(TransactionLog log) {
        logs.add(0, log);
    }

    public List<TransactionLog> findAll() {
        return logs;
    }
}