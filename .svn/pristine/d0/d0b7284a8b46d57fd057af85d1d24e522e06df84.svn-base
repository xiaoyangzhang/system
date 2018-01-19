package com.yhyt.health.dao;

import com.yhyt.health.model.SysServiceTask;
import com.yhyt.health.model.dto.*;
import com.yhyt.health.model.query.CustomerServiceQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysServiceTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysServiceTask record);

    int insertSelective(SysServiceTask record);

    SysServiceTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysServiceTask record);

    int updateByPrimaryKey(SysServiceTask record);

    /**
     * 客服任务列表
     * @param query
     * @return
     */
    List<CustomerServiceDTO> selectServiceListPage(@Param("map") Map<String,Object> params );

    /**
     * 客服任务详情
     * @param id
     * @return
     */
    ServiceDetailDTO selectById(Long id);
    SysServiceTask selectByOrderId(Long orderId);
    TaskStatisticsDTO selectStatisticsCount(CustomerServiceQuery query);

    /**
     * 一键释放
     * @param operator
     * @return
     */
    int releaseTaskByOperator(@Param("params") Map<String,Object> params);

    List<SysServiceTask> selectByOperator(String operator);

    List<ItemListVo> getItems(@Param("patientId") Long patientId,@Param("departmentId") Long departmentId ,@Param("doctorState") Byte doctorState);

    Long getItemCountsByState(@Param("patientId") Long patientId,@Param("departmentId") Long departmentId ,@Param("doctorState") Byte doctorState);

    ServiceTaskVo getItem(@Param("id") Long id);

    SysServiceTaskVo getSysServiceTask(@Param("id") Long id);

    Long getItemCountsByStates(@Param("patientId") Long patientId,@Param("departmentId") Long departmentId ,@Param("doctorStates") List<Byte> doctorStates);


}