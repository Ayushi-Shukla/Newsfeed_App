import {browser, element, by} from 'protractor';

export class WelcomePage {
    
    get getTitleText(){
        return element(by.css('h2'))
    }

    get searchlist() {
        return element(by.id('previousSearchbtn'));
      }
    
}