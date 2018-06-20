package com.muzhi.util;

import org.apache.log4j.Logger;

public class LogStrBuffer {
	 private int logLevel;
	 private StringBuffer strBuffer;
	 private boolean bStartLogging;
	 private String line_separator;
	 
	 //LOG级别
	 public static int logLevelDebug = 3;
	 public static int logLevelInfor = 2;
	 public static int logLevelWarn = 1;
	 
	 //默认LOG级别
	 public static  int logLevelDefault = logLevelInfor;
	 
	 //LOG类型
	 public static int LogTypeWeb = 0;
	 public static int LogTypeFile = 1;
	 public  int logType;
	 
	 private static Logger logger = Logger.getLogger(LogStrBuffer.class);
	 
	 public static ThreadLocal<LogStrBuffer> LogStrMap = new ThreadLocal<LogStrBuffer>(){
	        @Override
	        protected LogStrBuffer initialValue() {
	            return new LogStrBuffer();
	        }
	 }; 
	    
     public LogStrBuffer()
     {
    	 logType = LogTypeFile;
    	 bStartLogging = false;
    	 strBuffer = new StringBuffer();
    	 if(logType == LogTypeFile)
    	 {
    		 line_separator = System.getProperty("line.separator");
    	 }
     }
     
     public void setLogType(int logType)
     {
    	 this.logType = logType;
    	 if(this.logType == LogTypeFile)
    	 {
    		 line_separator = System.getProperty("line.separator");
    	 }
    	 else  if(this.logType == LogTypeWeb)
    	 {
    		 line_separator = "<br/>";
    	 }
     }
    /* public void addLog(String str)
     {
    	 this.strBuffer.append(str+System.getProperty("line.separator"));
     }*/
     
     public String getString()
     {
    	return this.strBuffer.toString();
     }
     public void clearBuf()
     {
    	 strBuffer = new StringBuffer();
     }
	 public static void addDebugLog(String str)
     {
		 LogStrBuffer logStr = LogStrBuffer.getThreadLocal();
		 if((logStr.logLevel >= LogStrBuffer.logLevelDebug) && (logStr.bStartLogging))
		 {
			 logStr.strBuffer.append(str+logStr.line_separator);
		 }
     }
	 public static void addInforLog(String str)
     {
		 LogStrBuffer logStr = LogStrBuffer.getThreadLocal();
		 if((logStr.logLevel >= LogStrBuffer.logLevelInfor) && (logStr.bStartLogging))
		 {
			 logStr.strBuffer.append(str+logStr.line_separator);
		 }
     }
	 public static void addWarnLog(String str)
     {
		 LogStrBuffer logStr = LogStrBuffer.getThreadLocal();
		 if((logStr.logLevel >= LogStrBuffer.logLevelWarn) && (logStr.bStartLogging))
		 {
			 logStr.strBuffer.append("warn="+str+logStr.line_separator);
		 }
     }
	 
	 public static String flushLog()
     {
		 String str = "";
		 LogStrBuffer logStr = LogStrBuffer.getThreadLocal();
		 logStr.bStartLogging = false;
		 str = logStr.getString();
		 if(logStr.logType == LogStrBuffer.LogTypeFile)
		 {
	    	 logger.info(str);
		 }
		 System.out.print(str);
 		 return str;
     }
	  
	 
	 public static LogStrBuffer startLogging(int logLevel,int logType)
	 {
		 LogStrBuffer logStrBuffer = LogStrMap.get();
		 logStrBuffer.clearBuf();
		 logStrBuffer.logLevel = logLevel;
		 logStrBuffer.bStartLogging = true;
		 logStrBuffer.setLogType(logType);
		 return logStrBuffer;
	 }
	 
	 public static LogStrBuffer getThreadLocal()
	 {
		 LogStrBuffer logStrBuffer = LogStrMap.get();
		 /*if(ConstValue.DEBUG == 1)
		 {
			 logStrBuffer.logLevel = logLevelDebug;
		 }
		 else
		 {
			 logStrBuffer.logLevel = logLevelDebug;//logLevelInfor;
		 }*/
		 return logStrBuffer;
	 }
	 
     
 
     
}

