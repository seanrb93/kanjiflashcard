variable "project_id" {
    type        = string
    description = "The GCP project ID where resources will be created."
}

variable "region" {
    type        = string
    description = "The GCP region where resources will be created."
    default     = "us-central1"
}

variable "image_repo" {
    type        = string
    description = "The container image repository for the Cloud Run service."
}

variable "image_tag" {
    type        = string
    description = "The tag of the container image for the Cloud Run service."
    default     = "latest"
}