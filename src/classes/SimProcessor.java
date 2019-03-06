package classes;

import eNum.ProcessState;
import interfaces.IProcess;
import interfaces.IProcessor;
import interfaces.IRandomValueGenerator;

public class SimProcessor implements IProcessor {
    IRandomValueGenerator randGen;
	IProcess currProcess;
	int[] registers = new int[4];
    int currInstruction = 0;
    
	public SimProcessor(IRandomValueGenerator randGen) {
		this.randGen = randGen;
		
	}
	
	@Override
	public IProcess getCurrProcess() {
		return currProcess;
	}

	@Override
	public void setCurrProcess(IProcess p) {
		currProcess = p;
	}

	@Override
	public ProcessState executeNextInstruction() { 
		ProcessState currState = currProcess.execute(currInstruction);
	    currInstruction++;
	    return currState;
	}

	@Override
	public void setRegisterValue(int i, int val) {
		registers[i] = val;
	}

	@Override
	public int getRegisterValue(int i) {
		return randGen.getNextInt();
	}

	@Override
	public int getCurrInstruction() {
		return currInstruction;
	}

	@Override
	public void setCurrInstruction(int i) {
		currInstruction = i;
	}

}
