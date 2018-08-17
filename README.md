# Alza Interactive Question Generator
This project produce a Java library that generate math quizzes on various topics.  
It works as a math question generator for android application(s) that aim to test students skill on certain topic.  
Question is auto generated based on certain patterns, most of them involving randomized numbers to minimize the possibility of learners memorizing the answer of certain question.

Question type available so far: 
* Simple (require direct answer), 
* Multiple choice 

Topics currently covered & being worked on are:
* Least Common Multiples & Greatest Common Denominator
* Fraction
* Integer
* Roman Numerals
* Geometry (2D & 3D)
* ..more to follows
Each topics has several question generator, arranged in levels (see usage)

## Usage
Code below can be found inside package **(com.alza.quiz.generator.examples)**, more examples available there  
``` java
public class GameLevelDemo {
  public static void main(String[] args) {
    IPlayableLevelsGroup lg = new FractionGameLevel();
    List<GameLevel> gls = lg.createGameLevels(new Locale("en","US"));
    for (GameLevel gameLevel : gls) {
      printQuizzes(gameLevel);
    }
  }
  private static void printQuizzes(GameLevel gl) {
    System.out.println("------------------------");
    System.out.println("Game Level: " + gl.getOrder()+" "+gl.getName()+": "+gl.getDesc());
    List<Quiz> lq = gl.generateQuiz();
    for (Quiz q : lq) {
      System.out.println("------------------------------");
      System.out.println("Grade : "+q.getLessonGrade());
      System.out.println("Subcategory :" +q.getLessonSubcategory());
      System.out.println("Question : " + q.getQuestion());
      System.out.println("Level : "+q.getQuizLevel());
      if (q instanceof MultipleChoiceQuiz){
        MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
        System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
      }
      System.out.println("Answer : "+ q.getCorrectAnswer());
    }
  }
}
```

## Apps using the libray:
I've built several math quiz apps on android using the library, all available in bilingual mode (English, Indonesian).
* Fraction Quiz [Google Play](https://play.google.com/store/apps/details?id=com.alza.quiz.fraction)
* LCM & GCD [Google Play](https://play.google.com/store/apps/details?id=com.alza.quiz.lcmgcd)
* Integers Quiz [Google Play](https://play.google.com/store/apps/details?id=com.alza.quiz.integer)
* Plane (2D) Geometry [Google Play](https://play.google.com/store/apps/details?id=com.alza.quiz.geom2d)
* Roman Numerals [Google Play](https://play.google.com/store/apps/details?id=com.alza.quiz.romans)

