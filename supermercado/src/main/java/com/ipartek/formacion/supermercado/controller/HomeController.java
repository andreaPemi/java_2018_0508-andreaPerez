package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.model.Producto;
import com.ipartek.formacion.supermercado.model.ProductoArrayListDAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = { "/home", "/inicio" }, initParams = {
		@WebInitParam(name = "numeroProductos", value = "10", description = "Numero de productos a mostrar en la pagina inicial") })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Producto> productos;
	private static ProductoArrayListDAO dao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		dao = ProductoArrayListDAO.getInstance();

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		dao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO mirar porque da null
			String numeroProductos = this.getServletContext().getInitParameter("numeroProductos");
			// String numeroProductos="10";			
			request.setAttribute("numeroProductos", numeroProductos);

			// Recoger listado de productos
			productos = (ArrayList<Producto>) dao.getAll();

			request.setAttribute("productos", productos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

	}

}
