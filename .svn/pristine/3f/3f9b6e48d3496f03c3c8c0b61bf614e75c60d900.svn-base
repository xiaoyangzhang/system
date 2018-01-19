package com.yhyt.health.service.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhyt.health.dao.*;
import com.yhyt.health.model.*;
import com.yhyt.health.model.dto.*;
import com.yhyt.health.spring.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gsh
 * @create 2017-11-16 15:22
 **/
@Service
public class QuestionnaireApiImpl implements QuestionnaireApi{

    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    @Autowired
    private QuestionnaireDoctorPatientMapper questionnaireDoctorPatientMapper;
    @Autowired
    private QuestionnaireItemMapper questionnaireItemMapper;
    @Autowired
    private QuestionnaireResultMapper questionnaireResultMapper;
    @Autowired
    private QuestionnairePictureMapper questionnairePictureMapper;

    @Override
    public AppResult getQuestionnaireRepertory(Long userId, String type, String moreFlag) {
        AppResult appResult = new AppResult();
        appResult.setCode("200");
        appResult.setMessage("成功");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        if ("2".equals(moreFlag)) {
            List<QuestionnaireAppAllVo> departments = questionnaireMapper.selectQuestionnaireListAllMore(map);
            appResult.setBody(departments);
            return appResult;
        }
        //如果是查询历史记录
        if ("1".equals(type)) {
            List<QuestionnaireAppVo> questionnaires = questionnaireMapper.selectQuestionnaireList(map);
            appResult.setBody(questionnaires);
            return  appResult;
        } else if ("2".equals(type)) {
            List<QuestionnaireAppAllVo> departments = questionnaireMapper.selectQuestionnaireListAll(map);
            appResult.setBody(departments);
            return appResult;
        } else {
            return appResult;
        }
    }

    @Override
    @Transactional
    public AppResult sendQuestionnaireDoctor(Long doctorId, Long patientId, Long departmentId, Long questionnaireId) {

        QuestionnaireDoctorPatient questionnaireDoctorPatient = new QuestionnaireDoctorPatient();
        questionnaireDoctorPatient.setCreateTime(new Date());
        questionnaireDoctorPatient.setUpdateTime(new Date());
        questionnaireDoctorPatient.setDoctorId(doctorId);
        questionnaireDoctorPatient.setPatientId(patientId);
        questionnaireDoctorPatient.setDepartmentId(departmentId);
        questionnaireDoctorPatient.setQuestionnaireId(questionnaireId);
        questionnaireDoctorPatientMapper.insertSelective(questionnaireDoctorPatient);
        Questionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("questionnaireDoctorPatientId", questionnaireDoctorPatient.getId());
        map.put("title", questionnaire.getTitle());
        AppResult appResult = new AppResult("200", "成功", map);
        return appResult;
    }

    @Override
    public AppResult getSendQuestionnaireDoctor(Long questionnaireDoctorPatientId, Long questionnaireId) {
        AppResult appResult = new AppResult("200", "成功",null);
        Map<String, Object> map = new HashMap<String,Object>();
        Map<String, Object> mapResult = new HashMap<String, Object>();
        if (null != questionnaireDoctorPatientId) {
            map.put("questionnaireDoctorPatientId", questionnaireDoctorPatientId);
            QuestionnaireDoctorPatient questionnaireDoctorPatient = questionnaireDoctorPatientMapper.selectByPrimaryKey(questionnaireDoctorPatientId);
            mapResult.put("departmentId", questionnaireDoctorPatient.getDepartmentId());
            mapResult.put("patientId", questionnaireDoctorPatient.getPatientId());
            if ((byte) 2 == questionnaireDoctorPatient.getIsFilled()) {//已填写
                map.put("questionnaireDoctorPatientId", questionnaireDoctorPatientId);
                List<QuestionnaireAppCardPatient> questionnaireAppCardPatients = questionnaireResultMapper.getQuestionnairePatient(map);
                mapResult.put("questionnaire", questionnaireAppCardPatients);
                appResult.setBody(mapResult);
            } else {
                List<QuestionnaireAppCard> questionnaireAppCards = questionnaireItemMapper.selectQuestionnaireCard(map);
                mapResult.put("questionnaire", questionnaireAppCards);
                appResult.setBody(mapResult);
            }
            return appResult;
        }
        map.put("questionnaireId", questionnaireId);
        List<QuestionnaireAppCard> questionnaireAppCards = questionnaireItemMapper.selectQuestionnaireCard(map);
        mapResult.put("questionnaire", questionnaireAppCards);
        appResult.setBody(mapResult);
        return appResult;
    }

    @Override
    @Transactional
    public AppResult submitQuestionnairePatient(Long questionnaireDoctorPatientId, Long questionnaireId
            ,String questionnaireItemAnswerList) {
        AppResult appResult = new AppResult("200", "成功", null);
        QuestionnaireDoctorPatient questionnaireDoctorPatient
                = questionnaireDoctorPatientMapper.selectByPrimaryKey(questionnaireDoctorPatientId);
        if ((byte) 2 == questionnaireDoctorPatient.getIsFilled()) {
            appResult.getStatus().setCode("201");
            appResult.getStatus().setMessage("已经提交，无需重复提交");
            return appResult;
        }
        if (null == questionnaireId) {
            questionnaireId = questionnaireDoctorPatient.getQuestionnaireId();
        }
        JSONArray answerns = JSONArray.parseArray(questionnaireItemAnswerList);
        for (int i = 0; i < answerns.size(); i++) {
            QuestionnaireResult questionnaireResult = new QuestionnaireResult();
            QuestionnaireItem questionnaireItem = questionnaireItemMapper.selectByPrimaryKey(answerns.getJSONObject(i).getLong("id"));
            questionnaireResult.setQuestionnaireDoctorPatientId(questionnaireDoctorPatientId);
            questionnaireResult.setAnswerA(questionnaireItem.getAnswerA());
            questionnaireResult.setAnswerB(questionnaireItem.getAnswerB());
            questionnaireResult.setAnswerC(questionnaireItem.getAnswerC());
            questionnaireResult.setAnswerD(questionnaireItem.getAnswerD());
            questionnaireResult.setAnswerE(questionnaireItem.getAnswerE());
            questionnaireResult.setAnswerF(questionnaireItem.getAnswerF());
            questionnaireResult.setUpdateTime(new Date());
            questionnaireResult.setTitle(questionnaireItem.getTitle());
            questionnaireResult.setMaxDescription(questionnaireItem.getMaxDescription());
            questionnaireResult.setMinDescription(questionnaireItem.getMinDescription());
            questionnaireResult.setRatioScope(questionnaireItem.getRatioScope());
            questionnaireResult.setUpdateTime(new Date());
            questionnaireResult.setQuestionnaireId(questionnaireItem.getQuestionnaireId());
            questionnaireResult.setQuestionnaireItemId(questionnaireItem.getId());
            questionnaireResult.setType(questionnaireItem.getType());
//            questionnaireResult.setId(questionnaireItemAnswer.getId());
            //题目类型 1-单选题；2-多选题；3-简答题；4-滑块题；5-图片题
            if ("5".equals(answerns.getJSONObject(i).getString("type"))) {
                questionnaireResultMapper.insertSelective(questionnaireResult);
                if (null == answerns.getJSONObject(i).getString("answer") || "".equals(answerns.getJSONObject(i).getString("answer"))) {
                    continue;
                }
                String[] strs = answerns.getJSONObject(i).getString("answer").split(",");
                for (String str : strs) {
                    QuestionnairePicture questionnairePicture = new QuestionnairePicture();
                    questionnairePicture.setQuestionnaireItemId(questionnaireResult.getId());
                    questionnairePicture.setCreateTime(new Date());
                    questionnairePicture.setUrl(str);
                    questionnairePictureMapper.insertSelective(questionnairePicture);
                }
            } else {
                questionnaireResult = genQuestionnaireResult(questionnaireResult,answerns.getJSONObject(i));
                questionnaireResultMapper.insertSelective(questionnaireResult);
            }
        }
        questionnaireDoctorPatient.setIsFilled((byte) 2);
        questionnaireDoctorPatient.setUpdateTime(new Date());
        questionnaireDoctorPatientMapper.updateByPrimaryKeySelective(questionnaireDoctorPatient);
        Map<String, Object> map = new HashMap<String, Object>();
        Questionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        map.put("questionnaireDoctorPatientId", questionnaireDoctorPatient.getId());
        map.put("title", questionnaire.getTitle());
        map.put("patientId", questionnaireDoctorPatient.getPatientId());
        map.put("doctorId", questionnaireDoctorPatient.getDoctorId());
        appResult.setBody(map);
        return appResult;
    }

    @Override
    public AppResult getQuestionnairePatient(Long questionnaireDoctorPatientId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("questionnaireDoctorPatientId", questionnaireDoctorPatientId);
        List<QuestionnaireAppCardPatient> questionnaireAppCardPatients = questionnaireResultMapper.getQuestionnairePatient(map);
        AppResult appResult = new AppResult("200", "成功", questionnaireAppCardPatients);
        return appResult;
    }

    @Override
    public AppResult getMyQuestionnairePatient(Long userId, String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (null!=type && !"".equals(type))
            map.put("type", Byte.valueOf(type));
        List<QuestionnaireAppVo> questionnaires = questionnaireMapper.getMyQuestionnairePatient(map);
        AppResult appResult = new AppResult("200", "成功", questionnaires);
        return appResult;
    }

    @Override
    public AppResult searchQuestionnaire(String keyWord,Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyWord", keyWord);
//        map.put("userId", userId);
        List<QuestionnaireAppVo> questionnaires = questionnaireMapper.searchQuestionnaire(map);
        AppResult appResult = new AppResult("200", "成功", questionnaires);
        return appResult;
    }

    public QuestionnaireResult genQuestionnaireResult(QuestionnaireResult questionnaireResult, JSONObject questionnaireItemAnswer) {
        if (null == questionnaireItemAnswer.getString("answer") || "".equals(questionnaireItemAnswer.getString("answer"))) {
            return questionnaireResult;
        }
        if ("1".equals(questionnaireItemAnswer.getString("type")) || "2".equals(questionnaireItemAnswer.getString("type"))) {
            String[] answers = questionnaireItemAnswer.getString("answer").split(",");
            for (String str : answers) {
                if ("A".equals(str)) {
                    questionnaireResult.setaSelected((byte)2);
                } else if ("B".equals(str)) {
                    questionnaireResult.setbSelected((byte)2);
                }else if ("C".equals(str)) {
                    questionnaireResult.setcSelected((byte)2);
                }else if ("D".equals(str)) {
                    questionnaireResult.setdSelected((byte)2);
                }else if ("E".equals(str)) {
                    questionnaireResult.seteSelected((byte)2);
                } else if ("F".equals(str)) {
                    questionnaireResult.setfSelected((byte)2);
                } else {
                }
            }
        } else if ("3".equals(questionnaireItemAnswer.getString("type"))) {
            questionnaireResult.setAnswerInput(questionnaireItemAnswer.getString("answer"));
        } else if ("4".equals(questionnaireItemAnswer.getString("type"))) {
            questionnaireResult.setRatio(Integer.valueOf(questionnaireItemAnswer.getString("answer")));
        }
        return questionnaireResult;
    }

    @Override
    public AppResult getMyPatientQuestionnaire(Long patientId, String type, Integer pageIndex, Integer pageSize) {
        Byte isFilled =null;
        if (null != type && !"".equals(type)) {
            isFilled = Byte.valueOf(type);
        }
        AppResult appResult = new AppResult("200", "成功", questionnaireMapper.getMyPatientQuestionnaire(patientId, isFilled));
        return appResult;
    }
}
