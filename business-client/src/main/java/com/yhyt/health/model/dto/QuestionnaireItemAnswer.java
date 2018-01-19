package com.yhyt.health.model.dto;

/**
 * @author gsh
 * @create 2017-11-16 17:27
 **/
public class QuestionnaireItemAnswer {
   private Long  id;//问题id
   private String type;//题目类型 1-单选题；2-多选题；3-简答题；4-滑块题；5-图片题
   private String answer;//答案 多选题以，隔开，多图片地址以，隔开。滚动输入具体的分值（参照type值赋值）
   private String  isNecessary;//1-非必填 2-必填

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(String isNecessary) {
        this.isNecessary = isNecessary;
    }
}
