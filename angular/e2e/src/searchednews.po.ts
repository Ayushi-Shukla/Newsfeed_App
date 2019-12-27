import {browser, element, by} from 'protractor';

export class Searchlistpage {
    
    get getTitleText(){
        return element(by.css('h3'))
    }
}