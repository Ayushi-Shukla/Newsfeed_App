import { browser } from "protractor";
import { AdminwelcomePage } from "./adminwelcome.po";
import { Searchuserlist } from "./searchuserlist.po";

describe('Searchuserlist', () => {

    let searchuserlist: Searchuserlist;

    beforeEach(() => {    
        searchuserlist = new Searchuserlist();
      browser.get('adminwelcome/admin@gmail.com');
    });
});