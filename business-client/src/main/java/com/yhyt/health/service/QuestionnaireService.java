package com.yhyt.health.service;

import com.yhyt.health.model.QuestionnaireDept;
import com.yhyt.health.model.QuestionnaireDisease;
import com.yhyt.health.model.QuestionnaireQuery;
import com.yhyt.health.model.dto.QuestionnaireDTO;
import com.yhyt.health.util.Page;

import java.util.List;

public interface QuestionnaireService  {

    public long addQuestionnaire(QuestionnaireDTO questionnaireDTO);

//    public int deleteQuestionnaire(Long id);

    /**
     * 下线
     * @param id
     * @param state
     * @return
     */
    public int updateState(Long id,Byte state)/*throws BusinessException*/;

    public QuestionnaireDTO selectById(Long id);
    public List<QuestionnaireDept> selectQuestionnaireDeptById(Long id);
    public List<QuestionnaireDisease> selectQuestionnaireDiseaseById(Long id);

    public Page<QuestionnaireDTO> selectQuestionnaireListPage(QuestionnaireQuery questionnaireQuery);
    public int deleteQuestionnaireDept(Long id);
    public int deleteQuestionnaireDisease(Long id);
    public int addQuestionnaireDeptBatch(List<QuestionnaireDept> questionnaireDepts);
    public int addQuestionnaireDiseaseBatch(List<QuestionnaireDisease> questionnaireDiseases);
}
