package classes;

import interfaces.IProcess;

public class ProcessControlBlock {
	IProcess process;
	int currInstruction = 0;
	int reg1;
	int reg2;
	int reg3;
	int reg4;
	public ProcessControlBlock(IProcess process) {
		this.process = process;
	}
	public IProcess getProcess() {
		return process;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	public int getReg1() {
		return reg1;
	}
	public void setReg1(int reg1) {
		this.reg1 = reg1;
	}
	public int getReg2() {
		return reg2;
	}
	public void setReg2(int reg2) {
		this.reg2 = reg2;
	}
	public int getReg3() {
		return reg3;
	}
	public void setReg3(int reg3) {
		this.reg3 = reg3;
	}
	public int getReg4() {
		return reg4;
	}
	public void setReg4(int reg4) {
		this.reg4 = reg4;
	}
	
	
	
	
	
	

}
