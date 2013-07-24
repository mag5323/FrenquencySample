package com.example.frenquencysample;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.SeekBar;

public class MainActivity extends Activity {
	
	private SoundPool soundPool;
	private int bentley;
	private float i;
	private boolean loaded;
	private SeekBar rateBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM,1);
		bentley = soundPool.load(this, R.raw.bentley_continental_gt_ogg, 1);	
		rateBar = (SeekBar)findViewById(R.id.rateBar);
		
		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
			@Override
			public void onLoadComplete(SoundPool soundPool, int SampleId, int status) {					
				soundPool.play(bentley, 1, 1, 1, 1, 1.0f);
				//soundPool.setLoop(bentley, -1);
				//play (int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)			
			}
		});		
		
		rateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){  			  
			@Override  
			   public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				int temp = 8192 - progress; 					
				float rate = (float)(2 - (Math.log(temp>0 ? temp:1)/Math.log(8192)));						
					soundPool.setRate(bentley, rate);
					Log.d("MainActivity", " rate : " + rate);
			   }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
