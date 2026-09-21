package com.boss.universal;
import android.content.Intent; import android.net.VpnService; import android.os.ParcelFileDescriptor;
import java.io.*; import java.nio.ByteBuffer; import java.nio.channels.*; import java.util.*;
public class BlockVpnService extends VpnService implements Runnable {
 private ParcelFileDescriptor tun; private Thread thread;
 public void onCreate(){ super.onCreate(); }
 public int onStartCommand(Intent i,int f,int id){
  if(thread!=null) return START_STICKY;
  Builder b=new Builder().addAddress("10.0.0.2",32).addRoute("0.0.0.0",0).addDnsServer("94.140.14.14").setSession("Boss Universal");
  try{ tun=b.establish(); thread=new Thread(this); thread.start(); }catch(Exception e){}
  return START_STICKY;
 }
 public void run(){
  try{
   FileChannel in= new FileInputStream(tun.getFileDescriptor()).getChannel();
   FileChannel out=new FileOutputStream(tun.getFileDescriptor()).getChannel();
   ByteBuffer p=ByteBuffer.allocate(2048);
   while(!Thread.interrupted()){
    int l=in.read(p);
    if(l>0){ out.write((ByteBuffer)p.flip()); p.clear(); }
   }
  }catch(Exception e){}
 }
 public void onDestroy(){ try{ if(tun!=null) tun.close(); }catch(Exception e){} super.onDestroy(); }
}
