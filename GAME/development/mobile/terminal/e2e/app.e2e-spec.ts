import { Page } from './app.po';
import { describe } from "selenium-webdriver/testing";

describe('App', () => {
  let page: Page;

  beforeEach(() => {
    page = new Page();
  });

  describe('default screen', () => {
    beforeEach(() => {
      page.navigateTo('/');
    });

    it('should have a title saying Terminal de Votação', () => {
      page.getPageOneTitleText().then(title => {
        expect(title).toEqual('Terminal de Votação');
      });
    });
  });

  describe('página de reuniões', () => {
      beforeEach(() => {
          page.navigateTo('/');

          page.enterInTheMeetingPage();

          page.pageSleep(3000);
      });

      it('deve mostrar a lista de reuniões abertas', function () {
          page.getMeetings().then(meetings => {
            console.log(meetings);
          })
      });
  })
});
