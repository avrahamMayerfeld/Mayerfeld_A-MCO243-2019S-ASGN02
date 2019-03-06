package classes;

import interfaces.IProcess;
import interfaces.IRandomValueGenerator;
import eNum.ProcessState;
public class SimProcess implements IProcess {

	IRandomValueGenerator rgen;
	int pid;
	String procName;
	int totalInstructions;
	
	public SimProcess(int id, String pName, int instructions, IRandomValueGenerator randG) {
		pid = id;
		procName = pName;
		totalInstructions = instructions;
		rgen = randG;
	}
	
	@Override
	public int getPid() {
		return pid;
	}

	@Override
	public String getProcName() {
		return procName;
	}

	@Override
	public ProcessState execute(int i) 
	{
		ProcessState state = null;
	
		if(i <= totalInstructions)
			System.out.println("pid: "+pid+" name: "+procName+" executing instruction #"+i);
		if(i >= totalInstructions)
			state = ProcessState.FINISHED;
		else
		{
			
			if(rgen.getTrueWithProbability(0.15))
				state = ProcessState.BLOCKED;
			else
				state = ProcessState.READY;
		}
		
		
		return state;
	}

}
