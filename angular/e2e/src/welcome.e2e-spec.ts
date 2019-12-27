import { browser } from 'protractor';
import { WelcomePage } from './welcome.po';
import { Searchlistpage } from './searchednews.po';
import { Homepage } from './homepage.po';
import { LoginPage } from './login.po';



describe('Welcome page', () => {
    
    let welcomepage: WelcomePage;
    let searchlist: Searchlistpage;
    let homepage: Homepage;
    let loginpage: LoginPage;


    beforeEach(() => {
      welcomepage= new WelcomePage();
      searchlist= new Searchlistpage();
      homepage=new Homepage;
      loginpage=new LoginPage;

      browser.get('');
    });

    
  it ('display title', () => {
      homepage.getlogInpage().click();
      loginpage.setEmailId();
      loginpage.setPassword();
      loginpage.getlogIn().click();
     expect(welcomepage.getTitleText.getText()).toEqual('WELCOME');
  });

  it ('navigating to search list page', () => {
    homepage.getlogInpage().click();
    loginpage.setEmailId();
    loginpage.setPassword();
    loginpage.getlogIn().click();
    welcomepage.searchlist.click();
    expect(searchlist.getTitleText.getText()).toEqual('List Of Words Searched');
    });
});