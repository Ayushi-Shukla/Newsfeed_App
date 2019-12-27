import {browser, element, by} from 'protractor';


export class LoginPage {
    
    get getTitleText(){
        return element(by.css('h2'))
    }

    get errorMessage() {
      return element(by.id('err'));
 }
 
    setPassword() {
        return (element(by.id('password')).sendKeys('ayushi123'));
      }

    setEmailId() {
        return (element(by.id('email')).sendKeys('ayushi@gmail.com'));
      }

      setinvalidPassword() {
        return (element(by.id('password')).sendKeys('fafafafa'));
      }

    setinvalidEmailId() {
        return (element(by.id('email')).sendKeys('fafa@gmail.com'));
      }


      setinvalidPassword2() {
        return (element(by.id('password')).sendKeys('ayushi123'));
      }

    setinvalidEmailId2() {
        return (element(by.id('email')).sendKeys('a@gmail.com'));
      }
      setadminPassword() {
        return (element(by.id('password')).sendKeys('admin123'));
      }

    setadminEmailId() {
        return (element(by.id('email')).sendKeys('admin@gmail.com'));
      }


      getlogIn() {
        return element(by.id('loginbtn1'));
      }
}
