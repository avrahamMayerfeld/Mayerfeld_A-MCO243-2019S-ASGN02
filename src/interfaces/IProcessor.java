package interfaces;

import eNum.ProcessState;

public interface IProcessor {
	public IProcess getCurrProcess();
	public void setCurrProcess(IProcess p);
	public int getCurrInstruction();
	public void setCurrInstruction(int i);
	public ProcessState executeNextInstruction();
	public void setRegisterValue(int i, int val);
	public int getRegisterValue(int i);
}
