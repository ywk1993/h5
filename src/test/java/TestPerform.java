import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestPerform {
	public static void main(String[] args) {
		ConcurrentSkipListSet<Integer> v = new ConcurrentSkipListSet<>();
		for (int i = 1; i <= 10000000 ; i++) {
			v.add(i);
		}
		Iterator<Integer> iterator = v.iterator();
		try {
			for (int j = 1; j <= 10; j ++) {
				new Thread(new LoginThread(iterator)).start();
				System.out.println("j=" + j);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class LoginThread implements Runnable{
	static Iterator<Integer> iterator;
	/**
	 * @param it
	 */
	public LoginThread(Iterator<Integer> iterator) {
		super();
		LoginThread.iterator = iterator;
	}

	@Override
	public void run() {
				while (iterator.hasNext()) {
					URL url;
					try {
						Integer next = null;
						synchronized (iterator){
							next = iterator.next();
							iterator.remove();
							System.out.println(Thread.currentThread().getName() + "\t" + next);
						}
						url = new URL("http://localhost:8083/SmallTownGame/baseUrl/login");
						URLConnection conn= url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
						String param = "";
						
						param = "{\n\"userID\":\"性能测试" + (Integer)next + "\",\n\"password\":\"123456\"}";
						JSONObject parseObject = JSON.parseObject(param);
						bw.write(parseObject.toJSONString());
						bw.flush();
						InputStream inputStream = conn.getInputStream();
						inputStream.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
}
