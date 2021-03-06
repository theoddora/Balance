package com.balance.controller;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.balance.dao.ProductDao;
import com.balance.model.Product;

@Controller
public class ProductController {

    private static final String ADDED_TO_CART = "The selected amount has been added to the cart";
    private static final String MESSAGE = "message";

    @Autowired
    ProductDao productDao;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProducts(Model model) {

        List<Product> products = productDao.getAllSellingProducts();

        model.addAttribute("products", products);

        return "work";
    }

    //
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCart(Model model, Principal principal, HttpSession session) {

        if (principal == null) {
            return "log_in";
        }

        Map<Product, Double> currentCart = (Map<Product, Double>) session.getAttribute("cart");
        if (currentCart == null) {
            currentCart = new HashMap<Product, Double>();
        }

        model.addAttribute("currentCart", currentCart);
        return "cart";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String addToCart(@RequestParam("productId") int id, @RequestParam("amount") double amount, Principal principal, HttpSession session, RedirectAttributes ra) {

        if (principal == null) {
            return "log_in";
        }

        if (amount < 0) {
            return "redirect:/product";
        }

        Map<Product, Double> currentCart = (Map<Product, Double>) session.getAttribute("cart");
        if (currentCart == null) {
            currentCart = new HashMap<>();
            session.setAttribute("cart", currentCart);
        }

        String message;
        double priceToShow = 0.0;

        Product product = productDao.findProductById(id);
        boolean isForKilo = product.getIsForKilo();

        if (!productDao.hasEnoughAmount(amount, id, isForKilo)) {
            if (currentCart.containsKey(product)) {
                currentCart.remove(product);
            }
            double currentAmount = productDao.getCurrentAmount(id, isForKilo);
            message = "There is not enough amount from " + product.getName() + ". The current amount is " + currentAmount + ".";
            ra.addFlashAttribute(MESSAGE, message);
            return "redirect:/cart";
        }
        currentCart.put(product, amount);
        ra.addFlashAttribute(MESSAGE, ADDED_TO_CART);
        for (Map.Entry<Product, Double> productDoubleEntry : currentCart.entrySet()) {
            //(current product - discount) * amount
            priceToShow += (productDoubleEntry.getKey().getPrice()
                - (productDoubleEntry.getKey().getPrice() * productDoubleEntry.getKey().getDiscount())) * productDoubleEntry.getValue();
        }

        session.setAttribute("priceToShow", priceToShow);
        return "redirect:/cart";
    }

    //returning view
    @RequestMapping(value = "/increasequantity", method = RequestMethod.GET)
    public String increaseQuantityJSP(Model model) {


        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);

        model.addAttribute(new Product());


        return "increasequantity";

    }

    //updating products
    @RequestMapping(value = "/increasequantity", method = RequestMethod.POST)
    public String increaseProductsQuantity(Product product) {
        System.out.println(product);
        if (product.getAmountKilo() != 0) {
            productDao.increaseProductByKilo(product.getAmountKilo(), product.getId());
        } else if (product.getAmountPiece() != 0) {
            productDao.increaseProductByPiece(product.getAmountPiece(), product.getId());
        }

        return "/";
    }

    //returning view
    @RequestMapping(value = "/addproducts", method = RequestMethod.GET)
    public String addProductsJSP(Model model) {

        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);


        model.addAttribute(new Product());


        return "addproducts";
    }

    //inserting product
    @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
    public String addProducts(Product product, @RequestParam("file") MultipartFile
        file) {

        productDao.insertProduct(product);
        String productName = product.getName();

        try {
            file.transferTo(new File(productName + ".jpg"));

        } catch (Exception e) {
            System.out.println(e);
        }

        return "addproducts";
    }

    //returning view
    @RequestMapping(value = "/manageproducts", method = RequestMethod.GET)
    public String manageProductsJSP(Model model) {


        prepareRender(model);

        return "manageproducts";
    }

    //managing products
    @RequestMapping(value = "/removeproduct", method = RequestMethod.POST)
    public String removeProduct(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("productToDelete"));

        productDao.removeProduct(id);
        prepareRender(model);
        return "manageproducts";

    }

    @RequestMapping(value = "/addproducttothestore", method = RequestMethod.POST)
    public String addToTheStore(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("productToAdd"));

        productDao.addToTheStore(id);
        prepareRender(model);
        return "manageproducts";
    }

    @RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
    public String updateProducts(HttpServletRequest request, Model model, Product product) {

        int id = Integer.parseInt(request.getParameter("productToUpdate"));

        productDao.updateProduct(id, product);


        prepareRender(model);


        return "manageproducts";

    }

    @RequestMapping(value = "/addquantity", method = RequestMethod.POST)
    public String addQuantity(HttpServletRequest request, Model model, Product product) {

        int id = Integer.parseInt(request.getParameter("quantityToAdd"));

        productDao.updateProduct(id, product);


        prepareRender(model);


        return "manageproducts";

    }

    private void prepareRender(Model model) {

        List<Product> products = productDao.getAllSellingProducts();
        List<Product> notSellingProducts = productDao.getAllNotSellingProducts();
        List<Product> emptyProducts = productDao.getAllEmptyProducts();

        model.addAttribute("notSellingProducts", notSellingProducts);
        model.addAttribute("products", products);
        model.addAttribute("emptyProducts", emptyProducts);
        model.addAttribute(new Product());
    }
}
