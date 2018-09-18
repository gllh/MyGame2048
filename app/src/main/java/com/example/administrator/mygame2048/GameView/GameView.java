package com.example.administrator.mygame2048.GameView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.administrator.mygame2048.Card.Card;
import com.example.administrator.mygame2048.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class GameView extends GridLayout {
	private Card[][] cardsMap=new Card[4][4];
	private List<Point> emptyPoints=new ArrayList<Point>();
	private  static GameView gv=null;

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initgameview();
		gv=this;
	}

	public GameView(Context context) {
		super(context);
		initgameview();
		gv=this;
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initgameview();
		gv=this;
	}
	public static GameView getGameView() {
		return gv;
	}
	protected void onSizeChanged(int w,int h,int oldw,int oldh){
		super.onSizeChanged(w,h,oldw,oldh);
		int cardWidth=(Math.min(w,h)-10)/4;
		addCards(cardWidth);
		startGame();
	}
	private void addCards(int cardwidth){
		Card card;
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++){
				card=new Card(getContext());
				card.setNum(0);
				addView(card,cardwidth,cardwidth);
				cardsMap[x][y]=card;
			}
		}
	}
	private void addRandomNum(){
		emptyPoints.clear();
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++) {
				if (cardsMap[x][y].getNum()==0){
					emptyPoints.add(new Point(x,y));
				}
			}
		}
		if (emptyPoints.size()>0){
			Point point=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
			int num;
			if (Math.random()>0.1){
				num=2;
			}else {
				num=4;
			}
			cardsMap[point.x][point.y].setNum(num);
		}
	}
	public void startGame(){
		MainActivity.getMainActivity().clearScore();
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++) {
			cardsMap[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}
	private  void initgameview(){
		setColumnCount(4);
		setBackgroundColor(0x77b73ca7);
		setOnTouchListener(new OnTouchListener() {
		private  float startX,startY,offsetX,offsetY;
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()){
				case  MotionEvent.ACTION_DOWN:
					startX=event.getX();
					startY=event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX=event.getX()-startX;
					offsetY=event.getY()-startY;
					if (Math.abs(offsetX)>Math.abs(offsetY)){
						if (offsetX<-5){
							swipeLeft();
						}else if (offsetX>5){
							swipeRight();
						}
					}else {
						if (offsetY<-5){
							swipeUp();
						}else if (offsetY>5){
							swipeDown();
						}
					}
					break;
			}
			return true;
		}
	});
	}
	private void swipeLeft(){
		boolean merge=false;
		for (int y=0;y<4;y++){
			for (int x=0;x<4;x++) {
				for (int x1=x+1;x1<4;x1++) {
					if (cardsMap[x1][y].getNum()>0){
						if (cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x--;
							merge=true;
						}else if(cardsMap[x][y].ifequal(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							merge=true;
							score(cardsMap[x][y].getNum());
						}
						break;
						}

					}
				}
			}if(merge==true){
			addRandomNum();
			gameOver();
		}
	}
	private void swipeRight(){
		boolean merge=false;
		System.out.println("right");
		for(int y=0;y<4;y++){
			for(int x=3;x>=0;x--){
				for(int x1=x-1;x1>=0;x1--){
					if(cardsMap[x1][y].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x++;///
							merge=true;
						}else if(cardsMap[x][y].ifequal(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							merge=true;
							score(cardsMap[x][y].getNum());
						}
						break;
						}

					}
				}
			}if(merge==true){
			addRandomNum();
			gameOver();
		}
	}
	private void swipeUp(){
		boolean merge=false;
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				for(int y1=y+1;y1<4;y1++){
					if(cardsMap[x][y1].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y--;
							merge=true;
						}else if(cardsMap[x][y].ifequal(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							merge=true;
							score(cardsMap[x][y].getNum());
						}break;
					}

					}
				}
			}
		if(merge==true){
			addRandomNum();
			gameOver();
		}
	}
	private void swipeDown(){
		boolean merge=false;
		for(int x=0;x<4;x++){
			for(int y=3;y>=0;y--){
				for(int y1=y-1;y1>=0;y1--){
					if(cardsMap[x][y1].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y++;
							merge=true;
						}else if(cardsMap[x][y].ifequal(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							merge=true;
							score(cardsMap[x][y].getNum());
						}
						break;
						}

					}
				}
			}if(merge==true){
			addRandomNum();
			gameOver();
		}
	}
	private void score(int s){
		MainActivity.getMainActivity().addScore(s);
	}
	private void gameOver() {

		boolean finish = true;

		ALL: for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				// 如果这个位置没有值，或者两两相等，则不结束，否则游戏结束
				if (cardsMap[x][y].getNum() == 0
						|| (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y]))
						|| (x < 3 && cardsMap[x][y].equals(cardsMap[x + 1][y]))
						|| (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1]))
						|| (y < 3 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {

					finish = false;
					break ALL;
				}
			}
		}

		if (finish) {
			new AlertDialog.Builder(getContext())
					.setTitle("游戏结束!")
					.setPositiveButton("不服再来！",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
													int which) {
									startGame();
								}
							})
					.setNegativeButton("quit",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
													int which) {
									MainActivity.getMainActivity().finish();
								}
							}).show();
		}

	}
}
