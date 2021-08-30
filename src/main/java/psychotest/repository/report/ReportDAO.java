package psychotest.repository.report;

import psychotest.entity.ReportEntity;

import java.util.List;

public interface ReportDAO {
    void saveTransaction(ReportEntity reportEntity);
    ReportEntity getTransactions();
    List<ReportEntity> getAll();
}
