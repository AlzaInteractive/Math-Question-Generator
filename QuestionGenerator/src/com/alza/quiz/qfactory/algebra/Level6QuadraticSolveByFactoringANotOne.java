package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.ISingleQuizPrimaryAttributeGenerator;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level6QuadraticSolveByFactoringANotOne implements IQuestionFactory {

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level6QuadraticSolveByFactoringANotOne.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;

	public Level6QuadraticSolveByFactoringANotOne(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}

	public Level6QuadraticSolveByFactoringANotOne() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	private void initStringFromLocale() {
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);

	}

	@Override
	public Quiz generateQuiz() {
		List<Quiz> quizList = generateQuizList();
		int rnd = new Random().nextInt(quizList.size());
		return quizList.get(rnd);
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		return generateQuiz();
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i = 0; i < this.numOfQuestion; i++) {
			ProblemSkeleton p = generateUniqueProblem(i);
			Quiz q = p.generateSingleQuiz();
			setQuizSecondaryAttributes(q);
			q.setSolutionSteps(p.generateSolutionSteps());
			lq.add(q);
		}
		return lq;
	}

	private ProblemSkeleton generateUniqueProblem(int idx) {
		ProblemSkeleton p;
		do {
			p = new ProblemSkeleton(idx);
		} while (qMap.containsKey(p.hash()));
		qMap.put(p.hash(), p);
		return p;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}

	private void setQuizSecondaryAttributes(Quiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level5.quadratic"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}

	protected class ProblemSkeleton implements ISingleQuizPrimaryAttributeGenerator {
		int a,b,c;			
		int num1;
		int num2;
		int split1,split2;
		String var;		
		final String[] VARSYM = { "x", "y", "z" };
		ProblemSkeleton(int idx) {			
			int modA,modAInv,gcdABC;			
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			do {
				// default positive pair
				num1 = ThreadLocalRandom.current().nextInt(2, 9);
				num2 = ThreadLocalRandom.current().nextInt(2, 9);
				int mod = idx % 3;
				if (mod==1) {
					// positive and negative pair
					num1 = num1 * CommonFunctionAndValues.getRandom(new int[] {1,-1});
					if (num1 > 0) {
						num2 = num2 * -1;
					}
				} else if (mod==2) {
					// negative pair
					num1 = num1 * -1;
					num2 = num2 * -1;					
				}
				a = ThreadLocalRandom.current().nextInt(2, 6);
				b = num1 + a*num2;
				c = num1 * num2;
				split1 = a*num2;
				split2 = num1;
				modA = num1 % a;
				modAInv = a % num1;
				gcdABC = MathUtils.findGCD(a,b,c);
			} while (num1 == num2 || num1 + num2 == 0 
					|| modA==0 || modAInv==0 || gcdABC > 1);			
			
		}

		int hash() {
			String s = a + " " +b+" "+c;
			return (CommonFunctionAndValues.hashSimple(s));
		}

		private String replaceAllSymbols(String s) {
			s = s.replace("avar", String.valueOf(this.a));
			s = s.replace("bvar", String.valueOf(this.b));
			s = s.replace("cvar", String.valueOf(this.c));			
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();		
				
			SolutionStep step1 = new SolutionStep();			
			String exp = "$$a=avar$$, $$b=bvar$$, $$c=cvar$$";				
			exp = replaceAllSymbols(exp);
			step1.setExpression(exp);
			step1.setExplanation("Determine $$a$$, $$b$$, $$c$$. Refer to general form $$ax^2+bx+c$$ ");
			steps.add(step1);
												
			SolutionStep step2 = new SolutionStep();			
			exp = "$$"+(num2*this.a)+"+"+num1+"="+(this.b)+"$$ and "
					+ "$$"+(num2*this.a)+"\\times"+num1+"="+(this.a)+" \\times "+(this.c)+"$$";						
			step2.setExpression(exp);
			step2.setExplanation("Find pair of numbers which sum is $$b$$, and multiply to  $$a \\times c$$");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();			
			exp = generateSplittedTerm();						
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);
			step3.setExplanation("Use both numbers to split the-$$"+this.var+"$$ term");			
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();			
			exp = generateGroupedSplittedTerm();						
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step4.setExpression(exp);
			step4.setExplanation("Group the first two terms, also group the last two");			
			steps.add(step4);
			
			SolutionStep step5 = new SolutionStep();			
			exp = generateFactoredGroupedTerm();						
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step5.setExpression(exp);
			step5.setExplanation("Factor out $$"+this.a+this.var+""
					+ "$$ from the first group"
					+ " and $$"+this.num1+"$$ from the second");			
			steps.add(step5);
			
			SolutionStep step6 = new SolutionStep();			
			exp = generateQuadraticForm()+"="+generateFactoredForm() +"=0 ";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step6.setExpression(exp);			
			step6.setExplanation("Rewrite the form to its factored one");
			steps.add(step6);
			
			SolutionStep step7 = new SolutionStep();			
			exp = "$$"+generateFirstFactorIsZero()+"$$ or $$"
					+generateSecondFactorIsZero()+"$$";						
			exp = replaceAllSymbols(exp);			
			step7.setExpression(exp);
			step7.setExplanation("To satisfy the equation, either factor must be zero");
			steps.add(step7);
			
			SolutionStep step8 = new SolutionStep();			
			exp = "$$VAR="+(-num1)+"/avar$$ or $$VAR="+(-num2)+"$$";						
			exp = replaceAllSymbols(exp);			
			step8.setExpression(exp);
			step8.setExplanation("Solve for $$"+this.var+"$$");
			steps.add(step8);
			
			return steps;
		}

		private String[] wrongChoices() {
			int ans1 = num1 * -1;
			int ans2 = num2 * -1;
			
			String wrongAns1a = num1+"/"+this.a;			
			
			String[] choices = { 
					wrongAns1a+","+num2, 
					wrongAns1a+","+ans2,
					ans1+","+num2,					
					};			
			return choices;
		}
		
		private String generateFirstFactorIsZero() {
			String firstFactor;
			if (num1>0) {
				firstFactor = "("+this.a+var+"+"+num1+")";
			} else {
				firstFactor = "("+this.a+var+num1+")";
			}			
			return firstFactor+"=0";
		}
		
		private String generateSecondFactorIsZero() {
			String secondFactor;
			if (num2>0) {
				secondFactor = "("+var+"+"+num2+")";
			} else {
				secondFactor = "("+var+num2+")";
			}			
			return secondFactor+"=0";
		}
		
		private String generateQuadraticForm() {
			String s = "avarVAR^2";
			if (b > 0) {
				s += "+bvarVAR";
			} else {
				s += "bvarVAR";
			}
			if (c > 0) {
				s += "+cvar";
			} else {
				s += "cvar";
			}			
			s = replaceAllSymbols(s);
			return s;
		}
		private String generateFactoredForm() {
			String firstFactor,secondFactor;
			if (num1>0) {
				firstFactor = "("+this.a+var+"+"+num1+")";
			} else {
				firstFactor = "("+this.a+var+num1+")";
			}
			if (num2>0) {
				secondFactor = "("+var+"+"+num2+")";
			} else {
				secondFactor = "("+var+num2+")";
			}
			return firstFactor+secondFactor;
		}
		
		public String generateSplittedTerm() {
			String s = "avarVAR^2";
			
			if (split1 > 0) {
				s += " + "+split1+"VAR";
			} else {
				int invSplit1 = - split1;
				s += " - "+invSplit1+"VAR";
			}
			if (split2 > 0) {
				s += " + "+split2+"VAR";
			} else {
				int invSplit2 = - split2;
				s += " - "+invSplit2+"VAR";
			}
			if (c > 0) {
				s += " + cvar";
			} else {
				int invC = - c;
				s += " - "+invC;
			}			
			s = replaceAllSymbols(s);
			return s;
		}
		
		public String generateGroupedSplittedTerm() {
			String s = "(avarVAR^2";
			
			if (split1 > 0) {
				s += " + "+split1+"VAR";
			} else {
				int invSplit1 = - split1;
				s += " - "+invSplit1+"VAR";
			}
			s += ")";
			if (split2 > 0) {
				s += " + ("+split2+"VAR";
			} else {
				int invSplit2 = - split2;
				s += " - ("+invSplit2+"VAR";
			}
			if (c > 0) {
				s += " + cvar)";
			} else {
				int invC = - c;
				s += " - "+invC+")";
			}			
			s = replaceAllSymbols(s);
			return s;
		}
		
		public String generateFactoredGroupedTerm() {
			String s = "avarVAR";
			
			if (num2 > 0) {
				s += "(VAR + "+this.num2+")";
			} else {
				int invNum2 = - this.num2;
				s += "(VAR - "+invNum2+")";
			}
			if (num1 > 0) {
				s += " + "+this.num1;				
			} else {
				int invNum1 = - this.num1;
				s += " - "+invNum1;
			}
			
			if (num2 > 0) {
				s += "(VAR + "+this.num2+")";
			} else {
				int invNum2 = - this.num2;
				s += "(VAR - "+invNum2+")";
			}
						
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestion() {
			String s = "avarVAR^2";
			if (b > 0) {
				s += "+bvarVAR";
			} else {
				s += "bvarVAR";
			}
			if (c > 0) {
				s += "+cvar";
			} else {
				s += "cvar";
			}
			s += "=0";
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = generateQuestion();
			s = replaceAllSymbols(s);
			s = CommonFunctionAndValues.enclosedWithMathJaxExp(s);
			String s2 = var + "=?";
			s2 = CommonFunctionAndValues.enclosedWithMathJaxExp(s2);
			s = s + " " + s2;
			return s;
		}

		@Override
		public String generateQuestionWolfram() {
			return generateQuestion();
		}

		@Override
		public String[] generateChoices() {
			int choiceSize = 3;
			List<String> choices = Arrays.asList(wrongChoices());
			String[] finalChoices = new String[choiceSize];
			Collections.shuffle(choices);
			for (int i = 0; i < choiceSize - 1; i++) {
				finalChoices[i] = choices.get(i);
			}
			finalChoices[choiceSize - 1] = generateAnswer();
			return finalChoices;
		}

		@Override
		public String generateAnswer() {
			return (num1*-1)+"/"+this.a+","+(num2*-1);
		}

		@Override
		public Quiz generateSingleQuiz() {
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion(generateQuestionMathjax());
			q.setProblemString(generateQuestionWolfram());
			q.setCorrectAnswer(generateAnswer());
			q.setChoices(generateChoices());
			return q;
		}

	}

}
