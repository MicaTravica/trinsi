package com.app.trinsi.mapper;

import com.app.trinsi.dto.QuestionnaireDTO;
import com.app.trinsi.model.QuestionnairePA;

public class QuestionnaireMapper {

    public static QuestionnairePA toQuestionnaire(QuestionnaireDTO questionnaireDTO) {
        return new QuestionnairePA(questionnaireDTO.getVigorousDays(), questionnaireDTO.getVigorousMinutes(), 0,
                questionnaireDTO.getModerateDays(), questionnaireDTO.getModerateMinutes(), 0,
                questionnaireDTO.getWalkingDays(), questionnaireDTO.getWalkingMinutes(), 0);
    }
}
