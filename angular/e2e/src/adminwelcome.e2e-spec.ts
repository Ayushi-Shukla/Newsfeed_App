import { browser } from "protractor";
import { AdminwelcomePage } from "./adminwelcome.po";
import { Searchuserlist } from "./searchuserlist.po";
import { Searchlistpage } from "./searchednews.po";
import { LoginComponent } from "src/app/login/login.component";
import { LoginPage } from "./login.po";
import { Homepage } from "./homepage.po";

describe('Adminwelcome page', () => {

    let adminwelcomepage: AdminwelcomePage;
    let searchuserlist: Searchuserlist;
    let searchlist: Searchlistpage;
    let loginpage: LoginPage;
    let homepage: Homepage;

    beforeEach(() => {    
      adminwelcomepage = new AdminwelcomePage();
      searchuserlist = new Searchuserlist();
      searchlist = new Searchlistpage();
      loginpage = new LoginPage();
      homepage=new Homepage;

      browser.get('');
    });

    it ('displaying user list', () => {
      homepage.getlogInpage().click();
      loginpage.setadminEmailId();
      loginpage.setadminPassword();
      loginpage.getlogIn().click();
      adminwelcomepage.setUsernameToSearch();
      adminwelcomepage.searchuser.click();
      expect(searchuserlist.getTitleText.getText()).toEqual('List of users');
      });

    it ('navigating to searched news list page', () => {
      homepage.getlogInpage().click();
      loginpage.setadminEmailId();
      loginpage.setadminPassword();
      loginpage.getlogIn().click();
    adminwelcomepage.searchlist.click();
    expect(searchlist.getTitleText.getText()).toEqual('List Of Words Searched');
    });

});