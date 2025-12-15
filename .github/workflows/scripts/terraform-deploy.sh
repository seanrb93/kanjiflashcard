#!/bin/bash
set -e

echo "Initializing Terraform..."
terraform init -input=false
echo "Deploying to $1"

CLOUD_PROVIDER=$1
GCP_PROJECT_ID=$2
AZURE_SUBSCRIPTION_ID=$3
GITHUB_REPOSITORY_OWNER=$4
GITHUB_SHA=$5

if [ "$CLOUD_PROVIDER" = "gcp" ]; then
  terraform apply -auto-approve -input=false \
    -var "project_id=$GCP_PROJECT_ID" \
    -var "image_repo=europe-west2-docker.pkg.dev/$GCP_PROJECT_ID/kanji-app/kanji-flashcards" \
    -var "image_tag=$GITHUB_SHA"
elif [ "$CLOUD_PROVIDER" = "azure" ]; then
  terraform apply -auto-approve -input=false \
    -var "subscription_id=$AZURE_SUBSCRIPTION_ID" \
    -var "image_repo=ghcr.io/$GITHUB_REPOSITORY_OWNER/kanji-flashcards" \
    -var "image_tag=$GITHUB_SHA"
else
  terraform apply -auto-approve -input=false \
    -var "image_repo=ghcr.io/$GITHUB_REPOSITORY_OWNER/kanji-flashcards" \
    -var "image_tag=$GITHUB_SHA"
fi