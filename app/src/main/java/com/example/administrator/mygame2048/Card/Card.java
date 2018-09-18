package com.example.administrator.mygame2048.Card;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/18.
 */

public class Card extends FrameLayout {
	private int num;
	private TextView label;

	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num=num;
		if (num<=0){
			label.setText("");
			label.setBackgroundColor(0x33f6a6c8);
		}else {
			label.setText(num+"");
			changeCardColor();
		}

	}
	public void changeCardColor() {
		switch (num) {
			case 2:
				label.setBackgroundColor(Color.parseColor("#f6a6c8"));
				break;
			case 4:
				label.setBackgroundColor(Color.parseColor("#bd5e3f"));
				break;
			case 8:
				label.setBackgroundColor(Color.parseColor("#997a33"));
				break;
			case 16:
				label.setBackgroundColor(Color.parseColor("#54b43c"));
				break;
			case 32:
				label.setBackgroundColor(Color.parseColor("#2c6b85"));
				break;
			case 64:
				label.setBackgroundColor(Color.parseColor("#5C7235"));
				break;
			case 128:
				label.setBackgroundColor(Color.parseColor("#826FA3"));
				break;
			case 256:
				label.setBackgroundColor(Color.parseColor("#355659"));
				break;
			case 512:
				label.setBackgroundColor(Color.parseColor("#BB719B"));
				break;
			case 1024:
				label.setBackgroundColor(Color.parseColor("#9B8B53"));
				break;
			case 2048:
				label.setBackgroundColor(Color.parseColor("#196A5D"));
				break;
			default:
				label.setBackgroundColor(Color.parseColor("#8A7760"));
		}
	}
	public Card(Context context){
		super(context);
		label=new TextView(getContext());
		label.setTextSize(30);
		LayoutParams layoutParams=new LayoutParams(-1,-1);
		layoutParams.setMargins(10,10,0,0);
		addView(label,layoutParams);
		label.setBackgroundColor(0x33f6a6c8);
		label.setGravity(Gravity.CENTER);
		setNum(0);
	}
	public boolean ifequal(Card c){
		return this.getNum()==c.getNum();
	}
}
