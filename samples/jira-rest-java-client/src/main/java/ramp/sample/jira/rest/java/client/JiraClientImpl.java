package ramp.sample.jira.rest.java.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.atlassian.jira.rest.client.api.GetCreateIssueMetadataOptions;
import com.atlassian.jira.rest.client.api.GetCreateIssueMetadataOptionsBuilder;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.BasicComponent;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.CimFieldInfo;
import com.atlassian.jira.rest.client.api.domain.CimIssueType;
import com.atlassian.jira.rest.client.api.domain.CimProject;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.EntityHelper;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.api.domain.input.VersionInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * 
 * @author Rama Palaniappan
 */
public class JiraClientImpl implements JiraClient {

	private JiraRestClient jiraRestClient = null;
	private static final Log LOG = LogFactory.getLog(JiraClientImpl.class);

	public JiraClientImpl(String jiraBaseUrl, String jiraUserName,
			String jiraPassword) {
		loginToJira(jiraBaseUrl, jiraUserName, jiraPassword);
	}

	private void loginToJira(String baseUrl, String userName, String password) {
		final JiraRestClientFactory jiraRestClientFactory = new AsynchronousJiraRestClientFactory();
		jiraRestClient = jiraRestClientFactory
				.createWithBasicHttpAuthentication(URI.create(baseUrl),
						userName, password);
	}

	public String createIssue(String projectKey, Long issueTypeId,
			String issueSummary, String issueDescription,
			List<String> versionNames, String componentName,
			List<String> watchers, List<String> labels) {
		String issueKey = null;

		// Issue Summary should not have new line characters
		IssueInputBuilder issueBuilder = new IssueInputBuilder(projectKey,
				issueTypeId, (issueSummary != null ? issueSummary.replaceAll(
						"\\n", "") : issueSummary));

		// Find or create version. User executing this piece of code should have
		// required permission to create a version
		try {
			if (versionNames != null) {
				for (String versionString : versionNames) {
					findOrCreateVersion(projectKey, versionString);
				}
			}
			issueBuilder.setAffectedVersionsNames(versionNames);
		} catch (RestClientException rce) {
			LOG.warn("Unable to create version " + versionNames
					+ " for Jira project " + projectKey + ". "
					+ rce.getLocalizedMessage());
		}

		issueBuilder.setDescription(issueDescription);

		// Component
		if (componentName != null) {
			ArrayList<BasicComponent> basicComponents = new ArrayList<BasicComponent>();
			BasicComponent component = new BasicComponent(null, null,
					componentName, null);
			basicComponents.add(component);
			issueBuilder.setComponents(basicComponents);
		}
		if (labels != null && labels.size() > 0) {
			issueBuilder.setFieldInput(new FieldInput(
					IssueFieldId.LABELS_FIELD, labels));
		}

		IssueInput newIssue = issueBuilder.build();

		IssueRestClient issueRestClient = jiraRestClient.getIssueClient();
		BasicIssue basicIssue = null;
		basicIssue = issueRestClient.createIssue(newIssue).claim();
		issueKey = basicIssue.getKey();
		Issue issue = getIssue(basicIssue);

		// Watchers
		if (watchers != null) {
			for (String watcher : watchers) {
				issueRestClient.addWatcher(issue.getWatchers().getSelf(),
						watcher);
			}
		}
		return issueKey;
	}

	public void addCommentToIssue(String issueKey, String comment) {
		final IssueRestClient issueClient = jiraRestClient.getIssueClient();
		final Issue jiraIssue = issueClient.getIssue(issueKey).claim();
		issueClient.addComment(jiraIssue.getCommentsUri(),
				Comment.valueOf(comment)).claim();
	}

	public void addWatcherToIssue(String issueKey, String watcherUsername) {
		final IssueRestClient issueClient = jiraRestClient.getIssueClient();
		final Issue jiraIssue = issueClient.getIssue(issueKey).claim();
		issueClient.addWatcher(jiraIssue.getWatchers().getSelf(),
				watcherUsername).claim();
	}

	public Issue getIssue(String issueKey) {
		Issue jiraIssue = jiraRestClient.getIssueClient().getIssue(issueKey)
				.claim();
		return jiraIssue;
	}

	private Issue getIssue(BasicIssue basicIssue) {
		if (basicIssue == null) {
			return null;
		}
		if (basicIssue instanceof Issue) {
			return (Issue) basicIssue;
		} else {
			return getIssue(basicIssue.getKey());
		}
	}

	public Version findOrCreateVersion(String projectKey, String version) {
		Version ver = null;
		try {
			ver = getVersion(projectKey, version);
		} catch (VersionNotPresentException e) {
			ver = createVersion(projectKey, version);
		}
		return ver;
	}

	public Version getVersion(String projectKey, String version) {
		if (version == null || projectKey == null) {
			throw new IllegalArgumentException("Null Arguments");
		}
		Project project = jiraRestClient.getProjectClient()
				.getProject(projectKey).claim();
		Iterable<Version> versions = project.getVersions();
		for (Version ver : versions) {
			if (ver != null && version.equalsIgnoreCase(ver.getName())) {
				return ver;
			}
		}
		throw new VersionNotPresentException("Version: " + version
				+ " not found in project: " + projectKey);
	}

	public Version createVersion(String projectKey, String versionName) {
		String versionDesc = "Version created thru' JIRA REST Java Client";
		VersionInput verInput = VersionInput.create(projectKey, versionName,
				versionDesc, null, false, false);
		Version version = jiraRestClient.getVersionRestClient()
				.createVersion(verInput).claim();
		return version;
	}

	// Close JIRA connection
	public void logoutFromJira(){
		try {
			jiraRestClient.close();
		} catch (IOException e) {
			LOG.info("Exception while closing Jira Rest Client", e);
		}
	}

	public HashMap<String, CimFieldInfo> getIssueRequiredCreateFields(
			String projectKey, Long issueTypeId) throws RestClientException {
		HashMap<String, CimFieldInfo> hm = new HashMap<String, CimFieldInfo>();
		try {
			GetCreateIssueMetadataOptions options = new GetCreateIssueMetadataOptionsBuilder()
					.withExpandedIssueTypesFields()
					.withIssueTypeIds(issueTypeId).withProjectKeys(projectKey)
					.build();

			Iterable<CimProject> cimProjects = jiraRestClient.getIssueClient()
					.getCreateIssueMetadata(options).claim();

			// get project
			if (cimProjects.iterator().hasNext()) {
				CimProject project = cimProjects.iterator().next();
				// get issue type by name
				CimIssueType bug = EntityHelper.findEntityById(
						project.getIssueTypes(), 1L);

				Set<String> keys = bug.getFields().keySet();
				for (String key : keys) {
					// get issue type field
					CimFieldInfo issuetypeField = bug.getFields().get(key);

					if (issuetypeField.isRequired()) {
						hm.put(issuetypeField.getId(), issuetypeField);
					}
				}
			}
		} catch (RestClientException rce) {
			throw new RestClientException(rce.getCause());
		}
		return hm;
	}
}