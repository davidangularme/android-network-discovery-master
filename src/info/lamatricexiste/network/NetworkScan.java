package info.lamatricexiste.network;

import java.io.IOException;  
import java.net.InetAddress;  
import java.net.NetworkInterface;  
import java.net.SocketException;  
import java.net.UnknownHostException;  
import java.util.Collections;  
import java.util.Enumeration;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  

import android.util.Log;
  
public class NetworkScan implements Runnable  
{  
  
 static Map devices=null;  
 String flag="500";  
 // search for local IP that is not 127.0.0.1 (other methods are not reliable)  
  public String[] getLocalAddress()   
  {  
   Enumeration<NetworkInterface> nets;  
   try { nets = NetworkInterface.getNetworkInterfaces();  
   } catch (SocketException e) {return null;}  
         for (NetworkInterface netint : Collections.list(nets)) {  
             Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();  
             for (InetAddress inetAddress : Collections.list(inetAddresses)) {  
                 if (!inetAddress.isLoopbackAddress()   
                   && inetAddress.getHostAddress().matches("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"))   
                 {  
                	 
                	 Log.d("davidfff", "davidfff IP Address:"+inetAddress.getHostAddress());  
                 	 Log.d("davidfff", "davidfff 11 IP Address:"+inetAddress.getCanonicalHostName());  
                 	 Log.d("davidfff", "davidfff 22 IP Address:"+inetAddress.getAddress());  
                 	                  return inetAddress.getHostAddress().split("\\.");  
                 }  
             }  
         }  
         return null;  
  }  
  public String Update()  
  {  
	  Log.d("davidfff", "davidfff nnnnnn ############################################ update########################################################################");  
   String devicesData="";  
   if(devices!=null&&!devices.isEmpty())  
   {  
   Set s = devices.entrySet();                  
   Iterator i = s.iterator();  
     
   while (i.hasNext())   
   {                        Log.d("davidfff", "davidfff Devices"+i.next());  
      
    //System.out.println(i.next());  
    String[] tmp=i.next().toString().split("=");  
      
    devicesData+=","+tmp[0]+":"+tmp[1];  
    /*try { 
     if (InetAddress.getByName(tmp[0]).isReachable(1000))  
     { 
      devicesData+=tmp[0]+":"+tmp[1]+":Active,"; 
     } 
     else 
     { 
      devicesData+=tmp[0]+":"+tmp[1]+":InActive,"; 
     } 
    } catch (UnknownHostException e) { 
     // TODO Auto-generated catch block 
     e.printStackTrace(); 
    } catch (IOException e) { 
     // TODO Auto-generated catch block 
     e.printStackTrace(); 
    } 
    */  
      
      
   }  
   }  
   Log.d("davidfff", "davidfff ################################################################################################################"+devicesData);  
   return devicesData;  
  }  
  public void run()  
  {  
	  Log.d("davidfff", "davidfff Running..."+flag);  
   if(devices==null)  
   {  
    devices=new HashMap();  
    //System.out.println("Map Created...");  
   }  
   String startIP, stopIP;  
   String[] local = getLocalAddress();  
     
   collectDeviceDetailsThreaad(local, "1", "7");  
     
   Log.d("davidfff", "davidfff "+flag+" milli sec-->"+System.currentTimeMillis()); 
   collectDeviceDetailsThreaad(local, "1", "7");  
   collectDeviceDetailsThreaad(local, "8", "15");  
   collectDeviceDetailsThreaad(local, "16", "22");  
   collectDeviceDetailsThreaad(local, "23", "29");  

 /*  switch (Integer.parseInt(flag))  
   {  
     
   case 1:  
    collectDeviceDetailsThreaad(local, "1", "7");  
    break;  
   case 2:  
    collectDeviceDetailsThreaad(local, "8", "15");  
    break;  
   case 3:  
    collectDeviceDetailsThreaad(local, "16", "22");  
    break;  
   case 4:  
    collectDeviceDetailsThreaad(local, "23", "29");  
    break;  
   case 5:  
    collectDeviceDetailsThreaad(local, "30", "36");  
    break;  
   case 6:  
    collectDeviceDetailsThreaad(local, "37", "43");  
    break;  
   case 7:  
    collectDeviceDetailsThreaad(local, "44", "50");  
    break;  
   case 8:  
    collectDeviceDetailsThreaad(local, "51", "57");  
    break;  
   case 9:   
    collectDeviceDetailsThreaad(local, "58", "64");  
    break;  
   case 10:  
    collectDeviceDetailsThreaad(local, "65", "71");  
    break;  
   case 11:   
    collectDeviceDetailsThreaad(local, "72", "78");  
    break;  
   case 12:  
    collectDeviceDetailsThreaad(local, "79", "85");  
    break;  
   case 13:   
    collectDeviceDetailsThreaad(local, "86", "92");  
    break;  
   case 14:  
   collectDeviceDetailsThreaad(local, "93", "99");  
   break;  
   case 15:  
    collectDeviceDetailsThreaad(local, "100", "106");  
    break;  
   case 16:  
    collectDeviceDetailsThreaad(local, "107", "113");  
    break;  
   case 17:  
    collectDeviceDetailsThreaad(local, "114", "120");  
    break;  
   case 18:  
    collectDeviceDetailsThreaad(local, "121", "127");  
    break;  
   case 19 :  
    collectDeviceDetailsThreaad(local, "128", "134");  
    break;  
   case 20:  
    collectDeviceDetailsThreaad(local, "135", "141");  
    break;  
   case 21:  
    collectDeviceDetailsThreaad(local, "142", "148");  
    break;  
   case 22:  
    collectDeviceDetailsThreaad(local, "149", "155");  
    break;  
   case 23:  
    collectDeviceDetailsThreaad(local, "156", "162");  
    break;  
   case 24:  
    collectDeviceDetailsThreaad(local, "163", "169");  
    break;  
   case 25:  
    collectDeviceDetailsThreaad(local, "170", "176");  
    break;  
   case 26:  
    collectDeviceDetailsThreaad(local, "177", "183");  
    break;  
   case 27:  
    collectDeviceDetailsThreaad(local, "184", "190");  
    break;  
   case 28:  
    collectDeviceDetailsThreaad(local, "191", "197");  
    break;  
   case 29:  
    collectDeviceDetailsThreaad(local, "198", "204");  
    break;  
   case 30:  
    collectDeviceDetailsThreaad(local, "205", "211");  
    break;  
   case 31:  
    collectDeviceDetailsThreaad(local, "212", "218");  
    break;  
   case 32:  
    collectDeviceDetailsThreaad(local, "219", "225");  
    break;  
   case 33:  
    collectDeviceDetailsThreaad(local, "226", "232");  
    break;  
   case 34:  
    collectDeviceDetailsThreaad(local, "233", "239");  
    break;  
   case 35:  
    collectDeviceDetailsThreaad(local, "240", "247");  
    break;  
   case 36:  
    collectDeviceDetailsThreaad(local, "248", "255");  
    break;  
      
      
      
   }  */
     
     
      
     
  }  
    
    
  public void collectDeviceDetailsThreaad(String[] local,String startingIP,String endingIP)  
  {  
     
   String startIP, stopIP;  
     
  
   //startIP="192.168.1.1";  
   startIP = local[0]+"."+local[1]+"."+local[2]+"."+startingIP; 
   Log.d("davidfff", "davidfff startIP "+startIP);
   stopIP = local[0]+"."+local[1]+"."+local[2]+"."+endingIP; 
   Log.d("davidfff", "davidfff stopIP "+stopIP);
   String[] from = startIP.split("\\.");  
   Log.d("davidfff", "davidfff from "+from);
   String[] to = stopIP.split("\\."); 
   Log.d("davidfff", "davidfff to "+to);
   int[] start = new int[4], stop = new int[4]; 
   Log.d("davidfff", "davidfff start "+start);
   for (int i = 0; i < 4; i++)   
   {  
    start[i] = Integer.parseInt(from[i]);  
    Log.d("davidfff", "davidfff to start[i] "+start[i]);
    stop[i] = Integer.parseInt(to[i]);  
    Log.d("davidfff", "davidfff to stop[i] "+stop[i]);
   }  
   for (int a = start[0]; a <= stop[0]; a++) {  
    for (int b = start[1]; b <= stop[1]; b++) {  
     for (int c = start[2]; c <= stop[2]; c++) {  
      for (int d = start[3]; d <= stop[3]; d++) {  
       //if (!run) return;  
       try {  
        
    	   Log.d("davidfff", "davidfff to a + b +c+ d "+a+"."+b+"."+c+"."+d);
        if (InetAddress.getByName(a+"."+b+"."+c+"."+d).isReachable( NetworkInterface.getByInetAddress(InetAddress.getByName(a+"."+b+"."+c+"."+d)), 128, 10000)/*.isReachable(10000)*/)   
        {  
           
  
         devices.put(a+"."+b+"."+c+"."+d, InetAddress.getByName(a+"."+b+"."+c+"."+d).getHostName()+":Active");  
         Log.d("davidfff", "davidfff "+a+"."+b+"."+c+"."+d+","+ InetAddress.getByName(a+"."+b+"."+c+"."+d).getHostName()+":Active"); 
           
        }  
        else  
        {  
         if(devices.containsKey(a+"."+b+"."+c+"."+d))  
         {  
            
          devices.put(a+"."+b+"."+c+"."+d, InetAddress.getByName(a+"."+b+"."+c+"."+d).getHostName()+":InActive");  
          Log.d("davidfff", "davidfff "+a+"."+b+"."+c+"."+d+","+ InetAddress.getByName(a+"."+b+"."+c+"."+d).getHostName()+":InActive");  
         }  
        }  
       } catch (IOException e) {System.out.println("exception..");}  
       //progress.setValue(++pNow);  
      }  
     }  
    }  
   }  
     
     
     
     
  }  
    
    
}  
