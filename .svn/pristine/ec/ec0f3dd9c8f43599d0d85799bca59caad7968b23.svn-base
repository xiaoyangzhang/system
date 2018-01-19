package com.yhyt.health.dao;

import com.yhyt.health.model.QuestionnaireDisease;
import com.yhyt.health.model.dto.QuestionnaireDiseaseDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireDiseaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionnaireDisease record);

    int insertSelective(QuestionnaireDisease record);

    QuestionnaireDisease selectByPrimaryKey(Long id);
    List<QuestionnaireDiseaseDTO> selectByQuestionnaireId(Long questionnaireId);

    int updateByPrimaryKeySelective(QuestionnaireDisease record);

    int updateByPrimaryKey(QuestionnaireDisease record);
    List<QuestionnaireDisease> selectQuestionnaireDiseaseById(@Param("questionnaireId") Long id);
    int insertBatch(@Param("list") List<QuestionnaireDisease> record);


}