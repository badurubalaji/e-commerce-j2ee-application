/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CategoryFacade;
import session.ProductFacade;

/**
 *
 * @author dangkhoa
 */
@WebServlet(name = "Controller",
            loadOnStartup = 1,
            urlPatterns = {"/category",
                           "/addToCart",
                           "/viewCart",
                           "/updateCart",
                           "/checkout",
                           "/purchase",
                           "/chooseLanguage"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("categories", categoryFacade.findAll());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String userPath = request.getServletPath();
        String url;
        Category selectedCategory = null;
        List<Product> categoryProducts = null;
        // if category page is requested
        if (userPath.equals("/category")) {
            // TODO: Implement category request
            // get categoryId from request
            String categoryId = request.getQueryString();

            if (categoryId != null) {
                // get selected category
                selectedCategory = categoryFacade.find(Short.parseShort(categoryId));
            }
            // place selected category in request scope
            request.setAttribute("selectedCategory", selectedCategory);
            // get all products for selected category
            categoryProducts = productFacade.findForCategory(selectedCategory);
            // place category products in request scope
            request.setAttribute("categoryProducts", categoryProducts);

        } else if (userPath.equals("/viewCart")) {
            userPath = "/cart";
            // TODO: Implement cart page request

            // if checkout page is requested
        } else if (userPath.equals("/checkout")) {
            // TODO: Implement checkout page request
            // if user switches language
        } else if (userPath.equals("/chooseLanguage")) {
            // TODO: Implement language request
        }

        // use RequestDispatcher to forward request internally
        url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        // if addToCart action is called
        if (userPath.equals("/addToCart")) {
            // TODO: Implement add product to cart action
            // if updateCart action is called
        } else if (userPath.equals("/updateCart")) {
            // TODO: Implement update cart action
            // if purchase action is called
        } else if (userPath.equals("/purchase")) {
            // TODO: Implement purchase action
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller Servlet";
    }// </editor-fold>
}
