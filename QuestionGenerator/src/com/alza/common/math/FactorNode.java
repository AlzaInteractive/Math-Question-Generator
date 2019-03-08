package com.alza.common.math;

public class FactorNode {
	public int value;
	public FactorNode leftNode;
	public FactorNode rightNode;
	
	public FactorNode(int value) {
        this.value = value;
        leftNode = null;
        rightNode = null;
    }

}
