package graphicEditor;

import javax.swing.*;


import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;
import java.awt.*;


public class Sang {
	
	
	public int vVer;
	public boolean fill;
	public int colors;
	public int stroke;
	
	Sang(){
		this.vVer= 0;
		this.fill= false;
		this.colors = 0;
		this.stroke = 0;
	}
	
	public void setVer(int ver) {
		
		this.vVer = ver;
	}
	public int getVer() {
		return vVer;
	}
	
	public void setFill(boolean Fill) {
		this.fill = Fill;
	}
	public boolean getFill() {
		return fill;
	}
	
	public void setColor(int c) {
		this.colors = c;
	}
	public int getColor() {
		return colors;
	}
	
	public void setStroke(int s) {
		this.stroke = s;
	}
	public int getStroke() {
		return stroke;
	}
	
	
	
	
	
}

