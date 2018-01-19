package com.yhyt.health.service.api;

import com.yhyt.health.model.dto.QuestionnaireItemAnswer;
import com.yhyt.health.spring.AppResult;

import java.util.List;

/**
 * api接口
 * @author gsh
 * @create 2017-11-16 15:09
 **/
public interface QuestionnaireApi {
   AppResult getQuestionnaireRepertory(Long userId, String type, String moreFlag);

   AppResult sendQuestionnaireDoctor(Long doctorId,Long patientId,Long departmentId,Long questionnaireId);

   AppResult getSendQuestionnaireDoctor(Long questionnaireDoctorPatientId, Long questionnaireId);

   AppResult submitQuestionnairePatient(Long questionnaireDoctorPatientId, Long questionnaireId, String questionnaireItemAnswerList);

   AppResult getQuestionnairePatient(Long questionnaireDoctorPatientId);

   AppResult getMyQuestionnairePatient(Long userId, String type);

   AppResult searchQuestionnaire(String keyWord,Long userId);

   AppResult getMyPatientQuestionnaire(Long patientId, String type, Integer pageIndex, Integer pageSize);
}
