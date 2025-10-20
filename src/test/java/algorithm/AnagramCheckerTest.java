package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramCheckerTest {

    @Test
    public void testPalindrome() {
        String word1 = "tea";
        String word2 = "aet";

        Assertions.assertTrue(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testNotAnagram() {
        String word1 = "hello";
        String word2 = "world";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testPalindromeWithDifferentCase() {
        // Make your solution ignore casing
        String word1 = "Tea";
        String word2 = "Aet";

        Assertions.assertTrue(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testPalindromeWithDifferentCaseAndSpaces() {
        String word1 = "Tea ";
        String word2 = "Aet";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testPalindromeWithDifferentCaseAndSpacesAndSpecialCharacters() {
        String word1 = "Tea!";
        String word2 = "Aet";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testPalindromeWithDifferentCaseAndSpacesAndSpecialCharactersAndNumbers() {
        String word1 = "Tea!1";
        String word2 = "Aet";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testAnagram() {
        String word1 = "listen";
        String word2 = "silent";

        Assertions.assertTrue(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testAnagramWithDifferentCase() {
        String word1 = "Listen";
        String word2 = "Silent";

        Assertions.assertTrue(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void testAnagramMultiplicationEdgeCase()
    {
        // If you trigger this test case it is because you are multiplying ascii values together...
        // This is correct in about 990 out of 1000 cases when there are 10 characters...
        // This is one of the cases it is wrong in
        String word1 = "adhqrguyhk";
        String word2 = "burkgapqyh";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void TestAnagramDivisionEdgeCase()
    {
        // This tests for an edge case. your solution is incorrect.
        String word2 = "ugvapoxpym";
        String word1 = "etuvglzxhe";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void TestAnagramDivisionWithoutMinusAEdgeCase()
    {
        // The script to disprove this was insane
        // Nice job finding an incorrect answer that took me twenty minutes to disprove
        String word1 = "mnkulcaprv";
        String word2 = "vgpbbqckxr";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void TestAnagramMultiplicationWithoutMinusAEdgeCase()
    {
        // This is an edge case...
        // If you trigger it you are using a solution that is right for the other cases
        // It is still a wrong solution though
        String word1 = "avptaetfpeleknxdxexaxkplzxhlqepeqxnfpfxlultwtpgdyn";
        String word2 = "nbnxqthniphwpfvxkqtmhkehnyxdxubpcdbqhmlyxtqmhcyraj";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }

    @Test
    public void TestAnagramAdditionEdgeCase()
    {
        // If you trigger this test case it is because you are adding ascii values together...
        // This is incorrect
        String word1 = "rmhnpavstq";
        String word2 = "tcppprstrb";

        Assertions.assertFalse(AnagramChecker.isAnagram(word1, word2));
    }
}