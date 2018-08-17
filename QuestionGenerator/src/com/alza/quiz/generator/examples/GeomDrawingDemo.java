package com.alza.quiz.generator.examples;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.GameLevel;
import com.alza.quiz.model.GameLevelQuestionFactory;
import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.geom.Geom;
import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point2D;
import com.alza.quiz.qfactory.fraction.FractionRepresentationInAPie;
import com.alza.quiz.qfactory.geom.GeomGameLevel;
import com.alza.quiz.qfactory.geom.UtilGeom;

public class GeomDrawingDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,downPane;
	private JPanel mg;
	JTextField soal,jawab,pilihan;
	private List<Quiz> ql;
	int qnum;
	private Canvas canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeomDrawingDemo frame = new GeomDrawingDemo();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					frame.perf();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GeomDrawingDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		canvas = new Canvas();
		mg = new JPanel();
		contentPane.add(mg, BorderLayout.CENTER);
		
		JButton btnNext = new JButton("Next");
		contentPane.add(btnNext, BorderLayout.NORTH);
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//showQuestion();
				drawFractionOnPie();
			}
		});
		downPane = new JPanel();
		downPane.setLayout(new FlowLayout());
		soal = new JTextField();
		contentPane.add(downPane,BorderLayout.SOUTH);
		downPane.add(soal);
		jawab = new JTextField();
		downPane.add(jawab);
		pilihan = new JTextField();
		downPane.add(pilihan);
	}
	
	private void perf() {
		ql = new ArrayList<Quiz>();
		GeomGameLevel rgl = new GeomGameLevel();
		GameLevel gl = rgl.createGameLevels(getLocale()).get(3);
		List<GameLevelQuestionFactory> glqf = gl.getLevelQF();
		for (GameLevelQuestionFactory glq : glqf) {
			ql.addAll(glq.getqFactory().generateQuizList(glq.getqCount()));
		}
		/**
		List<IQuestionFactory> lqf = GeomGameLevel.createGameLevels(getLocale()).get(0).getLevelQF().;
		
		for (IQuestionFactory iQuestionFactory : lqf) {
			ql.addAll(iQuestionFactory.generateQuizList());
		}**/
		Collections.sort(ql);
		System.out.println(ql.size());
		qnum=0;
	}
	private void showQuestion() {
		GeomQuiz mq;
		try {
			mq = (GeomQuiz) ql.get(qnum);
		} catch (Exception e) {
			System.out.println(ql.get(qnum).getClass()+" "+ql.get(qnum).getQuestion());
			qnum++;
			return;
		}
		
		List<Path> o = mq.getGeomShape();	
		if (o == null) {
			System.out.println("Null path"+mq.getQuestion());
			qnum++;
			return;
		}
		//List<Path> o = new Trapezoid().createExample().getPaths();
		//List<Path> o = new Cuboid().createExample().getPaths();
		drawShape(o);
		if (qnum==ql.size()-1) {
			perf();
		} else qnum++;
		soal.setText(mq.getQuestion());
		jawab.setText(mq.getCorrectAnswer());
		if (mq instanceof MultipleChoiceGeomQuiz) {
			pilihan.setText(((MultipleChoiceGeomQuiz) mq).getChoices().toArray().toString());
		}
		downPane.layout();
	}

	private void drawShape(List<Path> paths)  {
		Graphics g = mg.getGraphics();
		g.clearRect(0, 0, mg.getWidth(), mg.getHeight());
		paths = UtilGeom.transformPaths(mg.getWidth(), mg.getHeight(),paths);
		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
		for (Path path : paths) {
			//System.out.println("Path type:"+path.pathType);
			if (path.pathType == Path.PATH_TYPE_LINE) {
				if (path.stroke.type == Geom.STROKE_LINE) {
					g.drawLine((int)path.start.x,(int)path.start.y,(int)path.finish.x,(int)path.finish.y);
					//System.out.println("line start: "+path.start.x+" , "+path.start.y);
					//System.out.println("line finish: "+path.finish.x+" , "+path.finish.y);
				} else if (path.stroke.type == Geom.STROKE_DASH) {
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setStroke(dashed);
					g2d.drawLine((int)path.start.x,(int)path.start.y,(int)path.finish.x,(int)path.finish.y);
				}				
			}
			
			if (path.pathType == Path.PATH_TYPE_ARC) {
				Graphics2D g2d = (Graphics2D) g.create();
				if (path.stroke.type == Geom.STROKE_DASH) {
					g2d.setStroke(dashed);
				}
				if (path.fill==null) {
					g2d.draw(new Arc2D.Double(
							path.center.x-path.radHoriz, //left bound
							path.center.y-path.radVert, //top bound
			                path.radHoriz*2, path.radVert*2,// box width and height
			                path.startAngle, path.sweepAngle, 
			                Arc2D.OPEN));
				} else {
					g2d.fillArc(
							(int)(path.center.x-path.radHoriz), //left bound
							(int)(path.center.y-path.radVert), //top bound
			                (int) path.radHoriz*2, 
			                (int) path.radVert*2,// box width and height
			                (int)path.startAngle, 
			                (int)path.sweepAngle);
				}
			}
			if (path.pathType == Path.PATH_TYPE_OVAL) {
				Graphics2D g2d = (Graphics2D) g.create();
				if (path.stroke.type == Geom.STROKE_DASH) {
					g2d.setStroke(dashed);
				}
				if (path.fill==null) {
					g2d.draw(new Arc2D.Double(
							path.center.x-path.radHoriz, //left bound
							path.center.y-path.radVert, //top bound
			                path.radHoriz*2, path.radVert*2,// box width and height
			                0, 360, 
			                Arc2D.OPEN));
				} else {
					g2d.fillArc(
							(int)(path.center.x-path.radHoriz), //left bound
							(int)(path.center.y-path.radVert), //top bound
			                (int) path.radHoriz*2, 
			                (int) path.radVert*2,// box width and height
			                0, 360);
				}
			}
			if (path.pathType == Path.PATH_TYPE_TEXT) {
				System.out.println("draw text!!!!");
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setColor(Color.CYAN);
				FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
				int hgt = metrics.getHeight();
				int adv = metrics.stringWidth(path.text);
				Point2D ps = path.start.move(-adv/2, -hgt/2);
				g2d.drawString(path.text, (int)ps.x, (int)ps.y);
			}
		}
		mg.invalidate();
		
	}
	private void dodol() {
		Graphics g = mg.getGraphics();
		g.clearRect(0, 0, mg.getWidth(), mg.getHeight());
		g.drawLine(50, 50, 150, 50);
		Graphics2D g2d = (Graphics2D) g.create();
		Double arc = new Arc2D.Double(Arc2D.PIE);
		arc.setFrame(50,15,100,70);
		arc.setAngleStart(0);
		arc.setAngleExtent(30);
		//System.out.println("lewatttt........");
		g2d.draw(arc);
		arc.setAngleStart(0);
		arc.setAngleExtent(-30);
		g2d.draw(arc);
		contentPane.invalidate();
	}
	private void drawFractionOnPie() {
		FractionRepresentationInAPie fPie = new FractionRepresentationInAPie(new Fraction(3, 4));
		drawShape(fPie.getPaths());
	}
}
