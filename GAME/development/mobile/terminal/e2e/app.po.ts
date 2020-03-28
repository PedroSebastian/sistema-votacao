import {browser, by, element} from 'protractor';

export class Page {

    navigateTo(destination) {
        return browser.get(destination);
    }

    pageSleep(time) {
        browser.driver.sleep(time);
    }

    getTitle() {
        return browser.getTitle();
    }

    getPageOneTitleText() {
        return element(by.tagName('page-home')).element(by.tagName('ion-title')).element(by.css('.toolbar-title')).getText();
    }

    enterInTheMeetingPage() {
        return element(by.tagName('page-home')).element(by.tagName('ion-content')).element(by.css('.button')).click();
    }

    getMeetings() {
        return element(by.tagName('page-home')).element(by.tagName('ion-content')).element(by.css('.scroll-content'))
            .element(by.xpath('//ion-app/page-reunioes/ion-content/div[2]/ion-card/ion-card-content')).getWebElement();
    }
}
