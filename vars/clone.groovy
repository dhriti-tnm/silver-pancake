def call(String repoUrl, String branchName) {
  echo "Cloning code from ${repoUrl} branch ${branchName}"
  git url: repoUrl, branch: branchName
}
