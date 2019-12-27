import { protractor } from 'protractor/built/ptor';
import { browser } from 'protractor';
import { SignupPage } from './signup.po';
import { LoginPage } from './login.po';
import { WelcomePage } from './welcome.po';
import { Homepage } from './homepage.po';



describe('Login page', () => {
    let welcomepage: WelcomePage;
    let homepage: Homepage;
    let loginpage: LoginPage;
    beforeEach(() => {
        loginpage = new LoginPage();
        welcomepage = new WelcomePage();
        homepage = new Homepage();

      browser.get('');
    });

    it ('valid login', () => {
            homepage.getlogInpage().click();
            loginpage.setEmailId();
            loginpage.setPassword();
            loginpage.getlogIn().click();
            expect(welcomepage.getTitleText.getText()).toEqual('WELCOME');
        });

        it ('Invalid credentials login', () => {
            homepage.getlogInpage().click();
            loginpage.setinvalidEmailId();
            loginpage.setinvalidPassword();
            loginpage.getlogIn().click();
            expect(loginpage.errorMessage.getText()).toEqual('Invalid credentials! Please try again');
        });

        it ('Blaclisted user login', () => {
            homepage.getlogInpage().click();
            loginpage.setinvalidEmailId2();
            loginpage.setinvalidPassword2();
            loginpage.getlogIn().click();
            expect(loginpage.errorMessage.getText()).toEqual('Access denied');
        });

    it ('navigating to welcome page', () => {
        homepage.getlogInpage().click();
        loginpage.setEmailId();
        loginpage.setPassword();
        loginpage.getlogIn().click();
        expect(welcomepage.getTitleText.getText()).toEqual('WELCOME');
    });
});