package com.fung.fungdroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends Activity{

	 
	 private TextView textView;
	 private Handler handler;

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_activity);
		
//test 。。主线程给自己发送Message  begin..
		 Button btn1= (Button) this.findViewById(R.id.handerBtn1);
		 textView=(TextView)this.findViewById(R.id.textView1);
		 
		 btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Looper looper=Looper.getMainLooper(); //主线程的Looper对象
				//这里以主线程的Looper对象创建了handler，
				//所以，这个handler发送的Message会被传递给主线程的MessageQueue。
				handler=new MyHandler(looper);
				handler.removeMessages(0);//??????
				
				
				//构建Message对象
				//第一个参数：是自己指定的message代号，方便在handler选择性地接收
				//第二三个参数没有什么意义
				//第四个参数需要封装的对象

				Message msg=handler.obtainMessage(1, 1, 1, " msg main..");
				handler.sendMessage(msg);
				
			}
			 
			 
		 });
			
//test 。。主线程给自己发送Message ######end
		 
//  其他线程给主线程发送Message   begin
		 
		   Button btn2=(Button) this.findViewById(R.id.handerBtn2);
		   btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 
				new MyThread().start();
			}
			   
		   });

//  其他线程给主线程发送Message  ######end

		   
//3、主线程给其他线程发送Message  begin
		   
		   //启动线程。。 
		   //注意这里 线程已经创建了 handler.. 然后 在handler 的handlMessage 里 又把   handler 变量 赋值为主 线程的handler...
		   new ChildThread().start();
		   
		   Button btn3=(Button)this.findViewById(R.id.handerBtn3);
		   btn3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 
				 Message msg = handler.obtainMessage(1,1,1,"主线程发送的消息");
				 handler.sendMessage(msg);
			}
			   
		   });
//3、主线程给其他线程发送Message   ######end  

//4、其他线程给自己发送Message 
		   
		   Button btn4=(Button)this.findViewById(R.id.handerBtn4);
		   btn4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 
				 new ChildThread2().start();
			}
			   
		   });

//4、其他线程给自己发送Message   ######end  
	}
	
//########### inner classes...
	class MyHandler extends Handler{

		public MyHandler(Looper looper)
		{
			super(looper);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			textView.setText("main handler get msg:"+(String)msg.obj);
		}
		 
	 }
	
	class MyThread extends Thread{

		@Override
		public void run() {
			 Looper looper=Looper.getMainLooper();
			 handler=new MyHandler(looper);//主线程的Looper对象
				//这里以主线程的Looper对象创建了handler，
				//所以，这个handler发送的Message会被传递给主线程的MessageQueue。
			 
			 Message msg=handler.obtainMessage(1, 1, 1, " msg from other thread..");
				handler.sendMessage(msg);
		}
		
	}
	
	class ChildThread extends Thread{

		@Override
		public void run() {
			 Looper.prepare();//创建该线程的Looper对象，用于接收消息
			 handler=new ThreadHandler(Looper.myLooper());
			 
			 Looper.loop();
		}
		
	}
	
	class ChildThread2 extends Thread{

		@Override
		public void run() {
			 Looper.prepare();//创建该线程的Looper对象，用于接收消息
			 handler=new ThreadHandler(Looper.myLooper());
			 Message msg=handler.obtainMessage(1,1,1,"我自己发给我自己的。。");
			 handler.sendMessage(msg);
			 Looper.loop();
		}
		
	}
	
	
	class ThreadHandler extends Handler{
		public ThreadHandler(Looper looper)
		{
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			//这里对该线程中的MessageQueue中的Message进行处理
			//这里我们再返回给主线程一个消息
			handler = new MyHandler(Looper.getMainLooper());
			 Message msg2 = handler.obtainMessage(1,1,1,"子线程收到:"+(String)msg.obj);
			 handler.sendMessage(msg2);
		}
		 
	}

}
