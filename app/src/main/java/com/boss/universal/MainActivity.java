package com.boss.universal;
import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
public class MainActivity extends Activity {
 protected void onCreate(Bundle b){
  super.onCreate(b); setContentView(R.layout.activity_main);
  Button vpn=findViewById(R.id.btnVpn); Button acc=findViewById(R.id.btnAcc);
  vpn.setOnClickListener(v->{ Intent i=VpnService.prepare(this); if(i!=null) startActivityForResult(i,0); else startService(new Intent(this,BlockVpnService.class)); });
  acc.setOnClickListener(v-> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
 }
}
