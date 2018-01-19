package com.yhyt.health.dao;

import com.yhyt.health.model.Questionnaire;
import com.yhyt.health.model.dto.QuestionnaireAppAllVo;
import com.yhyt.health.model.dto.QuestionnaireAppVo;
import com.yhyt.health.model.dto.QuestionnaireDTO;
import com.yhyt.health.model.dto.QuestionnairePatientVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuestionnaireMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    Questionnaire selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);

//     QuestionnaireDTO selectById(Long id);
    List<QuestionnaireDTO> selectQuestionnaireListPage(Map<String,Object> params);

    //app查询历史问卷
    List<QuestionnaireAppVo> selectQuestionnaireList(Map<String, Object> map);

    List<QuestionnaireAppAllVo> selectQuestionnaireListAll(Map<String, Object> map);

    List<QuestionnaireAppAllVo> selectQuestionnaireListAllMore(Map<String, Object> map);

    List<QuestionnaireAppVo> getMyQuestionnairePatient(Map<String, Object> map);

    List<QuestionnaireAppVo> searchQuestionnaire(Map<String, Object> map);

    Questionnaire getQuestionnaireBySendId(Long questionnaireDoctorPatientId);

    List<QuestionnairePatientVo> getMyPatientQuestionnaire(@Param("patientId") Long patientId,@Param("isFilled")Byte isFilled);


}