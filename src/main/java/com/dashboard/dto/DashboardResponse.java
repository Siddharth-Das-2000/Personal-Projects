package com.dashboard.dto;

import java.util.List;

import com.dashboard.model.TransactionLog;

public class DashboardResponse {

    private long totalCount;
    private long errorCount;
    private List<TransactionLog> logs;

    public DashboardResponse(long totalCount,
                             long errorCount,
                             List<TransactionLog> logs) {

        this.totalCount = totalCount;
        this.errorCount = errorCount;
        this.logs = logs;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public List<TransactionLog> getLogs() {
        return logs;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public void setLogs(List<TransactionLog> logs) {
        this.logs = logs;
    }
}