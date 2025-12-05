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

resource "google_compute_network" "vpc" {
  name                    = "kanji-vpc"
  auto_create_subnetworks = false
}

resource "google_compute_subnetwork" "subnet" {
  name          = "kanji-subnet"
  ip_cidr_range = "10.0.0.0/24"
  region        = var.region
  network       = google_compute_network.vpc.id
}

resource "google_compute_firewall" "allow_http" {
  name    = "allow-http"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports    = ["80"]
  }

  source_ranges = ["0.0.0.0/0"]
}

resource "google_project_service" "run_api" {
  project = var.project_id
  service = "run.googleapis.com"
  disable_on_destroy = false
}

resource "google_cloud_run_service" "kanji" {
  name     = var.service_name
  location = var.region

  template {
    spec {
      containers {
        image = "europe-west2-docker.pkg.dev/${var.project_id}/kanji-app/kanji-flashcards:latest"
        ports {
          container_port = 8080
        }
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }
}

resource "google_cloud_run_service_iam_member" "public_invoker" {
  service = google_cloud_run_service.kanji.name
  location = google_cloud_run_service.kanji.location
  role   = "roles/run.invoker"
  member = "allUsers"
}

output "cloud_run_url" {
  value = google_cloud_run_service.kanji.status[0].url
}
