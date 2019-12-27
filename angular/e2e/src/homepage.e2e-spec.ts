import { LoginPage } from './login.po';
import { browser } from 'protractor';
import { Homepage } from './homepage.po';
import { SignupPage } from './signup.po';


describe('Home page', () => {
  
    let loginpage: LoginPage;
    let homepage: Homepage;
    let signuppage: SignupPage;


    beforeEach(() => {
        loginpage = new LoginPage();
        homepage= new Homepage();
        signuppage = new SignupPage();


      browser.get('');
    });

    it ('navigating to login page', () => {
        homepage.getlogInpage().click();
        expect(loginpage.getTitleText.getText()).toEqual('Login Page');
    });

    it ('navigating to signup page', () => {
        homepage.getsignInpage().click();
        expect(signuppage.getTitleText.getText()).toEqual('Signup Page');
    });
});