package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null) { 
            request.setAttribute("message",
                "セッションが切れています。もう一度トップページより操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
        
		String payment = request.getParameter("pay");
		
		String selectedPayment;
		if (payment == null) {
			request.setAttribute("message",
	                "支払方法を選択してください。");
	            gotoPage(request, response, "/errInternal.jsp");
	            return;
		} else {
			switch (payment) {
			case "cash":
				selectedPayment = "代金引換";
				break;
			case "card":
				selectedPayment = "クレジットカード決済";
				session.setAttribute("card", selectedPayment);
				 gotoPage(request, response, "/order.jsp");
				break;
			case "convini":
				selectedPayment = "コンビニ決済";
				break;
			default:
				selectedPayment = "???";
				break;
			}
		}

		
		

		
	}

	 private void gotoPage(HttpServletRequest request,
	       HttpServletResponse response, String page) throws ServletException,
	       IOException {
	      RequestDispatcher rd = request.getRequestDispatcher(page);
	      rd.forward(request, response);
	    }
}