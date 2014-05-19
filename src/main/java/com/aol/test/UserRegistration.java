package com.aol.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class UserRegistration
 */
public class UserRegistration extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HttpClient client = null;
    private UserVO user = null;
    String successMsg="";
    String failureMsg="";
    public void init() throws ServletException {
    }

    /**
     * Default constructor.
     */
    public UserRegistration() {
        client = new DefaultHttpClient();
        user = new UserVO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer sb = null;
        
        try {

            String postResString = "";

            BasicClientCookie cookie = new BasicClientCookie("ipq1234", "ipq1234");
            cookie.setDomain("myopenissues.com");
            cookie.setPath("/magento");

            DefaultHttpClient client = new DefaultHttpClient();
            CookieStore cookieStore = client.getCookieStore();
            cookieStore.addCookie(cookie);
            client.setCookieStore(cookieStore);

            HttpPost httpPost = new HttpPost("http://myopenissues.com/magento/index.php/customer/account/createpost/");
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstname"));
            user.setLastName(request.getParameter("lastname"));
            user.setPassword(request.getParameter("password"));
   
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("firstname",
                    user.getFirstName()));
            nameValuePairs.add(new BasicNameValuePair("lastname",
                    user.getEmail()));
            nameValuePairs.add(new BasicNameValuePair("password",
                    user.getPassword()));
            nameValuePairs.add(new BasicNameValuePair("email",
                    user.getEmail()));
            nameValuePairs.add(new BasicNameValuePair("confirmation",
                    request.getParameter("confirmation")));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse postRes = client.execute(httpPost);

            postResString = EntityUtils.toString(postRes.getEntity());
                 parseResponseMessage(postResString);
                    request.setAttribute("SuccessMsg", successMsg);
                    request.setAttribute("ErrorMsg", failureMsg);
   ServletContext context= getServletContext();
            context.getRequestDispatcher("/Register.jsp").forward(request, response);
         
      } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private void  parseResponseMessage(String postResString) {
        Document doc = Jsoup.parse(postResString);	

        Elements errorMsgs = doc.select(".error-msg ul");
		if(errorMsgs.size() > 0) {
			for(Element elem : errorMsgs) {
				successMsg += elem.html();
			}
		}

		//Check if success message is present, add to map if it is
		Elements successMsgs = doc.select(".success-msg ul");
		if(successMsgs.size() > 0) {
			failureMsg = successMsgs.text();
		}
    
    }
}
