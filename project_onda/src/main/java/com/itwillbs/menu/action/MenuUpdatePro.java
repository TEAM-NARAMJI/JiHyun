package com.itwillbs.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.menu.db.menuDAO;
import com.itwillbs.menu.db.menuDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



public class MenuUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MenuUpdatePro 실행");
		String uploadPath = request.getRealPath("/upload");
		System.out.println(uploadPath);

		int maxSize= 10*1024*1024;
		
		MultipartRequest multi
		= new MultipartRequest(request, uploadPath, maxSize ,"utf-8", new DefaultFileRenamePolicy());

		
		int menu_num=Integer.parseInt(multi.getParameter("menu_num"));
		int menu_price=Integer.parseInt(multi.getParameter("menu_price"));
		String menu_name=multi.getParameter("menu_name");
		String menu_detail=multi.getParameter("menu_detail");
		String menu_category=multi.getParameter("menu_category");
		String menu_img = multi.getFilesystemName("menu_img");
		// 수정할 첨부파일이 없으면 => oldfile 설정
		
		if( menu_img == null ) {
			menu_img = multi.getParameter("oldfile");
		}
		
		menuDTO dto=new menuDTO();

		dto.setMenu_num(menu_num);
		dto.setMenu_name(menu_name);
		dto.setMenu_detail(menu_detail);
		dto.setMenu_price(menu_price);
		dto.setMenu_img(menu_img);
		dto.setMenu_category(menu_category);
		
		menuDAO dao=new menuDAO();
		dao.updateMenu(dto);

		ActionForward forward = new ActionForward();
				
		forward.setPath("./MenuBoardList.mn");
		forward.setRedirect(true);
				
		return forward;
				
	}

}
