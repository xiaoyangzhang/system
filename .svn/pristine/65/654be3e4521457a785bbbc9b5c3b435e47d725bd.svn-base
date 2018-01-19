package com.yhyt.health.service;

import com.yhyt.health.model.OrderDetail;
import com.yhyt.health.result.AppResult;

/**
 * Created by localadmin on 17/9/1.
 */
public interface OrderDetailService {
    AppResult getPatientOrderDetail(String patientId);

    AppResult markCardUsed(long patientId, long departId);

    AppResult useCard(long patientId, long departId) throws Exception;

    AppResult cancelUseCard(long patientId, long departId);

    OrderDetail getOrderDetail(String cardId);
}
