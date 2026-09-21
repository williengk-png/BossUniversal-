package com.boss.universal;
import android.accessibilityservice.AccessibilityService; import android.view.accessibility.AccessibilityEvent; import android.view.accessibility.AccessibilityNodeInfo;
public class AdBlockAccessibilityService extends AccessibilityService {
 private final String[] SKIP = {"Skip ad","Skip Ad","SKIP AD","Skip","Close","Close ad","Dismiss"};
 public void onAccessibilityEvent(AccessibilityEvent e){
  if(getRootInActiveWindow()==null) return; findAndClick(getRootInActiveWindow());
 }
 private void findAndClick(AccessibilityNodeInfo node){
  if(node==null) return; CharSequence txt=node.getText(); if(txt!=null){ for(String s:SKIP){ if(txt.toString().toLowerCase().contains(s.toLowerCase())){ node.performAction(AccessibilityNodeInfo.ACTION_CLICK); return; } } }
  for(int i=0;i<node.getChildCount();i++){ findAndClick(node.getChild(i)); }
 }
 public void onInterrupt(){}
}
