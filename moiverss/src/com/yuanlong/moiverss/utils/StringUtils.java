package com.yuanlong.moiverss.utils;

public class StringUtils {
  	 public  static String toLength(String str, int length) {
  	    StringBuffer buff = new StringBuffer();
  	    int index = 0;
  	    char c;
  	    length -= 2;
  	    if(getLength(str) < length){
  	        return str ;
  	    }
  	    while (length > 0) {
  	        c = str.charAt(index);
  	        if (c < 128) {
  	            length--;
  	        } else {
  	            length--;
  	            length--;
  	        }
  	        //if(length >=0)   如果严格要求可以少于但是不能超出长度 请删除此注释
  	        buff.append(c);
  	        index++;
  	    }
  	    buff.append("...");
  	    return buff.toString();
  	}

  	private static int getLength(String str) {
  	    int count = 0;
  	    for (int i = 0; i < str.length(); i++) {

  	      //请勿去除 &&!((int)str.charAt(i)<=128) 这个代码 因为在某些时候没有这个代码会报异常

  	        if (String.valueOf(str.charAt(i)).matches("[^x00-xff]*")&&!((int)str.charAt(i)<=128)) {  
  	            count += 2;
  	        } else {
  	            count++;
  	        }
  	    }
  	    return count;
  	}
  	public static void main(String[] args) throws Exception {
		System.out.println(toLength("非常小特务3/特工小子3/非常小特务之3D立体出击[BD",22));
	}
}
