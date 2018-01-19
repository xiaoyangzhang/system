package com.yhyt.health.controller;

import com.yhyt.health.model.dto.QuestionnaireItemAnswer;
import com.yhyt.health.service.api.QuestionnaireApi;
import com.yhyt.health.spring.AppResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问卷接口
 * @author gsh
 * @create 2017-11-16 13:51
 **/
@Api(value="",description ="问卷接口")
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireAppController {

    @Autowired
    private QuestionnaireApi questionnaireApi;

    @ApiOperation(value = "问卷库（历史/全部问卷", notes = "问卷库（历史/全部问卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "1：历史问卷 2：全部问卷", paramType = "path", required = true, dataType = "String"),
            @ApiImplicitParam(name = "moreFlag", value = "1：否（不填默认为1），2：是", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("/questionnaireRepertory/{type}")
    public AppResult getQuestionnaireRepertory(
            @RequestParam Long userId,
            @PathVariable String type,
            @RequestParam(required = false) String moreFlag
    ) {
        return questionnaireApi.getQuestionnaireRepertory(userId,type,moreFlag);
    }

    @ApiOperation(value = "发送问卷卡片", notes = "发送问卷卡片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "departmentId", value = "科室id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "patientId", value = "患者id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "questionnaireId", value = "问卷id", paramType = "query", required = true, dataType = "Long")
    })
    @PostMapping("/questionnaireDoctor")
    public AppResult sendQuestionnaireDoctor(
            @RequestParam Long userId,
            @RequestParam Long patientId,
            @RequestParam Long departmentId,
            @RequestParam Long questionnaireId
    ) {
        return questionnaireApi.sendQuestionnaireDoctor(userId, patientId, departmentId, questionnaireId);
    }

    @ApiOperation(value = "查看发送问卷卡片", notes = "查看发送问卷卡片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionnaireDoctorPatientId", value = "卡片id", paramType = "query", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "questionnaireId", value = "问卷id", paramType = "query", required = false, dataType = "Long")
    })
    @GetMapping("/questionnaireDoctor")
    public AppResult getSendQuestionnaireDoctor(
            @RequestParam(required = false) Long questionnaireDoctorPatientId,
            @RequestParam(required = false) Long questionnaireId
    ) {
        return questionnaireApi.getSendQuestionnaireDoctor(questionnaireDoctorPatientId, questionnaireId);
    }

    @ApiOperation(value = "提交问卷", notes = "提交问卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionnaireDoctorPatientId", value = "卡片id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "questionnaireId", value = "问卷id", paramType = "query", required = false, dataType = "Long")
    })
    @PostMapping("/questionnairePatient")
    public AppResult submitQuestionnairePatient(
            @RequestParam(required = true) Long questionnaireDoctorPatientId,
            @RequestParam(required = false) Long questionnaireId,
            @ApiParam @RequestParam(required = true) String questionnaireItems
    ) {
        return questionnaireApi.submitQuestionnairePatient(questionnaireDoctorPatientId, questionnaireId, questionnaireItems);
    }

    @ApiOperation(value = "查看问卷卡片(患者发送卡片)", notes = "查看问卷卡片(患者发送卡片)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionnaireDoctorPatientId", value = "卡片id", paramType = "query", required = true, dataType = "Long")
    })
    @GetMapping("/questionnairePatient")
    public AppResult getQuestionnairePatient(
            @RequestParam(required = true) Long questionnaireDoctorPatientId
    ) {
        return questionnaireApi.getQuestionnairePatient(questionnaireDoctorPatientId);
    }

    @ApiOperation(value = "我的问卷", notes = "我的问卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",paramType = "query",required = false,dataType = "Long"),
            @ApiImplicitParam(name = "type",value = "1：未填写2：已填写，3：全部",paramType = "path",required = true,dataType = "String")
    })
    @PostMapping("/myQuestionnairePatient/{type}")
    public AppResult myQuestionnairePatient(
            @PathVariable String type,
            @RequestParam(required = false) Long userId
    ) {
        if ("3".equals(type)) {
            type=null;
        }
        return questionnaireApi.getMyQuestionnairePatient(userId,type);
    }

    @ApiOperation(value = "问卷搜索", notes = "问卷搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord",value = "关键字",paramType = "query",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userId",value = "用户id",paramType = "query",required = false,dataType = "Long")
    })
    @GetMapping("/searchQuestionnaire")
    public AppResult searchQuestionnaire(
            @RequestParam(required = true) String keyWord,
            @RequestParam(required = true) Long userId
    ) {
        return questionnaireApi.searchQuestionnaire(keyWord,userId);
    }

    @ApiOperation(value = "我的问卷（患者端）", notes = "我的问卷（患者端）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "患者Id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "为空时为全部,1：未填写 2：已填写", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", paramType = "query", required = false, dataType = "Integer")
    })
    @GetMapping("/getMyPatientQuestionnaire")
    public AppResult getMyPatientQuestionnaire(
            @RequestParam Long patientId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false)  Integer pageSize
    ) {
        return questionnaireApi.getMyPatientQuestionnaire(patientId,type,pageIndex,pageSize);
    }







}
