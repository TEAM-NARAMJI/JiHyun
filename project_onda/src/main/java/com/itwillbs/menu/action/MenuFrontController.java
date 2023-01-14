package com.itwillbs.menu.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주소 매핑

		// 가상주소 임의로 뽑아오기 => http://localhost:8080/Model2/insertForm.me
		// toString: 정보 문자열로 전환 -> 전체 주소를 가져옴
		String requestURL = request.getRequestURL().toString();
		System.out.println("requestURL : " + requestURL);

		// 만들어진 URI가져오기: => /Model2/insertForm.me
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : " + requestURI);

		// 프로젝트 명(Context명)가져오기: getContextPath()=> /Model2
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		System.out.println("contextPath길이 : " + contextPath.length());

//			substring : 지정한만큼 자르기 
		String strpath = request.getServletPath();
		System.out.println("뽑은 주소 strpath : " + strpath);
		System.out.println("주소뽑기완@@@");
		// 주소비교
		Action action=null;
		ActionForward forward=null;
		
		if(strpath.equals("/MenuInsertForm.mn")) {
			System.out.println("@@@@@");
			forward=new ActionForward();
			forward.setPath("./admin/InsertForm.jsp");
			forward.setRedirect(false);
			
		}else if(strpath.equals("/MenuInsertPro.mn")) {
			System.out.println("MenuInsertPro.mn 실행");
			action=new MenuInsertPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (strpath.equals("/MenuDelete.mn")) {
			// BoardDelete 객체 생성
			action= new MenuDelete();
			// 메서드 호출
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}else if (strpath.equals("/MenuBoardList.mn")) {
			
			action=new MenuBoardList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(strpath.equals("/MenuUpdateForm.mn")) {
			System.out.println("MenuUpdateForm.mn");
			action=new MenuUpdateForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(strpath.equals("/MenuUpdatePro.mn")) {
			action=new MenuUpdatePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}else if(strpath.equals("/MenuDelete.mn")) {
			action=new MenuDelete();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		} else if(strpath.equals("/MenuDetailPage.mn")) {
			System.out.println("menuDetailepage!!!!!!!!!!!!!!!!!!!!!!!");
			forward=new ActionForward();
			forward.setPath("./menu/mainMenu.jsp");
			forward.setRedirect(false);
			
		} else if(strpath.equals("/DrinkList.mn")) {
			action=new DrinkList();
		
			System.out.println("/DrinkList.mn");
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else if(strpath.equals("/FoodList.mn")) {
			action=new FoodList();
			System.out.println("/FoodList.mn");
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(strpath.equals("/GoodsList.mn")) {
			action=new GoodsList();
			System.out.println("/GoodsList.mn");
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		
		
		
		
		
		
		
		
		
//		이동 (-> (request)정보가 있으면 이동)
			if(forward != null) {
				if(forward.isRedirect()) {
					
					// true : 주소변경 되면서 이동
					System.out.println("true:"+ forward.getPath() +" sendRedirect() 이동");
					response.sendRedirect(forward.getPath());
				} else {
					
					// false : 주소변경 안되면서 이동
					System.out.println("false:"+ forward.getPath() +"foward() 이동");
					RequestDispatcher dis
					=request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);	
				}
				
			}
	}
	
	
	

	
	
	
	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}