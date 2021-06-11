package jp.co.froide.employeeListApp.controller;

import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TransientDataAccessResourceException.class})
        public String transientDataAccessResourceException(HttpServletRequest request, Model model, TransientDataAccessResourceException t) {
        System.out.println(t.getClass() + "" + t.fillInStackTrace() + "" + t.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("message", "DBサーバーの接続に失敗しました。");
        model.addAttribute("button", "再接続する");
        model.addAttribute("method", request.getMethod());
        System.out.println(request.getQueryString());
        String box = "";
        try {
            box = URLDecoder.decode(request.getQueryString(), "UTF-8");
            String d = "searchMethod";
            String e = "word";
            int a = box.indexOf(d);
            int b = box.indexOf(e);
            String searchMethod = box.substring(a+d.length()+1, b-1);
            String word = box.substring(b+e.length()+1);
            System.out.println(word);
            System.out.println(searchMethod);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
        }catch(StringIndexOutOfBoundsException s){
            s.printStackTrace();
        }catch (NullPointerException n){
            n.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        model.addAttribute("url",request.getRequestURI());

        System.out.println(request.getContextPath()+"1"+request.getPathInfo()+"2"+request.getPathTranslated()+"3"+request.getQueryString()+" 4"+request.getRequestURI());
        return "error";
    }
}
