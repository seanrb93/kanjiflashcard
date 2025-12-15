terraform {
  backend "gcs" {
    bucket = "kanji-tf-state-gcp"
    prefix = "terraform/state"
  }
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "7.12.0"
    }
  }

  required_version = ">= 1.6.0"
  
}

provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_project_service" "run_api" {
  project = var.project_id
  service = "run.googleapis.com"
  disable_on_destroy = false
}

resource "google_project_service" "iam_api" {
  project = var.project_id
  service = "iam.googleapis.com"
  disable_on_destroy = false
}

resource "google_project_service" "artifact_registry_api" {
  project = var.project_id
  service = "artifactregistry.googleapis.com"
  disable_on_destroy = false
}

resource "google_service_account" "cloud_run_sa" {
  account_id   = "kanji-cloud-run"
  display_name = "Kanji Cloud Run Service Account"
}

resource "google_cloud_run_v2_service" "kanji" {
  name     = var.service_name
  location = var.region

  template {
    service_account = google_service_account.cloud_run_sa.email
    
      containers {
        image = "${var.image_repo}:${var.image_tag}"
        ports {
          container_port = 8080
        }
      }
  }

  depends_on = [ 
  google_project_service.run_api,
  google_project_service.iam_api,
  google_project_service.artifact_registry_api
  ]
}

resource "google_cloud_run_v2_service_iam_member" "invoker" {
  name     = google_cloud_run_v2_service.kanji.name
  location = google_cloud_run_v2_service.kanji.location
  role     = "roles/run.invoker"
  member   = var.invoker_member
}

output "cloud_run_url" {
  value = google_cloud_run_v2_service.kanji.uri
}
