package cn.edu.zufe.drawable;

import java.text.DecimalFormat;
import java.util.LinkedList;

import processing.core.*;
import cn.edu.zufe.model.*;

public class PMapWell {

	private DWell well;
	private float px = -1, py = -1; // ���Ƶ�����λ��
	private float pw = 30, ph = 30; // Ĭ�ϵĿ��͸ߣ���Ҫ�������ã�
	public final static float OFFSET_X = 100, OFFSET_Y = 100, ZOOM_OUT = 200; // ƫ���Լ��Ŵ����
	private boolean clicked = false;

	/**
	 * ���캯��
	 * 
	 * @param well
	 *            �;�
	 * @param norX
	 *            ��һ�����X����ֵ
	 * @param norY
	 *            ��һ�����Y����ֵ
	 */
	public PMapWell(DWell well, float px, float py) {
		this.well = well;
		this.px = px;
		this.py = py;
	}

	public DWell getWell() {
		return well;
	}

	/**
	 * ����
	 * 
	 * @param pg
	 *            ����ͼ
	 * @param iconOrigin
	 *            ԭͼ��
	 * @param iconClicked
	 *            ������ͼ��
	 */
	public void draw(PGraphics pg, PShape iconOrigin, PShape iconClicked) {

		if (px != -1 && py != -1) {
			PShape shape;
			if (clicked) {
				pg.fill(0); // ���ʱ����ɫ
				shape = iconClicked;
				// drawInfo(pg, 10, 10); // ���ʱ��ʾ��Ϣ
			} else {
				pg.fill(100); // �����ʱ����ɫ
				shape = iconOrigin;
			}

			if (shape != null) {
				pg.shapeMode(PApplet.CENTER);
				pg.shape(shape, px, py, pw, ph);
			} else {
				pg.ellipseMode(PApplet.CENTER);
				pg.ellipse(px, py, pw, ph);
			}
		}

	}
	
	/***
	 * ������С������·�� 
	 * 
	 * @param pg
	 * 		����ͼ
	 * @param pw
	 * 		��ʼ�;�
	 * @param pwList
	 * 		�����;�
	 */
	public void drawMSTPath(PGraphics pg, PMapWell pw, LinkedList<PMapWell> pwList){
		
		boolean[][] mp = new boolean[pwList.size()][pwList.size()];
		
		for(int i=0; i<pwList.size(); ++i){
			for(int j=0; j<pwList.size(); ++j){
				if(mp[i][j]){
					
				}
			}
		}
	}
	
	/**
	 * ����
	 * 
	 * @param pg
	 *            ����ͼ
	 * @param iconOrigin
	 *            ԭͼ��
	 * @param iconClicked
	 *            ������ͼ��
	 */
	public void highlight(PGraphics pg, PShape iconOrigin, PShape iconClicked) {

		float ratio = 1.5f; // ͻ����ʾ��ϵ��
		PShape shape;
		if (clicked) {
			pg.fill(0); // ���ʱ����ɫ
			shape = iconClicked;
		} else {
			pg.fill(100); // �����ʱ����ɫ
			shape = iconOrigin;
		}

		if (px != -1 && py != -1) {
			if (shape != null) {
				pg.fill(255);
				pg.noStroke();
				pg.rectMode(PApplet.CENTER);
				pg.rect(px, py, pw, ph);
				pg.shapeMode(PApplet.CENTER);
				pg.shape(shape, px, py, pw * ratio, ph * ratio);
			} else {
				pg.ellipseMode(PApplet.CENTER);
				pg.ellipse(px, py, pw * ratio, ph * ratio);
			}
		}
		// ����ʱ��ʾ��Ϣ
		drawInfo(pg, 10, 10);
	}

	/**
	 * ��ʾ�����Ϣ
	 * 
	 * @param pg
	 * @param x
	 * @param y
	 */
	private void drawInfo(PGraphics pg, float x, float y) {
		int txtSize = 12; // ���������С
		// �ڵ���
		pg.fill(255);
		pg.stroke(255);
		pg.rectMode(PApplet.CORNER);
		pg.rect(x, y, 110, txtSize * 3);
		// ��������
		pg.fill(0);
		pg.textSize(txtSize);
		pg.text("Name: " + well.getName(), x, y + txtSize);
		// ʹ��DecimalFormat��ʽ��double���ͣ���ֹ�Կ�ѧ��������ʾ
		DecimalFormat df = new DecimalFormat("0.00");
		pg.text("X:" + df.format(well.getX()), x, y + txtSize * 2);
		pg.text("Y:" + df.format(well.getY()), x, y + txtSize * 3);
	}

	/**
	 * �Ƿ��������ײ�����μ�⣩
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return
	 */
	public boolean collisionDetection(int mouseX, int mouseY) {
		if (mouseX > px - pw / 2 && mouseX < px + pw / 2) {
			if (mouseY > py - ph / 2 && mouseY < py + ph / 2) {
				return true;
			}
		}
		return false;
	}

	public void setClicked(boolean b) {
		this.clicked = b;
	}

	public void setClicked() {
		this.clicked = !clicked;
	}
	
	public double getPX(){
		return this.px;
	}
	
	public double getPY(){
		return this.py;
	}
}