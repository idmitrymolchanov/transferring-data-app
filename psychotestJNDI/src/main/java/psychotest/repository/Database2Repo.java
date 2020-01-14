package psychotest.repository;

import psychotest.database2.entity.SberTest2;

import java.util.List;

public interface Database2Repo {
    public void saveBatch(final List<SberTest2> employeeList);
}