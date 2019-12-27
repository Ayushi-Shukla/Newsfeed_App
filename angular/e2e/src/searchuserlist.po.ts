import {browser, element, by} from 'protractor';


export class Searchuserlist {

    get getTitleText(){
        return element(by.css('h3'))
    }
}