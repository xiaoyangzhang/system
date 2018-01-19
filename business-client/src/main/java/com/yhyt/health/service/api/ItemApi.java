package com.yhyt.health.service.api;

import com.yhyt.health.spring.AppResult;

import java.util.List;

/**
 * @author gsh
 * @create 2018-01-02 13:56
 **/
public interface ItemApi {

    AppResult getItems(Long departmentId,String doctorState);

    AppResult getItem(Long taskId);

    AppResult updateItemState(Long taskId, String doctorState,Long userId);

    AppResult genTaskService(Long itemId,Long orderId);

    AppResult getTaskServiceState(Long orderId);

    AppResult getItemCounts(Long departmentId,List<String> doctorState);
}
