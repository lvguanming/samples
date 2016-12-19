package ramp.sample.jira.rest.java.client;

/**
 * 
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Version;

/**
 * @author Rama Palaniappan
 */
public class TestJiraImpl extends BaseTest {

	@Autowired
	private JiraClient jiraService;

	@DataProvider
	public Object[][] jiraIssue() {
		return new Object[][] {
				{ "TST", 1L, "Test Summary from JIRA rest client api",
						"Test Desc from JIRA rest client api",
						Arrays.asList("CMT: TEST"),
						"CMT:OPS Notification Scheme v1.0 (standard)",
						Arrays.asList("rpalaniappan", "mgopikrishna"),
						Arrays.asList("Label_Created_Thru_REST_API", "Label2"), true },
				{ null, 1L, null, null, null, null, null, null, false },
				{ null, null, "Test Summary from JIRA rest client api", null,
						null, null, null, null, false },
				{ null, null, null, "Test Desc from JIRA rest client api",
						null, null, null, null, false },
				{ null, null, null, null, Arrays.asList("CMT: TEST"), null,
						null, null, false },
				{ null, null, null, null, null,
						"CMT:OPS Notification Scheme v1.0 (standard)", null,
						null, false } };

	}

	@Test(dataProvider = "jiraIssue")
	public void createJiraIssue(String projectKey, Long issueTypeId,
			String issueSummary, String issueDesc, List<String> versionNames,
			String componentName, List<String> watchers, List<String> labels,
			boolean pass) {

		String issueKey = null;
		try {
			issueKey = jiraService.createIssue(projectKey, issueTypeId,
					issueSummary, issueDesc, versionNames, componentName,
					watchers, labels);
		} catch (Exception e) {
			if (pass) {
				Assert.fail("Unexpected exception: " + e.getLocalizedMessage());
			}
		}

		if (pass) {
			Assert.assertNotNull(issueKey, "Issue key is null");
		}
	}

	@DataProvider
	public Object[][] jiraComments() {
		return new Object[][] { { "TST-235", "Comments on TST-235", true },
				{ "TST-235", null, false }, { "TST-235", "", false },
				{ null, "Comments on TST-235", false },
				{ "", "Comments on TST-235", false } };
	}

	@DataProvider
	public Object[][] jiraWatchers() {
		return new Object[][] { { "TST-235", "rpalaniappan", true },
				{ "TST-235", null, true }, { null, "rpalaniappan", false } };
	}

	@Test(dataProvider = "jiraComments")
	public void addCommentsToJiraIssue(String issueKey, String comment,
			boolean pass) {
		try {

			jiraService.addCommentToIssue(issueKey, comment);
		} catch (Exception e) {
			if (pass)
				Assert.fail("Unexpected exception: " + e.getLocalizedMessage());
		}
	}

	/*
	 * @Test(dataProvider =
	 * "jiraComments",expectedExceptions={RestClientException
	 * .class,IllegalArgumentException.class}) public void
	 * addCommentsToJiraIssueNegativeTest(String issueKey, String comment,
	 * boolean pass) throws IOException {
	 * jiraService.addCommentToJiraIssue(issueKey, comment); }
	 */

	@Test(dataProvider = "jiraWatchers")
	public void addWatchersToJiraIssue(String issueKey, String watcherUsername,
			boolean pass) {
		try {
			jiraService.addWatcherToIssue(issueKey, watcherUsername);
		} catch (Exception e) {
			if (pass)
				Assert.fail("Unexpected exception: " + e.getLocalizedMessage());
		}
	}

	/*
	 * @Test(dataProvider =
	 * "jiraWatchers",expectedExceptions={RestClientException
	 * .class,IllegalArgumentException.class}) public void
	 * addWatchersToJiraIssueNegativeTest(String issueKey, String
	 * watcherUsername, boolean pass) throws IOException {
	 * jiraService.addWatcherToJiraIssue(issueKey, watcherUsername); }
	 */

	@Test
	public void getJiraIssue() throws IOException {
		String issueKey = "TST-90";
		BasicIssue issue = jiraService.getIssue(issueKey);
		Assert.assertEquals(issueKey, issue.getKey());

	}

	/*
	 * @Test(expectedExceptions = RestClientException.class) public void
	 * getJiraIssueNegativeTest() throws IOException { String issueKey =
	 * "TST-90"; BasicIssue issue = jiraService.getJiraIssue(issueKey);
	 * 
	 * }
	 */

	@DataProvider
	public Object[][] dpIsVersionPresent() {
		return new Object[][] { { "SAMPLE", "1.0" }, };
	}

	@Test(dataProvider = "dpIsVersionPresent")
	public void isVersionPresent(String projectKey, String versionName)
			throws IOException {
		Version version = jiraService.getVersion(projectKey, versionName);
		Assert.assertEquals(version.getName(), versionName);
	}

	@DataProvider
	public Object[][] dpcreateVersion() {
		return new Object[][] { { "SAMPLE", "" + System.currentTimeMillis() }, };
	}

	@Test(dataProvider = "dpcreateVersion")
	public void createVersion(String projectKey, String versionName)
			throws IOException {
		Version version = jiraService.createVersion(projectKey, versionName);
		Assert.assertEquals(versionName, version.getName());
	}
}
