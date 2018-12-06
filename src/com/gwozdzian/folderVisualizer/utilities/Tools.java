/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.utilities;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class Tools {
    
    public static final String newLine = "\n";
    
    
	
	public static String getMethodName()
	{
		return getMethodName(3);
	}
	
	public static String getMethodName(final int depth)
	{
            
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                if(depth<ste.length){
                    return ste[depth].getMethodName();
                }else{
                    return "There is no method for this level";
                }
	}
                
	
	public static void traceMethodName(Object obj) {
		System.out.println(getMethodName()+"() -> "+obj.toString());
	}
	
	
	
	public static String bytesToStringName(long longLength) {
		double length = (double)longLength;//.doubleValue();

		DecimalFormat df = new DecimalFormat("###.#");
		//StringBuilder stringBuilder = new StringBuilder("");
		if(length<800) {
			return length+" b";
			//stringBuilder.append(500);
		}else if(length<800000) {

			return df.format(length/1000) + " kb";
		}else if(length<800000000) {
			return df.format(length/1000000) + " mb";
		}else {
			return df.format(length/1000000000) + " gb";
		}
	}

    public static void trace(String text) {
        System.out.println(text);
    }
    
    public static void trace(Object obj, String text) {
        
        if(obj!=null){
            System.out.println(obj.getClass().getSimpleName()+"."+getMethodName(3)+"() -> "+text);

        }else{
            System.out.println(getMethodName(3)+"()"+text);
        }
        
    }
    
    
    public static String getAllStockTraces(){
        
            Map<Thread, StackTraceElement[]> allStockMap = Thread.getAllStackTraces();
            Object[] keys = allStockMap.keySet().toArray(); //Set<Thread> 
            Object[] values = allStockMap.values().toArray(); //Collection<StackTraceElement[]>
            
            StringBuilder sb = new StringBuilder("");
            
            for(int i=0; i<keys.length; i++){
                sb.append(((Thread)keys[i]).getName()+newLine);
                for(StackTraceElement currMethN: ((Thread)keys[i]).getStackTrace()){
                    sb.append("   "+currMethN.getLineNumber()+"] "+currMethN.getClassName()+", "+currMethN.getFileName()+", "+currMethN.getMethodName()+newLine);
                }
                
                //sb.append(((Thread)keys[i]).getStackTrace().toString());
                
            }
            
        return sb.toString();
    }

    
    
    
    public static String listMethodsResult(Object ... methodsResult){
        StringBuilder sb = new StringBuilder("");
        for(Object currMethodResult :methodsResult){
            sb.append("[0]"+getMethodName(0)+" , ");
            sb.append("[1]"+getMethodName(1)+" , ");
            sb.append("[2]"+getMethodName(2)+" , ");
            sb.append("[3]"+getMethodName(3)+" , ");
            sb.append("[4]"+getMethodName(4)+" , ");
            sb.append("[5]"+getMethodName(5)+" "+newLine);
            sb.append(Tools.getAllStockTraces());
            sb.append("() -> "+currMethodResult+newLine+newLine);
        }
        return sb.toString();
    }
    
}
