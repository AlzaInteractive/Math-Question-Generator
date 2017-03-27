package com.alza.quiz.qfactory.kpk;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.util.CommonFunctionAndValues;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class WhichDayScenarioKPKQuestionFactory extends TwoNumKPKQuestionFactory {
    private List<String> scenarios = new ArrayList<String>();

    public WhichDayScenarioKPKQuestionFactory(){
        super();
        prepareScenario();
    }
    @Override
    public MultipleChoiceQuiz generateQuiz() {
        int rnd = new Random().nextInt(scenarios.size());
        MultipleChoiceQuiz q;
        q = (MultipleChoiceQuiz) generateQuizList().get(rnd);
        return q;
    }
    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        int[][] numPair = CommonFunctionAndValues.simpleIntPairs;
        CommonFunctionAndValues.shuffleArray(numPair);
        int i = 0;
        for (String s:scenarios){
            int refDayInInt = new Random().nextInt(6);
            String day = CommonFunctionAndValues.hari[refDayInInt];
            String sce = CommonFunctionAndValues.buildScenario(s,numPair[i]);
            sce = sce.replace("#refday?",day);
            int correctAnswer = MathUtils.findLCM(numPair[i]) + refDayInInt;
            int rem = correctAnswer % 7;
            String correctAnswerInDay = CommonFunctionAndValues.hari[rem];
            ArrayList<String> choices = new ArrayList<String>(Arrays.asList(CommonFunctionAndValues.hari));
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setDifficultyLevel(quizLevel);
            q.setQuestion(sce);
            q.setCorrectAnswer(correctAnswerInDay);
            q.setChoices(choices);
            q.setLessonClassifier("Matematika SD");
            q.setLessonCategory("KPK & FPB");
            q.setLessonSubcategory("Soal cerita melibatkan hari");
            q.setLessonGrade(4);
            quizList.add(q);
            i++;
        }
        return  quizList;
    }

    @Override
    public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
        return null;
    }

    void prepareScenario(){
        scenarios.add("#orang1? memiliki hobi #sport1? dan #sport2?. " +
                "#sport1? tiap #val1? hari sekali di pagi hari, dan #sport2? #val2? hari sekali di sore hari " +
                "Jika hari ini adalah hari #refday? dan #orang1? melakukan kedua hobinya. " +
                "Kapan ia akan melakukan keduanya lagi di hari yang sama?");
        scenarios.add("#orang1? mengikuti les #les1? dan #les2?. " +
                "Ia les #les1? tiap #val1? hari sekali pada pagi hari, dan #les2? #val2? hari sekali pada sore hari " +
                "Jika hari ini adalah hari #refday? dan #orang1? mengikuti keduanya. " +
                "Kapan ia akan melakukan keduanya lagi di hari yang sama?");

    }
}
