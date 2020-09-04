package psychotest.repository;

import psychotest.entity.SchedulerEntity;
import psychotest.entity.TableNameEntity;

public interface SchedulerDAO {
    void saveTableName(SchedulerEntity entity);
    void updateRemainder(String hashTableName);
}
