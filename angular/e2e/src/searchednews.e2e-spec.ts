import { LoginPage } from './login.po';
import { protractor } from 'protractor/built/ptor';
import { browser } from 'protractor';
import { WelcomePage } from './welcome.po';
import { Searchlistpage } from './searchednews.po';
import { Homepage } from './homepage.po';


describe('Searchlist page', () => {
  let homepage: Homepage;
    let searchlist: Searchlistpage;
    let loginpage: LoginPage;
    let welcomepage: WelcomePage;

    beforeEach(() => {
    searchlist= new Searchlistpage();
    loginpage= new LoginPage();
    welcomepage=new WelcomePage();
    homepage=new Homepage();


      browser.get('');
    });

    it ('displaying searched list', () => {
      homepage.getlogInpage().click();
            loginpage.setEmailId();
            loginpage.setPassword();
            loginpage.getlogIn().click();
      welcomepage.searchlist.click();
      expect(searchlist.getTitleText.getText()).toEqual('List Of Words Searched');

      });
});