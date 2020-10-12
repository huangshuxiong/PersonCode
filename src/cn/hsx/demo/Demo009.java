package cn.hsx.demo;

public class Demo009 extends Thread {

	private static Integer ticket = 100;
	static Object lock = new Object();

	@Override
	public void run() {

		while (true) {
			synchronized (lock) {
				if (ticket > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "...." + ticket--);
				} else {

					break;
				}
			}
		}

	}

	public static void main(String[] args) {
		Demo009 d1 = new Demo009();
		Demo009 d2 = new Demo009();
		Demo009 d3 = new Demo009();
		d1.start();
		d2.start();
		d3.start();
	}
}
