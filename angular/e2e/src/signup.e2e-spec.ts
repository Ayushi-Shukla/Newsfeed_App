import { SignupPage } from './signup.po';
import { protractor } from 'protractor/built/ptor';
import { browser } from 'protractor';
import { LoginPage } from './login.po';
import { Homepage } from './homepage.po';


describe('Signup page', () => {
  let signuppage: SignupPage;
  let loginpage: LoginPage;
  let homepage: Homepage;

  beforeEach(() => {
    signuppage = new SignupPage();
    loginpage = new LoginPage();
    homepage= new Homepage();
    browser.get('');
  });

  it ('display title', () => {
    homepage.getsignInpage().click();
    expect(signuppage.getTitleText.getText()).toEqual('Signup Page');
  });
   

  it ('valid', () => {
    homepage.getsignInpage().click();
    signuppage.setName();
    signuppage.setEmailId();
    signuppage.setPassword();
    signuppage.getsignIn().click();
    expect(signuppage.errorMessage.getText()).toEqual('You have been successfully registered. Please click login to continue!')
  });
   
  it ('invalid', () => {
    homepage.getsignInpage().click();
    signuppage.setinvalidName();
    signuppage.setinvalidEmailId();
    signuppage.setinvalidPassword();
    signuppage.getsignIn().click();
    expect(signuppage.errorMessage.getText()).toEqual('Email Already Exists');
  });


  it ('navigating to loginpage', () => {
    homepage.getsignInpage().click();
    signuppage.getloginbtn().click();
    expect(loginpage.getTitleText.getText()).toEqual('Login Page');
});

});