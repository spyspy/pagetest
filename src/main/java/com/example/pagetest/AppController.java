package com.example.pagetest;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/love")
public class AppController {
    @RequestMapping({"/test/{page}","/test/"})
    public String wow(@PathVariable(required=false) String page,Model model){
        List<AppModel> wow = makeData();

        System.out.println("wow.size():"+wow.size());
        int totalSize = wow.size();
        int pageSize = 5;
        int pageCount = totalSize/pageSize;
        System.out.println("pageCount:"+pageCount);
        int pageCount2 = totalSize%pageSize;
        System.out.println("pageCount2"+pageCount2);

        if(pageCount2>0){
            pageCount++;
        }
        System.out.println("pageCount:"+pageCount);
        int currentPage= Integer.valueOf(page);
        System.out.println("currentPage:"+currentPage);

        model.addAttribute("totalSize",totalSize);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("page",currentPage);

        model.addAttribute("list",wow);

        return "test";
    }

    @RequestMapping({"/test"})
    public String wow2(Model model){
        List<AppModel> wow = makeData();


        model.addAttribute("list",wow);

        return "test";
    }

    @RequestMapping(value = {"", "/{page}"}, method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable(required=false, name="page") String page, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PagedListHolder<AppModel> userList;
        if(page == null) {
            userList = new PagedListHolder<AppModel>();
            List<AppModel> usersList = makeData();
            // Setting the source for PagedListHolder
            userList.setSource(usersList);
            userList.setPageSize(2);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("userList", userList);
        }else if(page.equals("prev")) {
            // get the user list from session
            userList = (PagedListHolder<AppModel>)request.getSession().getAttribute("userList");
            // switch to previous page
            userList.previousPage();
        }else if(page.equals("next")) {
            userList = (PagedListHolder<AppModel>)request.getSession().getAttribute("userList");
            // switch to next page
            userList.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            userList = (PagedListHolder<AppModel>)request.getSession().getAttribute("userList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            userList.setPage(pageNum - 1);
        }

        mv.setViewName("user");
        return mv;
    }


    private List<AppModel> makeData(){
        List<AppModel> wow = new ArrayList<>();
        AppModel app1 = new AppModel("Ashi","19","Taipei");
        AppModel app2 = new AppModel("Boyo","20","Koko");
        AppModel app3 = new AppModel("Coco","40","New York");
        AppModel app4 = new AppModel("Dydy","33","Dubai");
        AppModel app5 = new AppModel("Emily","44","Taichung4");
        AppModel app6 = new AppModel("Fxxxu","55","Stockhom");
        AppModel app7 = new AppModel("Gino","61","DCome");
        AppModel app8 = new AppModel("Hyhy","69","Here");
        AppModel app9 = new AppModel("Inono","15","There");
        AppModel app10 = new AppModel("Jojo","77","Beijin");
        AppModel app11 = new AppModel("Kuku","49","Tokyo");
        wow.add(app1);
        wow.add(app2);
        wow.add(app3);
        wow.add(app4);
        wow.add(app5);
        wow.add(app6);
        wow.add(app7);
        wow.add(app8);
        wow.add(app9);
        wow.add(app10);
        wow.add(app11);
        return wow;
    }
}
