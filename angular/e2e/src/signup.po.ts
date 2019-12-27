import {browser, element, by} from 'protractor';

export class SignupPage {

  get Name() {
    return element(by.id('username'));
  }

  get Password() {
    return element(by.id('password'));
  }
 
  get EmailId() {
    return element(by.id('email'));
  }
  setName() {
    return (element(by.id('username')).sendKeys('miku'));
  }
  setPassword() {
    return (element(by.id('password')).sendKeys('miketyson'));
  }
  setEmailId() {
    return (element(by.id('email')).sendKeys('mikuuu@gmail.com'));
  }

  setinvalidName() {
    return (element(by.id('username')).sendKeys('ayushi'));
  }
  setinvalidPassword() {
    return (element(by.id('password')).sendKeys('ayushi12345'));
  }
  setinvalidEmailId() {
    return (element(by.id('email')).sendKeys('ayushi@gmail.com'));
  }

  get getTitleText(){
      return element(by.css('h3'))
  }

  get errorMessage() {
     return element(by.id('err'));
}

  getsignIn() {
    return element(by.id('regbtn'));
  }

  getloginbtn() {
    return element(by.id('loginbtn'));
  }
}
