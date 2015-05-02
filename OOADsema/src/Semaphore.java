import java.util.PriorityQueue;


public class Semaphore {
	FairRunTime rt;
	private int counter=1;
	String name;
	Semaphore(FairRunTime rt,String name){
		this.rt=rt;
		this.name=name;
	}
	public int wait( String id){
     	System.out.println(rt.time + ": resource "+name+" is accessed by " +id + "and has value of"+counter);
		/*	The counter of S is positive 
		In this case, the counter is decreased by 1 and the thread resumes its execution.*/
		if (counter>0){
			counter--;
	     	System.out.println(rt.time + ": resource "+name+" is taken by " +id);
			return 10000;
		} else if (counter==0){
			/*	The counter of S is zero 
			In this case, the thread is suspended and put into the private queue of S.*/
			System.out.println(rt.time + ": " +id+ " waits resource "+name);
		}

		return -1;
	}
	public void signal(){
	/*	When Signal is executed by a thread, we also have two possibilities:
		The queue of S has no waiting thread 
		The counter of S is increased by one and the thread resumes its execution.*/
			counter++;

	}

}
