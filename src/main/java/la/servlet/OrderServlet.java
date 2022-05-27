package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CardBean;
import la.bean.CartBean;
import la.bean.CustomerBean;
import la.dao.CardDAO;
import la.dao.DAOException;
import la.dao.OrderDAO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null) {
			request.setAttribute("message", "セッションが切れています。もう一度トップページより操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
			return;
		}
		CartBean cart = (CartBean) session.getAttribute("cart");
		if (cart == null) {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
			return;
		}
		// 追加です

		try {

			String action = request.getParameter("action");

			if (action == null || action.length() == 0 || action.equals("input_customer")) {

				gotoPage(request, response, "/customerInfo.jsp");
			} else if (action.equals("confirm")) {
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");

				if (name == null || name.length() == 0 || address == null || address.length() == 0 || tel == null
						|| tel.length() == 0 || email == null || email.length() == 0) {
					request.setAttribute("message", "入力漏れ、入力間違いがあります。");
					gotoPage(request, response, "/errInternal.jsp");
					return;
				}
				try {

					int IntTel = Integer.parseInt(tel);

					CustomerBean bean = new CustomerBean();
					bean.setName(request.getParameter("name"));
					bean.setAddress(request.getParameter("address"));
					bean.setTel(request.getParameter("tel"));
					bean.setEmail(request.getParameter("email"));
					session.setAttribute("customer", bean);

					// 追加です
					String payment = request.getParameter("pay");
					if (payment.equals("cash")) {
						request.setAttribute("message", "代金引換");
						gotoPage(request, response, "/confirmCash.jsp");
					} else if (payment.equals("card")) {
						request.setAttribute("message", "クレジットカード決済");
						gotoPage(request, response, "/cardInfo.jsp");

					} else if (payment.equals("convini")) {
						request.setAttribute("message", "コンビニ決済");
						gotoPage(request, response, "/conviniInfo.jsp");

					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("message", "電話番号は数字のみを入力してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} else if (action.equals("cardInfo")) {
				String name = request.getParameter("cardName");
				String num = request.getParameter("cardNum");
				String mon = request.getParameter("cardMonth");
				String year = request.getParameter("cardYear");
				String pass = request.getParameter("cardPass");

				if (name == null || name.length() == 0 || num == null || num.length() == 0 || mon == null
						|| mon.length() == 0 || year == null || year.length() == 0 || pass == null
						|| pass.length() == 0) {
					request.setAttribute("message", "入力漏れ、入力間違いがあります。");
					gotoPage(request, response, "/errInternal.jsp");
					return;
				}
				try {

					int Intnum = Integer.parseInt(num);
					int Intmon = Integer.parseInt(mon);
					int Intyear = Integer.parseInt(year);
					int Intpass = Integer.parseInt(pass);

					CardBean card = new CardBean();
					card.setName(request.getParameter("cardName"));
					card.setNum(request.getParameter("cardNum"));
					card.setMonth(request.getParameter("cardMonth"));
					card.setYear(request.getParameter("cardYear"));
					card.setPass(request.getParameter("cardPass"));
					session.setAttribute("card", card);
					request.setAttribute("message", "クレジットカード決済");

					gotoPage(request, response, "/confirmCard.jsp");
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("message", "名前以外は数字のみを入力してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} else if (action.equals("conviniInfo")) {
				String kinds = request.getParameter("kinds");
				if (kinds.equals("famima")) {
					request.setAttribute("convini", "ファミリーマート");
				} else if (kinds.equals("seven")) {
					request.setAttribute("convini", "セブンイレブン");
				} else if (kinds.equals("lawson")) {
					request.setAttribute("convini", "ローソン");
				}
				request.setAttribute("message", "コンビニ決済");
				gotoPage(request, response, "/confirmConvini.jsp");

			} else if (action.equals("order")) {

				CustomerBean customer = (CustomerBean) session.getAttribute("customer");

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

			} else if (action.equals("orderCard")) {

				CustomerBean customer = (CustomerBean) session.getAttribute("customer");
				CardBean card = (CardBean) session.getAttribute("card");

				if (customer == null) {
					request.setAttribute("message", "正しく操作してください。");
					gotoPage(request, response, "/errInternal.jsp");
					return;
				}

				OrderDAO order = new OrderDAO();
				int orderNumber = order.saveOrder(customer, cart);
				CardDAO dao = new CardDAO();
				dao.saveCard(customer, card);

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

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}