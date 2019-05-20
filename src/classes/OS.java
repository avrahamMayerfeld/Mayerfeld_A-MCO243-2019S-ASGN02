package classes;

import java.util.ArrayList;
import java.util.LinkedList;


import eNum.ProcessState;
import interfaces.IProcess;
import interfaces.IProcessor;
import interfaces.IRandomValueGenerator;

public class OS {
	static IRandomValueGenerator rg = new RandomValueGenerator();
	static IProcessor processor = new SimProcessor(rg);
	static IProcess p0 = new SimProcess(0, "p0", 100, rg);
	static IProcess p1 = new SimProcess(1, "p1", 150, rg);
	static IProcess p2 = new SimProcess(2, "p2", 160, rg);
	static IProcess p3 = new SimProcess(3, "p3", 180, rg);
	static IProcess p4 = new SimProcess(4, "p4", 200, rg);
	static IProcess p5 = new SimProcess(5, "p5", 300, rg);
	static IProcess p6 = new SimProcess(6, "p6", 400, rg);
	static IProcess p7 = new SimProcess(7, "p7", 367, rg);
	static IProcess p8 = new SimProcess(8, "p8", 263, rg);
	static IProcess p9 = new SimProcess(9, "p9", 250, rg);
	static ProcessControlBlock pcb0 = new ProcessControlBlock(p0);
	static ProcessControlBlock pcb1 = new ProcessControlBlock(p1);
	static ProcessControlBlock pcb2 = new ProcessControlBlock(p2);
	static ProcessControlBlock pcb3 = new ProcessControlBlock(p3);
	static ProcessControlBlock pcb4 = new ProcessControlBlock(p4);
	static ProcessControlBlock pcb5 = new ProcessControlBlock(p5);
	static ProcessControlBlock pcb6 = new ProcessControlBlock(p6);
	static ProcessControlBlock pcb7 = new ProcessControlBlock(p7);
	static ProcessControlBlock pcb8 = new ProcessControlBlock(p8);
	static ProcessControlBlock pcb9 = new ProcessControlBlock(p9);
	static final int QUANTUM = 5;
	static LinkedList<ProcessControlBlock> ready = new LinkedList<ProcessControlBlock>();
	static LinkedList<ProcessControlBlock> blocked = new LinkedList<ProcessControlBlock>();
	static ProcessControlBlock currPCB;
	static int quantumCounter = 0;
	
	public static void main(String[]args) {
		ready.add(pcb0);
		ready.add(pcb1);
		ready.add(pcb2);
		ready.add(pcb3);
		ready.add(pcb4);
		ready.add(pcb5);
		ready.add(pcb6);
		ready.add(pcb7);
		ready.add(pcb8);
		ready.add(pcb9);
		
		
		//start off partially manually, avoid several issues 
		currPCB = ready.poll();
		processor.setCurrProcess(currPCB.getProcess());
		processor.setCurrInstruction(currPCB.getCurrInstruction());//this is initially set to 0 anyway, and i'm not sure if its a good idea to put 0 here, although if it is it would be cleaner.
		
		for(int i = 1; i <= 3000; i++)
		{
			ProcessState state = processor.executeNextInstruction();
			quantumCounter++;
			//There's a slight repetition with the next 3, but i think it's cleaner than to have an additional if and repeat the states.
			if(state.equals(ProcessState.FINISHED)) {
				System.out.print("process finished");
				doSwitch(currPCB);
			
			}
			else if(state.equals(ProcessState.BLOCKED)) {
				System.out.print("process blocked");
				blocked.add(currPCB);
				doSwitch(currPCB);
				
			}
			else if(quantumCounter >= QUANTUM) {
				System.out.print("quantum reached");
				ready.add(currPCB);
				doSwitch(currPCB);
				
			}
			
		}
		
	}
	
	public static void doSwitch(ProcessControlBlock pcb) {
		
		quantumCounter = 0;
		
		if(ready.isEmpty()) 
		{
			System.out.println("		The processor is idling...");
			if(!blocked.isEmpty())
				unblockWithProbability();
		}
		else 
		{
			pcb.setReg1(processor.getRegisterValue(0));
			pcb.setReg2(processor.getRegisterValue(1)); 
			pcb.setReg3(processor.getRegisterValue(2));
			pcb.setReg4(processor.getRegisterValue(3));
			pcb.setCurrInstruction(processor.getCurrInstruction());
			currPCB = ready.poll();
			System.out.println("		performing context switch");
			processor.setCurrProcess(currPCB.getProcess());
			processor.setCurrInstruction(currPCB.getCurrInstruction());
			processor.setRegisterValue(0, currPCB.getReg1());
			processor.setRegisterValue(1, currPCB.getReg2());
			processor.setRegisterValue(2, currPCB.getReg3());
			processor.setRegisterValue(3, currPCB.getReg4());
		}
	}
		
	public static void unblockWithProbability() 
	{   		
		ArrayList<ProcessControlBlock> unblocked = new ArrayList<ProcessControlBlock>();
		
		for(ProcessControlBlock pcblock : blocked)
		{
			if(rg.getTrueWithProbability(0.30)) 
				unblocked.add(pcblock);
			
		}
		for (ProcessControlBlock un : unblocked) {
			ready.add(un);
			blocked.remove(un);
		}
	}

}
