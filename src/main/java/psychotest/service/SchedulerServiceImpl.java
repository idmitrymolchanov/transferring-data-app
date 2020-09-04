package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.SchedulerEntity;
import psychotest.repository.SchedulerDAO;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final SchedulerDAO schedulerDAO;

    @Autowired
    public SchedulerServiceImpl(SchedulerDAO schedulerDAO) {
        this.schedulerDAO = schedulerDAO;
    }

    @Override
    public void saveTableName(SchedulerEntity entity) {
        schedulerDAO.saveTableName(entity);
    }

    @Override
    public void updateRemainder(String hashTableName) {
        schedulerDAO.updateRemainder(hashTableName);
    }
}
