package project.util;

import project.constant.GlobalConstant;

public class PageUtil {
	
	public static int getOffset(Integer page) {
		return (page - 1) * GlobalConstant.TOTAL_ROW;
	}
	
	public static int getTotalpage(int totalRow) {
		return (int)Math.ceil((float)totalRow/GlobalConstant.TOTAL_ROW);
	}
}
