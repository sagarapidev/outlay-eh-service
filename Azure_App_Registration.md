# GitHub Actions → Azure Federated Identity Setup

This guide consolidates the steps to register an Azure Service Principal, configure federated credentials for GitHub Actions, and assign the necessary roles.

---

## 1. Create Service Principal

```bash
az ad sp create-for-rbac \
  --name "github-deploy-sp" \
  --role contributor \
  --scopes /subscriptions/<SUBSCRIPTION_ID>/resourceGroups/<RESOURCE_GROUP>


{
  "appId": "b29b649b-e167-4b2f-a642-ac0d9cc412cd",   # AZURE_CLIENT_ID
  "displayName": "github-deploy-sp",
  "password": <password>, # AZURE_CLIENT_SECRET
  "tenant": "f3f67193-3f5a-4b1e-ba8a-f4b397127a9f"   # AZURE_TENANT_ID
}

## 2. Create Federated Credential
az ad app federated-credential create \
  --id <APP_ID or CLIENT_ID> \
  --parameters '{
    "name": "gh-outlay-eh-service-dev",
    "issuer": "https://token.actions.githubusercontent.com",
    "subject": "repo:<ORG>/<REPO>:environment:<ENVIRONMENT>",
    "audiences": ["api://AzureADTokenExchange"]
  }'
## 3. Assign Role to Service Principal
az role assignment create \
  --assignee <APP_ID or CLIENT_ID> \
  --role "Contributor" \
  --scope /subscriptions/<SUBSCRIPTION_ID>/resourceGroups/<RESOURCE_GROUP>
``` 
## 4.Verify assignment:

```bash
az role assignment list \
  --assignee <APP_ID or CLIENT_ID> \
  --scope /subscriptions/<SUBSCRIPTION_ID> \
  --output table

## 5. GitHub Repository Secrets
- AZURE_CLIENT_ID: b29b649b-e167-4b2f-a642-xxxx
- AZURE_CLIENT_SECRET: <AZURE_CLIENT_SECRET>
- AZURE_TENANT_ID: f3f67193-3f5a-4b1e-ba8a-f4b397127a9f
- AZURE_SUBSCRIPTION_ID: <SUBSCRIPTION_ID>
- AZURE_RESOURCE_GROUP: <RESOURCE_GROUP>
- AZURE_APP_NAME: github-deploy-sp

                

