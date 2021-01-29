package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Nokia",
                article_title
        );

    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    //Refactoring Ex6 (TestForDZ_Ex6)
    // wait закоментен, т.к. он используется общий для ArticleTest c timeoutInSeconds 15
    @Test
    public void testWaitTitleNotTimeout()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String element_found = "Nokia";
        //ArticlePageObject.waitForTitleElement();
        ArticlePageObject.assertElementPresent(element_found);
    }


}
