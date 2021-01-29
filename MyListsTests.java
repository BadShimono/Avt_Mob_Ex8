package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = "Nokia";
        String name_of_folder = "Mobil device company";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

    }


    //Refactoring Ex5 (TestForDZ_Ex5) тест немного модернизирован, теперь статьи добовляются не просто в папку "Save",
    //а в специально создаваемую папку.
    //Два article_title используются ввиду того что, не получается воспользоватьс в виду того что в Beta версии
    // приложения title на основной станице статьи находится в attribute "content-desc", а не в attribute "text".

    @Test
    public void testSaveSecondArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = "HTC";
        String article_title1 = "Nokia";
        String name_of_folder = "Mobil device company";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticleAndClearSearchLine();

        SearchPageObject.typeSearchLine("HTC");
        SearchPageObject.clickByArticleWithSubstring("Taiwanese electronics company");

        ArticlePageObject.waitForTitleElement1();
        ArticlePageObject.addArticleMyList();
        ArticlePageObject.savedArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
        MyListPageObject.waitForArticleToMyListAndClick(article_title1);

        ArticlePageObject.waitForTitleElement();

    }

}
