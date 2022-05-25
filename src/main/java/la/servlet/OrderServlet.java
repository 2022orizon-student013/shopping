package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CartBean;
import la.bean.CustomerBean;
import la.dao.DAOException;
import la.dao.OrderDAO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null) { 
            request.setAttribute("message",
                "セッションが切れています。もう一度トップページより操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
        CartBean cart = (CartBean)session.getAttribute("cart");
        if (cart == null) {
            request.setAttribute("message", "正しく操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }

        try {
        
            String action = request.getParameter("action");
            if (action == null || action.length() == 0
                    || action.equals("input_customer")) {
               
                gotoPage(request, response, "/customerInfo.jsp");
            } else if (action.equals("confirm")) {
               
                CustomerBean bean = new CustomerBean();
                bean.setName(request.getParameter("name"));
                bean.setAddress(request.getParameter("address"));
                bean.setTel(request.getParameter("tel"));
                bean.setEmail(request.getParameter("email"));
                session.setAttribute("customer", bean);
                
                ///追加機能お試し版
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
        				 
        				break;
        			case "convini":
        				selectedPayment = "コンビニ決済";
        				break;
        			default:
        				selectedPayment = "???";
        				break;
        			}
        		}
                // おわり
                
                gotoPage(request, response, "/confirm.jsp");
            } else if (action.equals("order")) {
                 
                CustomerBean customer = 
                         (CustomerBean)session.getAttribute("customer");
                if (customer == null) { 
                    request.setAttribute("message", "正しく操作してください。");
                    gotoPage(request, response, "/errInternal.jsp");
                    return;
                }

                OrderDAO order = new OrderDAO();
                int orderNumber = order.saveOrder(customer, cart);
                
                session.removeAttribute("cart");
                session.removeAttribute("customer");
                
                request.setAttribute("orderNumber", Integer.valueOf(orderNumber));
                                               
                gotoPage(request, response, "/order.jsp");
                
            } else { 
               
                request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

    private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}