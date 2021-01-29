package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("Finnish technology and telecommunications company");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForNavigateUpButtonToAppear();
        SearchPageObject.clickNavigateUpButton();
        SearchPageObject.waitForNavigateUpButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        String search_Line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_Line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        String search_Line = "zxcvasdfqwer";
        SearchPageObject.typeSearchLine(search_Line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have ben changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have ben changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("Finnish technology and telecommunications company");
        this.backgroundApp(5);
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("Finnish technology and telecommunications company");

    }

    //Refactoring Ex3 (TestForDZ_Ex3)
    @Test
    public void testCancellationSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("Finnish technology and telecommunications company");
        SearchPageObject.clearSearchLine();
    }
}
