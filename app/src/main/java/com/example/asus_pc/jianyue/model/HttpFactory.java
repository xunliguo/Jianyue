	package com.example.asus_pc.jianyue.model;

    /**
 * 爱生活，爱代码
 * 创建于：2017/8/16 16:14
 * 作 者：T
 * 微信：704003376
 */
public class HttpFactory {
    private static final int OKHTTP = 0;
    private static final int RETROFIT = 1;
    private static final int VOLLEY = 2;
    public static final int HTTP_TYPE = 0;
	private int myHttpType;
  public void setHttpType(int httpType){
	  
	 // this.httpType=httpType;
  }
    public static IHttp getHttpClient() {
        switch (HTTP_TYPE) {
            case OKHTTP:
                return HttpUtils.getHttpUtilsInstance();
            case RETROFIT:
                break;
            case VOLLEY:
                break;
        }
        return null;
    }
	
private int getHttpType(){
	
	return myHttpType;
	
	
}

}
