package controller;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyController implements Controller {

	private BlockingQueue<Commands> queue;
	private boolean isStopped = false;

	public MyController() {
		queue = new ArrayBlockingQueue<Commands>(10);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
	Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isStopped) {
					try {
						Commands cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null)
							cmd.execute();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();

	}

	@Override
	public void stop() {
		isStopped = true;
	}

	@Override
	public void insertCommand(Command command) {
		try {
			queue.put(command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
