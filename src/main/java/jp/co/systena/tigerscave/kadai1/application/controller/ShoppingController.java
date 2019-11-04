package jp.co.systena.tigerscave.kadai1.application.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.kadai1.application.model.Cart;
import jp.co.systena.tigerscave.kadai1.application.model.DeleteForm;
import jp.co.systena.tigerscave.kadai1.application.model.ListForm;
import jp.co.systena.tigerscave.kadai1.application.model.Order;
import jp.co.systena.tigerscave.kadai1.application.service.ListService;

@Controller
public class ShoppingController {

  @Autowired
  HttpSession session;

  ListService listService = new ListService();
  ListForm listForm = new ListForm();
  Cart cart = new Cart();
  DeleteForm deleteForm = new DeleteForm();


  @RequestMapping("/Shopping")
  public ModelAndView list(ModelAndView mav) {

    mav.addObject("itemList", listService.getItemList());
    mav.setViewName("ListView");

    return mav;
  }

  @RequestMapping(value = "/AddOrder", method = RequestMethod.POST)
  public ModelAndView order(HttpSession session, ModelAndView mav, @Valid ListForm listForm, @Valid DeleteForm deleteForm) {

    mav.addObject("itemList", listService.getItemList());

    int itemId = listForm.getItemId();
    int num = listForm.getNum();
    int price = listForm.getPrice();

    Order order = new Order(itemId, num, price);
    cart.addOrder(order);
    cart.setTotalPrice(0);
    session.setAttribute("cart", cart);

    Cart cartSession = (Cart)session.getAttribute("cart");
    int totalPrice = cartSession.operateTotalPrice();

    mav.addObject("cart", cartSession);
    mav.addObject("totalPrice", totalPrice);
    mav.setViewName("cartView");

    return mav;
  }

  @RequestMapping(value = "/DeleteOrder", method = RequestMethod.POST)
  public ModelAndView deleteOrder(HttpSession session, ModelAndView mav, @Valid ListForm listForm, @Valid DeleteForm deleteForm) {

    mav.addObject("itemList", listService.getItemList());

    int itemId = deleteForm.getItemId();

    Cart cartSession = (Cart)session.getAttribute("cart");
    cartSession.deleteOrder(itemId);
    cartSession.setTotalPrice(0);
    int totalPrice = cartSession.operateTotalPrice();

    mav.addObject("cart", cartSession);
    mav.addObject("totalPrice", totalPrice);
    mav.setViewName("cartView");

    return mav;
  }


}
