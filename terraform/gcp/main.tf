terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 5.0"
    }
  }

  required_version = ">= 1.6.0"
}

provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_cloud_run_v2_service" "kanji" {
  name     = "kanji-flashcards"
  location = var.region

  template {
    containers {
      image = "${var.image_repo}:${var.image_tag}"

      ports {
        container_port = 8080
      }
    }
  }

  traffic {
    type    = "TRAFFIC_TARGET_ALLOCATION_TYPE_LATEST"
    percent = 100
  }
}

resource "google_cloud_run_v2_service_iam_member" "public" {
  name     = google_cloud_run_v2_service.kanji.name
  location = google_cloud_run_v2_service.kanji.location
  role     = "roles/run.invoker"
  member   = "allUsers"
}
