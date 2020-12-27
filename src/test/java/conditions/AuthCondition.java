package conditions;

import data.LoginData;
import factory.PageType;
import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import pages.LoginPage;
import util.BaseTest;
import utils.JsonReaderHelper;

@Slf4j
public abstract class AuthCondition extends BaseTest implements BaseCondition {

  @Override
  public void preExecute() {
    LoginPage loginPage = (LoginPage) pageFactory.getInstance(PageType.LOGIN);
    LoginData loginData = this.readLoginData();
    loginPage.login(loginData.getUserName(), loginData.getPassword());
  }

  @Override
  public void postExecute() {
    // implement the post execution logic here
  }

  private LoginData readLoginData() {
    try {
      return JsonReaderHelper.getLoginData()[0];
    } catch (FileNotFoundException ex) {
      log.error("FileNotFoundException : ", ex);
    }
    return null;
  }
}
