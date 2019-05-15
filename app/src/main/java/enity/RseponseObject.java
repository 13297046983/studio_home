package enity;

import java.nio.channels.ScatteringByteChannel;

public class RseponseObject <T>{
	private String msg;
	private int state=1;
	private T datas;
	
	public RseponseObject(int state,String msg,T datas) {
		this.datas=datas;
		this.state=state;
		this.msg=msg;
	}

	public RseponseObject(int state,T datas) {
		this.datas=datas;
		this.state=state;
	}
	
	public RseponseObject(int state,String msg) {
		this.state=state;
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
}
