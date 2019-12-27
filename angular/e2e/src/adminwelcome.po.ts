import {browser, element, by} from 'protractor';


export class AdminwelcomePage{

    get getTitleText(){
        return element(by.css('h3'))
    }

    get searchuser() {
        return element(by.id('searchbtn'));
      }

    setUsernameToSearch() {
        return (element(by.id('txtSearch')).sendKeys('water'));
      }

      get searchlist() {
        return element(by.id('previousSearchbtn1'));
      }
}