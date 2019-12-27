import {browser, element, by} from 'protractor';


export class Homepage {
    getlogInpage(){
    return element(by.id('lg'));
    }

    getsignInpage(){
        return element(by.id('sg'));
        }

        get getTitleText(){
            return element(by.css('h3'))
        }
}