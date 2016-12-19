/**
 * 
 */
package ramp.sample.jira.rest.java.client;

import java.util.HashMap;
import java.util.List;

import com.atlassian.jira.rest.client.api.domain.CimFieldInfo;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Version;

public interface JiraClient {
	public String createIssue(String projectKey, Long issueTypeId,
			String issueSummary, String issueDescription,
			List<String> versionNames, String componentName,
			List<String> watchers, List<String> labels);

	public void addCommentToIssue(String issueKey, String comment);

	public void addWatcherToIssue(String issueKey, String watcherUsername);

	public Issue getIssue(String issueKey);

	public Version findOrCreateVersion(String projectKey, String version);

	public Version getVersion(String projectKey, String version);

	public Version createVersion(String projectKey, String versionName);

	public HashMap<String, CimFieldInfo> getIssueRequiredCreateFields(
			String projectKey, Long issueTypeId);
}
