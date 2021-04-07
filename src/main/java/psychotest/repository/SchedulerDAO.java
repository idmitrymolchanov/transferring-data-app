package psychotest.repository;

import psychotest.entity.SchedulerEntity;

public interface SchedulerDAO {
    void saveTableName(SchedulerEntity entity);
    void updateRemainder(String hashTableName);
    SchedulerEntity findSchedulerObjectByTableHash(String table_hash);
}
