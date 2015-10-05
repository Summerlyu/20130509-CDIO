/**
 * 
 */
package edu.fjnu.hotelsys.utils;

/**
 * @author ctd
 *
 */
public class PageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Page page=new Page();
		
		page.setTotalRecNum(102);
		page.setPageSize(10);
		
		System.out.println(page.getTotalPageNum());

		page.setPageNo(3);
		
		System.out.println(page.getStartIndex()+"-"+page.getEndIndex());
		
		System.out.println(page.getNextPage());
		System.out.println(page.getPrePage());
		
	}

}
