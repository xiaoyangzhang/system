package com.yhyt.health.dao;

import com.yhyt.health.model.QuestionnaireDept;
import com.yhyt.health.model.dto.QuestionnaireDeptDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionnaireDept record);

    int insertSelective(QuestionnaireDept record);
    int insertBatch(@Param("list") List<QuestionnaireDept> record);

    QuestionnaireDept selectByPrimaryKey(Long id);
    List<QuestionnaireDeptDTO> selectByQuestionnaireId(Long questionnaireId);

    int updateByPrimaryKeySelective(QuestionnaireDept record);

    int updateByPrimaryKey(QuestionnaireDept record);
     List<QuestionnaireDept> selectQuestionnaireDeptById(@Param("questionnaireId") Long id);
     List<QuestionnaireDept> selectQuestionnaireDeptByDeptId(@Param("dictDepartmentId") Long id);

}