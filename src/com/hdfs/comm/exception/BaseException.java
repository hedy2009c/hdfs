package com.hdfs.comm.exception;



import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception{

	private static final long serialVersionUID = -6906234848604712975L;
	
	public static final int SQL_EXCEPTION=-1;
	
	public BaseException(){
		super();
	}

	public BaseException(String msg){
		super(msg);
	}
	public BaseException(String msg,Throwable cause){
		super(msg,cause);
	}
	
	public BaseException(Throwable cause){
		super(cause);
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
        synchronized (s) {
            s.println(this);
            StackTraceElement[] trace = getStackTrace();
            if(trace!=null && trace.length>0) s.println("\tat " + trace[0]);
        }
    }
 
    @Override
	public void printStackTrace(PrintWriter s) { 
        synchronized (s) {
            s.println(this);
            StackTraceElement[] trace = getStackTrace();
            if(trace!=null && trace.length>0) s.println("\tat " + trace[0]);
        }
    }

}
