package com.example.administrator.mygame2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mygame2048.GameView.GameView;

public class MainActivity extends AppCompatActivity {
	private static MainActivity mainActivity=null;
	private Button bt_restart;
	private TextView tvscore;
	private int sumScore;
	private GameView gameView;
	private int s;

	public static MainActivity getMainActivity(){
		return mainActivity;
	}

	public MainActivity(){
		mainActivity=this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvscore=(TextView)findViewById(R.id.tv_score);
		bt_restart=(Button)findViewById(R.id.bt_restart);
		gameView=(GameView)findViewById(R.id.gl_game);
		bt_restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameView.startGame();
			}
		});
	}
	public void clearScore(){
		sumScore=0;
		showScore();
	}
	public void showScore(){
		tvscore.setText(sumScore+"");
	}
	public void addScore(int s){
		sumScore+=s;
		showScore();
	}

}
