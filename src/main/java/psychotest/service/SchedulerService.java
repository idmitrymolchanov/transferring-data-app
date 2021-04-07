package psychotest.service;

import psychotest.entity.SchedulerEntity;

public interface SchedulerService {
    void saveTableName(SchedulerEntity entity);
    void updateRemainder(String hashTableName);
    SchedulerEntity findObjectByTableHash(String table_hash);
}
