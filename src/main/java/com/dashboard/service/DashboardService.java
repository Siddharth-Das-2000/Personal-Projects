package com.dashboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.dashboard.dto.DashboardResponse;
import com.dashboard.model.TransactionLog;
import com.dashboard.repository.LogRepository;
import com.dashboard.util.XmlUtil;

@Service
public class DashboardService {

    private final LogRepository repository;

    private final AtomicLong counter = new AtomicLong();

    public DashboardService(LogRepository repository) {
        this.repository = repository;
    }

    public void addLog(Map<String, String> headers, String payload) {

        boolean isError = XmlUtil.isErrorPayload(headers);

        TransactionLog log = new TransactionLog(
                counter.incrementAndGet(),
                payload,
                LocalDateTime.now().toString(),
                isError
        );

        repository.save(log);
    }

    public DashboardResponse getDashboardData() {

        List<TransactionLog> logs = repository.findAll();

        long errorCount = logs.stream()
                .filter(TransactionLog::isError)
                .count();

        return new DashboardResponse(
                logs.size(),
                errorCount,
                logs
        );
    }
}