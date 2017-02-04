package com.alza.quiz.qfactory.kpk;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.util.CommonFunctionAndValues;
import com.alza.common.math.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class BasicScenarioKPKQuestionFactory extends BasicKPKQuestionFactory {
    private List<String> scenarios = new ArrayList();

    public BasicScenarioKPKQuestionFactory(){
        super();
        this.quizLevel = QuizLevel.MUDAH;
        prepareScenario();
    }
    public MultipleChoiceQuiz generateQuiz() {
        int rnd = new Random().nextInt(scenarios.size());
        MultipleChoiceQuiz q;
        q = (MultipleChoiceQuiz) generateQuizList().get(rnd);
        return q;
    }

    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        for (String sce :scenarios){
            String[] pairPeople = CommonFunctionAndValues.getPairofPeople();
            pairs = CommonFunctionAndValues.getPairOfIntSimple();
            sce = CommonFunctionAndValues.buildScenario(sce,pairPeople,pairs);
            int correctAnswer = MathUtils.findLCM(pairs);
            choices = new ArrayList<>();
            addChoices(correctAnswer);
            generateChoices();
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setLessonGrade(4);
            q.setDifficultyLevel(quizLevel);
            q.setQuestion(sce);
            q.setCorrectAnswer(String.valueOf(correctAnswer));
            q.setChoices(choices);
            q.setLessonClassifier("Matematika SD");
            q.setLessonCategory("KPK");
            q.setLessonSubcategory("Soal cerita sederhana");
            quizList.add(q);
        }


        return quizList;
    }

    @Override
    public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
        return null;
    }

    void prepareScenario(){
        String sceOne = "Dua bersaudara #orang1? dan #orang2? memiliki hobi bersepeda. " +
                "#orang1? bersepeda #val1? hari sekali sedangkan #orang2? bersepeda #val2? hari sekali. " +
                "Jika hari ini mereka bersepeda bersama, berapa hari lagi mereka akan dapat melakukannya bersama?";
        String sceTwo = "#orang1? menyukai bakso dan rujak. Hari ini, ia dapat menikmati keduanya karena " +
                "tukang bakso dan tukang rujak melewati depan rumahnya. " +
                "Jika tukang bakso lewat #val1? hari sekali dan tukang rujak #val2?, " +
                "berapa hari lagi #orang1? akan dapat menikmati keduanya?";
        String sceThree = "#orang1? memasang lampu hias 17-an di depan rumahnya. " +
                "Lampu tersebut terdiri atas 150 lampu berwarna kuning, merah dan biru. " +
                "#orang1? mengatur agar lampu merah berkedip tiap #val1? detik, " +
                "sedangkan lampu biru berkedip tiap #val2? detik dan lampu kuning berkedip tiap detik. " +
                "Saat ini lampu kuning, merah dan biru menyala bersama. " +
                "Mereka akan menyala bersama lagi setelah .... detik. ";
        scenarios.add(sceOne);
        scenarios.add(sceTwo);
        scenarios.add(sceThree);
    }
}
